package org.atlas.edge.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private static class Claims {
        static final String USER_ID = "user_id";
        static final String ROLE = "role";
    }

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String ISSUER = "my-app";
    private static final long EXPIRY_TIME = 1000L * 60 * 60 * 24 * 30; // The token expires in 30 days.

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = loadPublicKey(keyFactory);
        this.privateKey = loadPrivateKey(keyFactory);
    }

    public String issueToken(LoginInfo loginInfo) {
        long now = System.currentTimeMillis();
        return JWT.create()
                .withSubject(loginInfo.getUsername())
                .withIssuer(ISSUER)
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + EXPIRY_TIME))
                .withJWTId(UUID.randomUUID().toString())
                .withClaim(Claims.USER_ID, loginInfo.getUserId())
                .withClaim(Claims.ROLE, loginInfo.getRole())
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

    public LoginInfo verifyToken(String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);

        DecodedJWT decodedJWT = JWT.require(Algorithm.RSA256(publicKey))
                .withIssuer(ISSUER)
                .build()
                .verify(token);

        // Check blacklist
        String blacklistRedisKey = getBlacklistRedisKey(token);
        if (redisTemplate.opsForValue().get(blacklistRedisKey) != null) {
            throw new RuntimeException("The token was inactivated");
        }

        return obtainLoginInfo(decodedJWT);
    }

    public void revokeToken(String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);

        String blacklistRedisKey = getBlacklistRedisKey(token);

        // Calculate TTL
        DecodedJWT decodedJWT = JWT.decode(token);
        long remainingTimeMillis = decodedJWT.getExpiresAt().getTime() - System.currentTimeMillis();
        if (remainingTimeMillis <= 0) {
            log.warn("Token has expired: {}", token);
            return;
        }

        redisTemplate.opsForValue().set(blacklistRedisKey, true, remainingTimeMillis, TimeUnit.MILLISECONDS);
    }

    private RSAPublicKey loadPublicKey(KeyFactory keyFactory) throws IOException, InvalidKeySpecException {
        byte[] publicKeyBytes = Files.readAllBytes(
                ResourceUtils.getFile("classpath:secret/token.pub").toPath());
        String publicKeyContent = new String(publicKeyBytes)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    private RSAPrivateKey loadPrivateKey(KeyFactory keyFactory) throws IOException, InvalidKeySpecException {
        byte[] privateKeyBytes = Files.readAllBytes(
                ResourceUtils.getFile("classpath:secret/token.key").toPath());
        String privateKeyContent = new String(privateKeyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (!authorizationHeader.startsWith(TOKEN_PREFIX)) {
            throw new RuntimeException("Invalid token prefix");
        }
        final String accessToken = authorizationHeader.substring(TOKEN_PREFIX.length()).trim();
        if (!StringUtils.hasText(accessToken)) {
            throw new RuntimeException("Obtained an empty token after truncating token prefix");
        }
        return accessToken;
    }

    private String getBlacklistRedisKey(String token) {
        return  "token_blacklist:" + token;
    }

    private LoginInfo obtainLoginInfo(DecodedJWT decodedJWT) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(decodedJWT.getSubject());
        loginInfo.setUserId(decodedJWT.getClaim(Claims.USER_ID).asInt());
        String role = decodedJWT.getClaim(Claims.ROLE).asString();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        loginInfo.setAuthorities(authorities);
        return loginInfo;
    }
}
