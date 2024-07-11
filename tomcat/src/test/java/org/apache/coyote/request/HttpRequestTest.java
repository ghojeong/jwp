package org.apache.coyote.request;

import org.apache.coyote.common.HttpProtocol;
import org.apache.coyote.request.request_line.HttpMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpRequestTest {

    @DisplayName("HttpRequest 의 RequestLine, Headers, Body 를 파싱할 수 있어야 한다.")
    @Test
    void from() throws IOException {
        final String body = "My name is JeongWan Gho!";
        final String request = String.join(
                "\r\n",
                "POST /login?password=p@ssW0rd&account=gugu HTTP/1.1 ",
                "Host: localhost:8080 ",
                "Connection: keep-alive ",
                "Content-Length: " + body.length(),
                "",
                body
        );
        final BufferedReader reader = new BufferedReader(new StringReader(request));
        final HttpRequest actual = HttpRequest.from(reader);
        assertAll(
                () -> assertThat(actual.httpMethod())
                        .isEqualTo(HttpMethod.POST),
                () -> assertThat(actual.path())
                        .isEqualTo("/login"),
                () -> assertThat(actual.queryParam("account"))
                        .isEqualTo(Optional.of("gugu")),
                () -> assertThat(actual.queryParam("password"))
                        .isEqualTo(Optional.of("p@ssW0rd")),
                () -> assertThat(actual.protocol())
                        .isEqualTo(HttpProtocol.HTTP_11),
                () -> assertThat(actual.header("Host"))
                        .isEqualTo(Optional.of("localhost:8080")),
                () -> assertThat(actual.header("Connection"))
                        .isEqualTo(Optional.of("keep-alive")),
                () -> assertThat(actual.contentLength())
                        .isEqualTo(body.length()),
                () -> assertThat(actual.toString()).isEqualTo(String.join(
                        "\r\n",
                        "POST /login?password=p@ssW0rd&account=gugu HTTP/1.1",
                        "Connection: keep-alive",
                        "Host: localhost:8080",
                        "Content-Length: " + body.length(),
                        "",
                        body
                ))
        );
        reader.close();
    }
}
