package com.tys.legalbases.infrastructure.external;

import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import static feign.Util.UTF_8;
import static feign.Util.decodeOrDefault;

@Slf4j
public class FeignRequestLogger extends feign.Logger {

    private static final ThreadLocal<String> lastRequest = new ThreadLocal<>();
    private final AtomicLong requestId = new AtomicLong();

    public static String getLastRequest() {
        return lastRequest.get();
    }

    public static void clearLastRequest() {
        lastRequest.remove();
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        long id = requestId.incrementAndGet();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("---> %s %s HTTP/1.1%n", request.method(), request.url()));
        request.headers().forEach((key, value) ->
                sb.append(String.format("%s: %s%n", key, value.iterator().next())));


        int bodyLength = 0;
        if (request.body() != null) {
            bodyLength = request.body().length;
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                String bodyText = request.charset() != null ? new String(request.body(), request.charset()) : new String(request.body(), UTF_8);
                sb.append(String.format("%n%s%n", bodyText));
            }
        }
        sb.append(String.format("---> END HTTP (%s-byte body)%n", bodyLength));

        
        String requestString = sb.toString();
        lastRequest.set(requestString); // Store the request string
        log.debug(requestString); // Also log it for console visibility
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        long id = requestId.get();
        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("<--- HTTP/1.1 %s %s (%sms)%n", response.status(), response.reason(), elapsedTime));
            response.headers().forEach((key, value) ->
                    sb.append(String.format("%s: %s%n", key, value.iterator().next())));

            int bodyLength = 0;
            if (response.body() != null && !(response.status() == 204 || response.status() == 205)) {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                bodyLength = bodyData.length;
                if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                    sb.append(String.format("%n%s%n", decodeOrDefault(bodyData, response.charset(), String.valueOf(Util.UTF_8))));
                }
                response = response.toBuilder().body(bodyData).build(); // Rebuffer the response body

            }
            sb.append(String.format("<--- END HTTP (%s-byte body)%n", bodyLength));
            log.debug(sb.toString());
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        // Delegate to slf4j
        if (log.isDebugEnabled()) {
            log.debug(String.format(methodTag(configKey) + format, args));
        }
    }
}
