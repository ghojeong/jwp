package org.apache.coyote.request.request_line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RequestTargetTest {
    @DisplayName("RequestTarget 를 파싱할 수 있어야 한다.")
    @Test
    void from() {
        final RequestTarget actual = RequestTarget.from("/login?account=gugu&password=p@ssW0rd");
        assertAll(
                () -> assertThat(actual.path())
                        .isEqualTo("/login"),
                () -> assertThat(actual.queryParam("account"))
                        .isEqualTo(Optional.of("gugu")),
                () -> assertThat(actual.queryParam("password"))
                        .isEqualTo(Optional.of("p@ssW0rd")),
                () -> assertThat(actual.getString())
                        .isEqualTo("/login?password=p@ssW0rd&account=gugu")
        );
    }
}
