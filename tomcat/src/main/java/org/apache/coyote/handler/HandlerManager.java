package org.apache.coyote.handler;

import org.apache.coyote.exception.UnsupportedHandlerException;
import org.apache.coyote.request.HttpRequest;

import java.util.LinkedList;

public final class HandlerManager {
    private static final LinkedList<HttpHandler> handlers = new LinkedList<>() {{
        add(HomeHandler.getInstance());
        add(FileHandler.getInstance());
    }};

    private HandlerManager() {}

    public static HandlerManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addHandler(HttpHandler handler) {
        handlers.addFirst(handler);
    }

    public byte[] handle(HttpRequest request) {
        return handlers.stream().filter(
                handler -> handler.support(request)
        ).findFirst().orElseThrow(
                () -> new UnsupportedHandlerException(request)
        ).handle(request).getBytes();
    }

    private static class SingletonHolder {
        private static final HandlerManager INSTANCE = new HandlerManager();
    }
}
