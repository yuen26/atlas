package org.atlas.framework.notification.websocket;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String DESTINATION_PREFIX = "/topic";
    public static final String STOMP_ENDPOINT = "/ws";
}
