package org.apache.coyote.handler;

import org.apache.catalina.session.Session;
import org.apache.coyote.exception.MethodNotAllowedException;
import org.apache.coyote.exception.UnsupportedHandlerException;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

import java.util.LinkedList;

public final class HandlerManager {
    private static final LinkedList<HttpHandler> handlers = new LinkedList<>() {{
        add(HomeHandler.getInstance());
        add(FileHandler.getInstance());
    }};
    private static final HttpHandler methodNotAllowedHandler =
            MethodNotAllowedHandler.getInstance();

    private HandlerManager() {}

    public static HandlerManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void addHandler(HttpHandler... httpHandlers) {
        for (HttpHandler handler : httpHandlers) {
            handlers.addFirst(handler);
        }
    }

    public byte[] handle(HttpRequest request) {
        final HttpResponse response = getResponse(request);
        request.getSession().map(
                Session::getId
        ).ifPresent(response::setSession);
        return response.getBytes();
    }

    private HttpResponse getResponse(HttpRequest request) {
        try {
            return handlers.stream().filter(
                    handler -> handler.support(request)
            ).findFirst().orElseThrow(
                    () -> new UnsupportedHandlerException(request)
            ).handle(request);
        } catch (MethodNotAllowedException e) {
            return methodNotAllowedHandler.handle(request);
        }
    }

    private static class SingletonHolder {
        private static final HandlerManager INSTANCE = new HandlerManager();
    }
}
