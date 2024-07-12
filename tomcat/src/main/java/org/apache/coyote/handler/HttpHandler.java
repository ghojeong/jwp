package org.apache.coyote.handler;

import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

public interface HttpHandler {
    boolean support(HttpRequest request);
    
    HttpResponse handle(HttpRequest request);
}
