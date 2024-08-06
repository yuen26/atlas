package org.atlas.framework.rest.client.apachehttpclient.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Slf4j
public class RestLoggingResponseInterceptor implements HttpResponseInterceptor {

    @Override
    public void process(HttpResponse response, EntityDetails entity, HttpContext context) throws HttpException, IOException {
        int statusCode = response.getCode();
        String body = getBody(response);

        log.info("Response: HTTP status code = {}, Response body = {}", statusCode, body);
    }

    private String getBody(HttpResponse response) throws IOException {
        if (response instanceof BasicClassicHttpResponse) {
            BasicClassicHttpResponse castResponse = (BasicClassicHttpResponse) response;
            HttpEntity httpEntity = castResponse.getEntity();
            if (httpEntity == null) {
                return null;
            }
            BufferedReader buffer = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            String body = buffer.lines().collect(Collectors.joining(System.lineSeparator()));
            castResponse.setEntity(new StringEntity(body));
            return body;
        }
        return null;
    }
}
