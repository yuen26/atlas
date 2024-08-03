package org.atlas.shared.util.json;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    private static final JsonOps OPS = new JacksonOps();

    public static <T> T toObject(String source, Class<T> type) {
        return OPS.toObject(source, type);
    }

    public static <T> List<T> toList(String source, Class<T> type) {
        return OPS.toList(source, type);
    }

    public static String toJson(Object source) {
        return OPS.toJson(source);
    }
}
