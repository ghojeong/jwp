package org.apache.coyote.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpStatusTest {

    @DisplayName("HttpStatus 를 문자열로 바꿀 수 있다.")
    @Test
    void testgetString() {
        assertAll(
                () -> assertThat(HttpStatus.OK.getString())
                        .isEqualTo("200 OK"),
                () -> assertThat(HttpStatus.CREATED.getString())
                        .isEqualTo("201 Created"),
                () -> assertThat(HttpStatus.FOUND.getString())
                        .isEqualTo("302 Found"),
                () -> assertThat(HttpStatus.BAD_REQUEST.getString())
                        .isEqualTo("400 Bad Request"),
                () -> assertThat(HttpStatus.UNAUTHORIZED.getString())
                        .isEqualTo("401 Unauthorized"),
                () -> assertThat(HttpStatus.FORBIDDEN.getString())
                        .isEqualTo("403 Forbidden"),
                () -> assertThat(HttpStatus.NOT_FOUND.getString())
                        .isEqualTo("404 Not Found"),
                () -> assertThat(HttpStatus.INTERNAL_SERVER_ERROR.getString())
                        .isEqualTo("500 Internal Server Error")
        );
    }
}
