package org.atlas.framework.template.contract;

import org.springframework.lang.NonNull;

import java.util.Map;

public interface TemplateResolver {

    String resolve(@NonNull String templateName) throws Exception;

    String resolve(@NonNull String templateName, @NonNull Map<String, Object> data) throws Exception;
}
