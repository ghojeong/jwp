package org.apache.coyote.response;

import org.apache.coyote.common.ContentType;
import org.apache.coyote.common.HttpBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpResponseTest {
    @DisplayName("HttpResponse 를 byte 로 변환할 수 있다.")
    @Test
    void getBytes() {
        final String body = "Hello world!";
        final HttpResponse actual = new HttpResponse(
                new HttpBody(ContentType.TEXT_HTML, body)
        );
        assertThat(
                new String(actual.getBytes())
        ).isEqualTo(String.join(
                "\r\n",
                "HTTP/1.1 200 OK",
                "Content-Length: " + body.length(),
                "Content-Type: text/html;charset=utf-8",
                "",
                "Hello world!"
        ));
    }

    @DisplayName("HttpResponse 의 Set-Cookie 를 통해 JSESSIONID 를 설정할 수 있다.")
    @Test
    void setSession() {
        final HttpResponse actual = new HttpResponse(StatusLine.ok());
        actual.setSession("sessionId");
        assertThat(
                actual.getString()
        ).isEqualTo(String.join(
                "\r\n",
                "HTTP/1.1 200 OK",
                "Set-Cookie: JSESSIONID=sessionId",
                "",
                ""
        ));
    }

    @DisplayName("Redirect 를 할 때 302 코드와 Location 을 반환해야 한다.")
    @Test
    void redirect() {
        final String location = "/login";
        assertThat(
                HttpResponse.redirect(location).getString()
        ).isEqualTo(String.join(
                "\r\n",
                "HTTP/1.1 302 Found",
                "Location: " + location,
                "",
                ""
        ));
    }
}
