package org.atlas.framework.persistence.jpa.core.specification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryFilter {

    private String key;
    private Object value;
    private QueryOperation operation;

    public static QueryFilter of(@NonNull String key, @NonNull Object value, @NonNull QueryOperation operation) {
        QueryFilter instance = new QueryFilter();
        instance.setKey(key);
        instance.setValue(value);
        instance.setOperation(operation);
        return instance;
    }
}
