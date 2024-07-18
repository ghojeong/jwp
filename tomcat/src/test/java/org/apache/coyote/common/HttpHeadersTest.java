package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpHeadersTest {

    @DisplayName("Request 의 Header 들을 파싱할 수 있어야 한다.")
    @Test
    void from() {
        final HttpHeaders actual = HttpHeaders.of(
                "Host: localhost:8080 ",
                "Connection: keep-alive ",
                "Content-Length: 42",
                "Cookie: yummy_cookie=choco; tasty_cookie=strawberry; JSESSIONID=656cef62-e3c4-40bc-a8df-94732920ed46"
        );
        assertAll(
                () -> assertThat(actual.get("Host"))
                        .isEqualTo(Optional.of("localhost:8080")),
                () -> assertThat(actual.get("Connection"))
                        .isEqualTo(Optional.of("keep-alive")),
                () -> assertThat(actual.contentLength())
                        .isEqualTo(42),
                () -> assertThat(actual.cookie("yummy_cookie"))
                        .isEqualTo(Optional.of("choco")),
                () -> assertThat(actual.cookie("tasty_cookie"))
                        .isEqualTo(Optional.of("strawberry")),
                () -> assertThat(actual.sessionId())
                        .isEqualTo(Optional.of("656cef62-e3c4-40bc-a8df-94732920ed46"))
        );
    }

    @DisplayName("Session 을 설정할 경우, Header 에 Set-Cookie: JSESSIONID 가 추가되어야 한다.")
    @Test
    void setSession() {
        final HttpHeaders actual = HttpHeaders.of()
                .setSession("sessionId");
        assertThat(actual.getString())
                .isEqualTo("Set-Cookie: JSESSIONID=sessionId");
    }
}
