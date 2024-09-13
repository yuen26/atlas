package org.atlas.framework.template.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.atlas.framework.template.contract.TemplateResolver;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FreemarkerTemplateResolver implements TemplateResolver {

    private final Configuration configuration;

    @Override
    public String resolve(@NonNull String templateName) throws TemplateException, IOException {
        return resolve(templateName, new HashMap<>());
    }

    /**
     * @param templateName The relative path of template file in resources/templates folder.
     */
    @Override
    public String resolve(@NonNull String templateName, @NonNull Map<String, Object> data) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName), data);
    }
}
