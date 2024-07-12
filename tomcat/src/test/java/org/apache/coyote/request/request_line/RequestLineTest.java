package org.apache.coyote.request.request_line;

import org.apache.coyote.common.HttpProtocol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RequestLineTest {

    @DisplayName("RequestLine 을 파싱할 수 있어야 한다.")
    @Test
    void from() {
        final RequestLine actual = RequestLine.from("POST /login?account=gugu&password=p@ssW0rd HTTP/1.1");
        assertAll(
                () -> assertThat(actual.matchMethod(HttpMethod.POST))
                        .isTrue(),
                () -> assertThat(actual.path())
                        .isEqualTo("/login"),
                () -> assertThat(actual.queryParam("account"))
                        .isEqualTo(Optional.of("gugu")),
                () -> assertThat(actual.queryParam("password"))
                        .isEqualTo(Optional.of("p@ssW0rd")),
                () -> assertThat(actual.protocol())
                        .isEqualTo(HttpProtocol.HTTP_11),
                () -> assertThat(actual.toString())
                        .isEqualTo("POST /login?password=p@ssW0rd&account=gugu HTTP/1.1")
        );
    }
}
