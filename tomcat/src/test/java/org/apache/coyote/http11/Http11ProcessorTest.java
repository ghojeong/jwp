package org.apache.coyote.http11;

import org.apache.coyote.Processor;
import org.apache.coyote.support.StubSocket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class Http11ProcessorTest {

    @DisplayName("루트에 대한 HTTP GET 요청을 처리할 수 있다.")
    @Test
    void process() {
        // given
        final StubSocket socket = new StubSocket();
        final Processor processor = new Http11Processor(socket);

        // when
        processor.process(socket);

        // then
        assertThat(socket.output()).isEqualTo(String.join(
                "\r\n",
                "HTTP/1.1 200 OK",
                "Content-Length: 12",
                "Content-Type: text/html;charset=utf-8",
                "",
                "Hello world!"
        ));
    }

    @DisplayName("index.html에 대한 HTTP GET 요청을 처리할 수 있다.")
    @Test
    void index() throws IOException {
        // given
        final String httpRequest = String.join(
                "\r\n",
                "GET /index.html HTTP/1.1",
                "Host: localhost:8080",
                "Connection: keep-alive",
                "",
                ""
        );

        final var socket = new StubSocket(httpRequest);
        final Http11Processor processor = new Http11Processor(socket);

        // when
        processor.process(socket);

        // then
        final URL resource = getClass().getClassLoader().getResource("static/index.html");
        final String body = new String(Files.readAllBytes(new File(resource.getFile()).toPath()));
        var expected = String.join(
                "\r\n",
                "HTTP/1.1 200 OK",
                "Content-Length: " + body.length(),
                "Content-Type: text/html;charset=utf-8",
                "",
                body
        );

        assertThat(socket.output()).isEqualTo(expected);
    }
}
