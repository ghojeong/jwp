package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ContentTypeTest {
    @DisplayName("문자열을 기반으로 ContentType 을 변환할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {
            "text/html:TEXT_HTML",
            "text/css:TEXT_CSS",
            "text/xml:TEXT_XML",
            "text/javascript:TEXT_JAVASCRIPT",
            "application/json:APPLICATION_JSON",
    }, delimiter = ':')
    void from(String actual, String expected) {
        assertThat(ContentType.from(actual))
                .isEqualTo(ContentType.valueOf(expected));
    }

    @DisplayName("파일 확장자를 기반으로 ContentType 을 변환할 수 있다.")
    @Test
    void findByFile() {
        assertAll(
                () -> assertThat(
                        ContentType.findByFile(".html")
                ).isEqualTo(ContentType.TEXT_HTML),
                () -> assertThat(
                        ContentType.findByFile(".css")
                ).isEqualTo(ContentType.TEXT_CSS),
                () -> assertThat(
                        ContentType.findByFile(".xml")
                ).isEqualTo(ContentType.TEXT_XML),
                () -> assertThat(
                        ContentType.findByFile(".js")
                ).isEqualTo(ContentType.TEXT_JAVASCRIPT),
                () -> assertThat(
                        ContentType.findByFile(".json")
                ).isEqualTo(ContentType.APPLICATION_JSON)
        );
    }
}
