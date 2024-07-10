package org.apache.coyote;

import java.net.Socket;

// https://github.com/apache/tomcat/blob/main/java/org/apache/coyote/Processor.java
public interface Processor {
    void process(Socket socket);
}
