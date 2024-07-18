package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CookiesTest {
    @DisplayName("cookies 문자열을 파싱할 수 있어야 한다.")
    @Test
    void from() {
        final Cookies actual = Cookies.from(
                "yummy_cookie=choco; tasty_cookie=strawberry; JSESSIONID=656cef62-e3c4-40bc-a8df-94732920ed46"
        );
        assertAll(
                () -> assertThat(actual.get("yummy_cookie"))
                        .isEqualTo(Optional.of("choco")),
                () -> assertThat(actual.get("tasty_cookie"))
                        .isEqualTo(Optional.of("strawberry")),
                () -> assertThat(actual.get("JSESSIONID"))
                        .isEqualTo(Optional.of("656cef62-e3c4-40bc-a8df-94732920ed46")),
                () -> assertThat(actual.getString())
                        .isEqualTo("yummy_cookie=choco;JSESSIONID=656cef62-e3c4-40bc-a8df-94732920ed46;tasty_cookie=strawberry")
        );
    }
}
