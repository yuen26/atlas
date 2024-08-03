package org.atlas.framework.i18n.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Locale locale;
    private final MessageSource messageSource;

    public String getMessage(String code, Object... params) {
        return messageSource.getMessage(code, params, locale);
    }
}
