package org.atlas.framework.notification.core;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class NotificationDispatcher {

    private final List<NotificationHandler> handlers;

    private Map<NotificationId, List<NotificationHandler>> handlerMap;

    @PostConstruct
    public void init() {
        handlerMap = handlers.stream()
            .collect(Collectors.groupingBy(NotificationHandler::notificationId));
    }

    public void dispatch(Notification notification) {
        List<NotificationHandler> matchingHandlers = handlerMap.get(notification.getId());
        if (CollectionUtils.isEmpty(matchingHandlers)) {
            throw new RuntimeException("Not found handler for notification ID " + notification.getId());
        }
        for (NotificationHandler handler : matchingHandlers) {
            try {
                handler.handle(notification.getData());
                log.info("Done notify: id={}, channel={}, data={}",
                    notification.getId(), handler.channel(), notification.getData());
            } catch (Exception e) {
                log.error("Failed to notify: id={}, channel={}, data={}",
                    notification.getId(), handler.channel(), notification.getData(), e);
                throw e;
            }
        }
    }
}
