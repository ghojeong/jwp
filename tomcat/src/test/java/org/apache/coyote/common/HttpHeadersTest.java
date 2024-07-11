package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpHeadersTest {

    @DisplayName("Request 의 Header 들을 파싱할 수 있어야 한다.")
    @Test
    void from() {
        final HttpHeaders actual = HttpHeaders.from(List.of(
                "Host: localhost:8080 ",
                "Connection: keep-alive ",
                "Content-Length: 42"
        ));
        assertAll(
                () -> assertThat(actual.get("Host"))
                        .isEqualTo(Optional.of("localhost:8080")),
                () -> assertThat(actual.get("Connection"))
                        .isEqualTo(Optional.of("keep-alive")),
                () -> assertThat(actual.contentLength())
                        .isEqualTo(42)
        );
    }
}
