package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpProtocolTest {

    @DisplayName("문자열을 HttpProtocol enum 으로 바꿀 수 있어야 한다.")
    @Test
    void from() {
        assertAll(
                () -> assertThat(HttpProtocol.from("HTTP/1.1"))
                        .isEqualTo(HttpProtocol.HTTP_11),
                () -> assertThat(HttpProtocol.from("HTTP/1.0"))
                        .isEqualTo(HttpProtocol.HTTP_1),
                () -> assertThat(HttpProtocol.from("HTTP/2.0"))
                        .isEqualTo(HttpProtocol.HTTP_2),
                () -> assertThat(HttpProtocol.from("HTTP/3.0"))
                        .isEqualTo(HttpProtocol.HTTP_3)
        );
    }

}
