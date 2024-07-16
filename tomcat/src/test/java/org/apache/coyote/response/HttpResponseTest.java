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
}
