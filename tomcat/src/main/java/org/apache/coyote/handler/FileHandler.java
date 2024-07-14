package org.apache.coyote.handler;

import org.apache.coyote.common.ContentType;
import org.apache.coyote.common.HttpBody;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.request.request_line.HttpMethod;
import org.apache.coyote.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public final class FileHandler implements HttpHandler {
    private static final Logger logger = LoggerFactory.getLogger(FileHandler.class);

    private FileHandler() {}

    public static FileHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean support(HttpRequest request) {
        if (!request.matchMethod(HttpMethod.GET)) {
            return false;
        }
        getClass().getClassLoader();
        return ClassLoader.getSystemResource(
                resourcePath(request)
        ) != null;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        final HttpBody body = readResource(request);
        return new HttpResponse(body);
    }

    private InputStream getResourceStream(HttpRequest request) {
        return getClass().getClassLoader()
                .getResourceAsStream(resourcePath(request));
    }

    private String resourcePath(HttpRequest request) {
        final String BASE_PATH = "static";
        return BASE_PATH + request.path();
    }

    private HttpBody readResource(HttpRequest request) {
        try (InputStream inputStream = getResourceStream(request)) {
            return new HttpBody(
                    ContentType.findByFile(request.path()),
                    inputStream.readAllBytes()
            );
        } catch (IOException exception) {
            logger.error(exception.getMessage(), exception);
            return HttpBody.emptyInstance();
        }
    }

    private static class SingletonHolder {
        private static final FileHandler INSTANCE = new FileHandler();
    }
}
