package org.apache.coyote.request.request_line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class HttpMethodTest {
    @DisplayName("문자열을 HttpMethod enum 으로 바꿀 수 있어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "GET:GET",
            "POST:POST",
            "PUT:PUT",
            "DELETE:DELETE",
            "PATCH:PATCH",
            "HEAD:HEAD",
            "TRACE:TRACE",
            "OPTIONS:OPTIONS",
    }, delimiter = ':')
    void from(String actual, String expected) {
        assertThat(HttpMethod.from(actual))
                .isEqualTo(HttpMethod.valueOf(expected));
    }
}
