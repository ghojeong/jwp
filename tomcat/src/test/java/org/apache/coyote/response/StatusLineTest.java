package org.apache.coyote.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusLineTest {
    @DisplayName("StatusLine 을 문자열로 변환할 수 있다.")
    @Test
    void testToString() {
        assertThat(StatusLine.ok().toString())
                .isEqualTo("HTTP/1.1 200 OK");
    }
}
