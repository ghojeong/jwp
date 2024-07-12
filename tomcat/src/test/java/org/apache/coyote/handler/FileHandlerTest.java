package org.apache.coyote.handler;

import org.apache.coyote.request.HttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FileHandlerTest {
    private static final HttpHandler handler = FileHandler.getInstance();

    @DisplayName("Static File 에 대한 요청을 지원하고 처리할 수 있어야 한다.")
    @Test
    void handle() throws IOException {
        final HttpRequest request = HttpRequest.from(new BufferedReader(new StringReader(
                String.join(
                        "\r\n",
                        "GET /index.html HTTP/1.1",
                        "Host: localhost:8080",
                        "Connection: keep-alive",
                        "",
                        ""
                )
        )));

        final String body = new String(Files.readAllBytes(new File(
                getClass().getClassLoader().getResource("static/index.html").getFile()
        ).toPath()));
        assertAll(
                () -> assertThat(
                        handler.support(request)
                ).isTrue(),
                () -> assertThat(
                        handler.handle(request).toString()
                ).isEqualTo(String.join(
                        "\r\n",
                        "HTTP/1.1 200 OK",
                        "Content-Length: " + body.length(),
                        "Content-Type: text/html;charset=utf-8",
                        "",
                        body
                ))
        );
    }
}
