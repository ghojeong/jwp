package org.apache.coyote.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharSetTest {
    @DisplayName("CharSet 을 String 으로 변환할 수 있다.")
    @Test
    void getString() {
        assertThat(CharSet.UTF8.getString())
                .isEqualTo(";charset=utf-8");
    }
}
