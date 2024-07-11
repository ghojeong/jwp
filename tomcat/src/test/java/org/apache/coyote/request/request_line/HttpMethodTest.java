package org.apache.coyote.request.request_line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpMethodTest {

    @DisplayName("문자열을 HttpMethod enum 으로 바꿀 수 있어야 한다.")
    @Test
    void from() {
        assertAll(
                () -> assertThat(HttpMethod.from("GET"))
                        .isEqualTo(HttpMethod.GET),
                () -> assertThat(HttpMethod.from("POST"))
                        .isEqualTo(HttpMethod.POST),
                () -> assertThat(HttpMethod.from("PUT"))
                        .isEqualTo(HttpMethod.PUT),
                () -> assertThat(HttpMethod.from("DELETE"))
                        .isEqualTo(HttpMethod.DELETE),
                () -> assertThat(HttpMethod.from("HEAD"))
                        .isEqualTo(HttpMethod.HEAD),
                () -> assertThat(HttpMethod.from("TRACE"))
                        .isEqualTo(HttpMethod.TRACE),
                () -> assertThat(HttpMethod.from("OPTIONS"))
                        .isEqualTo(HttpMethod.OPTIONS),
                () -> assertThat(HttpMethod.from("PATCH"))
                        .isEqualTo(HttpMethod.PATCH)
        );
    }
}
