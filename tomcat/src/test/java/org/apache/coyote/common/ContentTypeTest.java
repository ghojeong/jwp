package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ContentTypeTest {
    @DisplayName("문자열을 기반으로 ContentType 을 변환할 수 있다.")
    @Test
    void from() {
        assertAll(
                () -> assertThat(
                        ContentType.from("text/html")
                ).isEqualTo(ContentType.TEXT_HTML),
                () -> assertThat(
                        ContentType.from("text/css")
                ).isEqualTo(ContentType.TEXT_CSS),
                () -> assertThat(
                        ContentType.from("text/xml")
                ).isEqualTo(ContentType.TEXT_XML),
                () -> assertThat(
                        ContentType.from("application/javascript")
                ).isEqualTo(ContentType.APPLICATION_JAVASCRIPT),
                () -> assertThat(
                        ContentType.from("application/json")
                ).isEqualTo(ContentType.APPLICATION_JSON)
        );
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
                ).isEqualTo(ContentType.APPLICATION_JAVASCRIPT),
                () -> assertThat(
                        ContentType.findByFile(".json")
                ).isEqualTo(ContentType.APPLICATION_JSON)
        );
    }
}
