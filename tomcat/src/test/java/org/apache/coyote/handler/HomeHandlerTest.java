package org.apache.coyote.handler;

import org.apache.coyote.request.HttpRequest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HomeHandlerTest {

    @Test
    void support() throws IOException {
        final HttpRequest request = HttpRequest.from(new BufferedReader(new StringReader(
                String.join(
                        "\r\n",
                        "GET / HTTP/1.1 ",
                        "Host: localhost:8080 ",
                        "Connection: keep-alive ",
                        "",
                        ""
                )
        )));
        assertAll(
                () -> assertThat(
                        HomeHandler.getInstance()
                                .support(request)
                ).isTrue(),
                () -> assertThat(
                        HomeHandler.getInstance()
                                .handle(request).toString()
                ).isEqualTo(String.join(
                        "\r\n",
                        "HTTP/1.1 200 OK",
                        "Content-Length: 12",
                        "Content-Type: text/html;charset=utf-8",
                        "",
                        "Hello world!"
                ))
        );
    }
}
