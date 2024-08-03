package org.atlas.framework.rest.server.core.idempotence;

import org.atlas.shared.util.IdGenerator;

public class IdempotenceKeyGenerator {

    private IdempotenceKeyGenerator() {
    }

    public static String generate() {
        return IdGenerator.uuid();
    }
}
