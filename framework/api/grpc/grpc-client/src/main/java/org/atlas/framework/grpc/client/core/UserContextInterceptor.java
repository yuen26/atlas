package org.atlas.framework.grpc.client.core;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.atlas.shared.constant.CustomHeaders;
import org.atlas.shared.context.UserContext;
import org.atlas.shared.context.UserInfo;

@GrpcGlobalClientInterceptor
public class UserContextInterceptor implements ClientInterceptor {

    private static final Metadata.Key<String> USER_ID_HEADER =
        Metadata.Key.of(CustomHeaders.USER_ID, Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> USER_ROLE_HEADER =
        Metadata.Key.of(CustomHeaders.USER_ROLE, Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                               CallOptions callOptions,
                                                               Channel next) {
        UserInfo userInfo = UserContext.getCurrentUser();
        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(USER_ID_HEADER, String.valueOf(userInfo.getUserId()));
                headers.put(USER_ROLE_HEADER, userInfo.getRole().name());
                super.start(responseListener, headers);
            }
        };
    }
}
