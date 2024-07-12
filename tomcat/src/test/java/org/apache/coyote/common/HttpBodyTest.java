package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpBodyTest {
    @DisplayName("HttpBody 를 byte 로 변환할 수 있어야 한다.")
    @Test
    void testToString() {
        assertAll(
                () -> assertThat(HttpBody.emptyInstance().getBytes())
                        .isEqualTo(new byte[]{}),
                () -> assertThat(
                        new HttpBody(ContentType.TEXT_PLAIN, "Hello world!").toString()
                ).isEqualTo("Hello world!")
        );
    }
}
