package app.controller;

import org.apache.coyote.exception.MethodNotAllowedException;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

public class LoginController extends Controller {
    @Override
    protected String path() {
        return "/login";
    }

    @Override
    protected HttpResponse doPost(HttpRequest request) {
        throw new MethodNotAllowedException(request);
    }

    @Override
    protected HttpResponse doGet(HttpRequest request) {
        return HttpResponse.redirect("/login.html");
    }
}
