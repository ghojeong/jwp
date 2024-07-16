package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class HttpProtocolTest {

    @DisplayName("문자열을 HttpProtocol enum 으로 바꿀 수 있어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "HTTP/1.1:HTTP_11",
            "HTTP/1.0:HTTP_1",
            "HTTP/2.0:HTTP_2",
            "HTTP/3.0:HTTP_3",
    }, delimiter = ':')
    void from(String actual, String expected) {
        assertThat(HttpProtocol.from(actual))
                .isEqualTo(HttpProtocol.valueOf(expected));
    }

}
