package org.apache.catalina.session;

// https://github.com/apache/tomcat/blob/main/java/org/apache/catalina/Session.java
// https://github.com/jakartaee/servlet/blob/master/api/src/main/java/jakarta/servlet/http/HttpSession.java
public interface Session {
    String getId();

    Object getAttribute(String name);

    void setAttribute(String name, Object value);

    void removeAttribute(String name);

    void invalidate();

    boolean isExpired();
}
