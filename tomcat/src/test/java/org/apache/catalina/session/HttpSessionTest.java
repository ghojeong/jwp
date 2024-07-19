package org.apache.catalina.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

final class HttpSessionTest {
    private String sessionId;

    @BeforeEach
    void setUp() {
        sessionId = randomUUID().toString();
    }

    @DisplayName("세션 생성시 sessionId 와 expiryAt 이 할당되어야 한다.")
    @Test
    void getId() {
        final Session session = new HttpSession(sessionId);
        assertAll(
                () -> assertThat(session.getId())
                        .isEqualTo(sessionId),
                () -> assertThat(session.isExpired())
                        .isFalse()
        );
    }

    @DisplayName("세션이 만료되었다면 isExpired 가 false 이어야 한다.")
    @Test
    void isExpired() {
        final int ttlSeconds = -1;
        assertThat(new HttpSession(
                sessionId, ttlSeconds
        ).isExpired()).isTrue();
    }

    @Test
    void getAttribute() {
    }

    @DisplayName("세션을 통해 설정한 attribute 를 가져올 수 있어야 한다.")
    @Test
    void setAttribute() {
        // given
        final Session session = new HttpSession(sessionId);
        assertThat(
                session.getAttribute(User.KEY)
        ).isNull();
        final User user = new User(
                "name", "email@a.com"
        );

        // when
        session.setAttribute(User.KEY, user);

        // then
        assertThat(
                session.getAttribute(User.KEY)
        ).isEqualTo(user);
    }

    @DisplayName("세션에서 삭제된 attribute 는 가져올 수 없다.")
    @Test
    void removeAttribute() {
        // given
        final Session session = new HttpSession(sessionId);
        session.setAttribute(User.KEY, new User(
                "name", "email@a.com"
        ));

        // when
        session.removeAttribute(User.KEY);

        // then
        assertThat(
                session.getAttribute(User.KEY)
        ).isNull();
    }

    @DisplayName("만료된 세션에서 attribute 를 가져올 수 없다.")
    @Test
    void invalidate() {
        // given
        final int ttlSeconds = -1;
        final Session session = new HttpSession(
                sessionId, ttlSeconds
        );
        session.setAttribute(User.KEY, new User(
                "name", "email@a.com"
        ));

        // when
        session.invalidate();

        // then
        assertThat(
                session.getAttribute(User.KEY)
        ).isNull();
    }


    private record User(
            String name,
            String email
    ) {
        private static final String KEY = "User";
    }
}
