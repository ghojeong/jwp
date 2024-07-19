package org.apache.catalina.session;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// https://github.com/jakartaee/servlet/blob/master/api/src/main/java/jakarta/servlet/http/HttpSession.java
public class HttpSession implements Session {
    private static final int DEFAULT_TTL_SECONDS = 3600;

    private final LocalDateTime expiryAt;
    private final String id;
    private final Map<String, Object> session = new HashMap<>();

    public HttpSession(String sessionId, int ttlSeconds) {
        this.id = sessionId;
        this.expiryAt = LocalDateTime.now().plusSeconds(ttlSeconds);
    }

    public HttpSession(String sessionId) {
        this(sessionId, DEFAULT_TTL_SECONDS);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getAttribute(String name) {
        return session.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        session.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        session.remove(name);
    }

    @Override
    public void invalidate() {
        session.clear();
    }

    @Override
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryAt);
    }
}
