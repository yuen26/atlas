package org.atlas.commons.utils.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Base64Util {

    public static String encode(String input) {
        return Base64.encodeBase64String(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String input) {
        return new String(Base64.decodeBase64(input));
    }
}
