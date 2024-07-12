package org.apache.coyote.handler;

import org.apache.coyote.common.ContentType;
import org.apache.coyote.common.HttpBody;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.request.request_line.HttpMethod;
import org.apache.coyote.response.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public final class FileHandler implements HttpHandler {
    private FileHandler() {}

    public static FileHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean support(HttpRequest request) {
        return request.matchMethod(HttpMethod.GET)
                && getResource(request) != null;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        final HttpBody body = readResource(request);
        return new HttpResponse(body);
    }

    private URL getResource(HttpRequest request) {
        final String BASE_PATH = "static";
        return getClass().getClassLoader()
                .getResource(BASE_PATH + request.path());
    }

    private HttpBody readResource(HttpRequest request) {
        try {
            return new HttpBody(
                    ContentType.findByFile(request.path()),
                    Files.readAllBytes(new File(
                            getResource(request).getFile()
                    ).toPath())
            );
        } catch (IOException exception) {
            return HttpBody.emptyInstance();
        }
    }

    private static class SingletonHolder {
        private static final FileHandler INSTANCE = new FileHandler();
    }
}
