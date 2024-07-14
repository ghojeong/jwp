package org.apache.coyote.request.request_line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QueryParamsTest {

    @DisplayName("QueryParams 문자열을 파싱할 수 있어야 한다.")
    @Test
    void from() {
        final QueryParams actual = QueryParams.from(
                "account=gugu&password=p@ssW0rd"
        );
        assertAll(
                () -> assertThat(actual.get("account"))
                        .isEqualTo(Optional.of("gugu")),
                () -> assertThat(actual.get("password"))
                        .isEqualTo(Optional.of("p@ssW0rd")),
                () -> assertThat(actual.getString())
                        .isEqualTo("password=p@ssW0rd&account=gugu")
        );
    }
}
