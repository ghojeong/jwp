package org.apache.coyote.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class HttpStatusTest {

    @DisplayName("HttpStatus 를 문자열로 바꿀 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {
            "OK:200 OK",
            "CREATED:201 Created",
            "FOUND:302 Found",
            "BAD_REQUEST:400 Bad Request",
            "UNAUTHORIZED:401 Unauthorized",
            "FORBIDDEN:403 Forbidden",
            "NOT_FOUND:404 Not Found",
            "INTERNAL_SERVER_ERROR:500 Internal Server Error",
    }, delimiter = ':')
    void from(String actual, String expected) {
        assertThat(HttpStatus.valueOf(actual).getString())
                .isEqualTo(expected);
    }
}
