package org.apache.catalina.session;

import java.util.Optional;

// https://github.com/apache/tomcat/blob/main/java/org/apache/catalina/Manager.java
public interface Manager {
    void add(Session session);

    Optional<Session> findSession(String id);

    void remove(Session session);

    void clear();

    Session createSession();

    Session createSession(int ttlSeconds);
}
