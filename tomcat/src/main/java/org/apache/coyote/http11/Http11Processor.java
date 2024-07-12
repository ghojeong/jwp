package org.apache.coyote.http11;

import org.apache.coyote.Processor;
import org.apache.coyote.exception.UncheckedServletException;
import org.apache.coyote.handler.HandlerManager;
import org.apache.coyote.request.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

// https://github.com/apache/tomcat/blob/main/java/org/apache/coyote/http11/Http11Processor.java
public class Http11Processor implements Runnable, Processor {
    private static final Logger log = LoggerFactory.getLogger(Http11Processor.class);
    private static final HandlerManager handler = HandlerManager.getInstance();

    private final Socket connection;

    public Http11Processor(final Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        log.info("connect host: {}, port: {}", connection.getInetAddress(), connection.getPort());
        process(connection);
    }

    @Override
    public void process(final Socket connection) {
        try (final OutputStream outputStream = connection.getOutputStream();
             final BufferedReader bufferedReader = new BufferedReader(
                     new InputStreamReader(connection.getInputStream())
             )
        ) {
            outputStream.write(handler.handle(
                    HttpRequest.from(bufferedReader)
            ));
            outputStream.flush();
        } catch (final IOException | UncheckedServletException e) {
            log.error(e.getMessage(), e);
        }
    }
}
