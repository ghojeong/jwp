package org.apache.catalina.session;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionManagerTest {
    private final Manager manager = SessionManager.getInstance();

    private Session session;
    private Session expiredSession;

    @BeforeEach
    void setUp() {
        session = manager.createSession();
        final int expiredTtlSeconds = -1;
        expiredSession = manager.createSession(
                expiredTtlSeconds
        );

    }

    @AfterEach
    void clear() {
        manager.clear();
    }

    @DisplayName("Manager 에 등록된 세션을 조회할 수 있다.")
    @Test
    void findSession() {
        assertAll(
                () -> assertThat(
                        manager.findSession(session.getId())
                ).isEqualTo(Optional.of(session)),
                () -> assertThat(
                        manager.findSession(expiredSession.getId())
                ).isEqualTo(Optional.of(expiredSession))
        );
    }

    @DisplayName("삭제된 세션은 조회할 수 없다.")
    @Test
    void remove() {
        manager.remove(session);
        assertThat(
                manager.findSession(session.getId())
        ).isNotPresent();
    }

    @DisplayName("세션을 새로 등록할 경우, 만료된 세션들이 삭제된다.")
    @Test
    void add() {
        // given
        assertThat(
                manager.findSession(expiredSession.getId())
        ).isPresent();

        // when
        manager.createSession();

        // then
        assertThat(
                manager.findSession(expiredSession.getId())
        ).isNotPresent();
    }
}
