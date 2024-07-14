package org.apache.coyote.request.request_line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpPathTest {
    @DisplayName("HttpPath 의 path 와 variable 을 따로 파싱할 수 있어야 한다.")
    @Test
    void of() {
        final HttpPath actual = HttpPath.of(
                "/api/users/23/ghojeong",
                new String[]{"id", "name"}
        );
        assertAll(
                () -> assertThat(actual.path())
                        .isEqualTo("/api/users"),
                () -> assertThat(actual.variable("id"))
                        .isEqualTo("23"),
                () -> assertThat(actual.variable("name"))
                        .isEqualTo("ghojeong")
        );
    }
}
