package org.apache.catalina.session;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.UUID.randomUUID;

// https://github.com/apache/tomcat/blob/main/java/org/apache/catalina/Manager.java
public class SessionManager implements Manager {
    private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    public static SessionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void add(Session session) {
        invalidate();
        sessionMap.put(session.getId(), session);
    }

    @Override
    public Optional<Session> findSession(String id) {
        return Optional.ofNullable(sessionMap.get(id));
    }

    @Override
    public void remove(Session session) {
        session.invalidate();
        sessionMap.remove(session.getId());
    }

    @Override
    public void clear() {
        sessionMap.clear();
    }

    @Override
    public Session createSession() {
        return createSession(randomUUID().toString());
    }

    @Override
    public Session createSession(int ttlSeconds) {
        final Session session = new HttpSession(
                randomUUID().toString(), ttlSeconds
        );
        add(session);
        return session;
    }

    private Session createSession(String id) {
        final Session session = new HttpSession(id);
        add(session);
        return session;
    }

    private void invalidate() {
        sessionMap.values().stream()
                .filter(Session::isExpired)
                .forEach(this::remove);
    }

    private static class SingletonHolder {
        private static final SessionManager INSTANCE = new SessionManager();
    }
}
