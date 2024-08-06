package org.atlas.framework.storage.firebase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "firebase")
@Data
public class FirebaseProperties {

    private String credentials;
    private String bucket;
}
