package app.controller;

import org.apache.coyote.exception.MethodNotAllowedException;
import org.apache.coyote.handler.HttpHandler;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

import static org.apache.coyote.request.request_line.HttpMethod.GET;
import static org.apache.coyote.request.request_line.HttpMethod.POST;

public abstract class Controller implements HttpHandler {
    protected abstract String path();

    protected abstract HttpResponse doPost(HttpRequest request);

    protected abstract HttpResponse doGet(HttpRequest request);

    @Override
    public HttpResponse handle(HttpRequest request) {
        if (request.matchMethod(GET)) {
            return doGet(request);
        }
        if (request.matchMethod(POST)) {
            return doPost(request);
        }
        throw new MethodNotAllowedException(request);
    }

    @Override
    public boolean support(HttpRequest request) {
        return request.path().equals(path());
    }
}
