package org.apache.coyote.handler;

import org.apache.coyote.common.ContentType;
import org.apache.coyote.common.HttpBody;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.request.request_line.HttpMethod;
import org.apache.coyote.response.HttpResponse;
import org.apache.coyote.response.StatusLine;

import static org.apache.coyote.response.HttpStatus.NOT_FOUND;

public final class NotFoundHandler implements HttpHandler {
    private static String location = "";

    private NotFoundHandler() {}

    public static NotFoundHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void setLocation(String location) {
        NotFoundHandler.location = location;
    }

    @Override
    public boolean support(HttpRequest request) {
        return request.matchMethod(HttpMethod.GET);
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        return location.isBlank()
                ? response(request)
                : redirect();
    }

    private HttpResponse redirect() {
        return HttpResponse.redirect(location);
    }

    private HttpResponse response(HttpRequest request) {
        final String lineSeparator = "\r\n\r\n";
        final String body = NOT_FOUND.getString()
                + lineSeparator
                + request.requestLine();
        return new HttpResponse(
                new HttpBody(ContentType.TEXT_PLAIN, body),
                StatusLine.notFound()
        );
    }

    private static class SingletonHolder {
        private static final NotFoundHandler INSTANCE = new NotFoundHandler();
    }
}
