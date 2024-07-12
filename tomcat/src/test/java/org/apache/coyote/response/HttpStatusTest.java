package org.apache.coyote.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpStatusTest {

    @DisplayName("HttpStatus 를 문자열로 바꿀 수 있다.")
    @Test
    void testToString() {
        assertAll(
                () -> assertThat(HttpStatus.OK.toString())
                        .isEqualTo("200 OK"),
                () -> assertThat(HttpStatus.CREATED.toString())
                        .isEqualTo("201 Created"),
                () -> assertThat(HttpStatus.FOUND.toString())
                        .isEqualTo("302 Found"),
                () -> assertThat(HttpStatus.BAD_REQUEST.toString())
                        .isEqualTo("400 Bad Request"),
                () -> assertThat(HttpStatus.UNAUTHORIZED.toString())
                        .isEqualTo("401 Unauthorized"),
                () -> assertThat(HttpStatus.FORBIDDEN.toString())
                        .isEqualTo("403 Forbidden"),
                () -> assertThat(HttpStatus.NOT_FOUND.toString())
                        .isEqualTo("404 Not Found"),
                () -> assertThat(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .isEqualTo("500 Internal Server Error")
        );
    }
}
