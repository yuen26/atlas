package org.atlas.commons.utils.idgenerator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDGenerator {

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
