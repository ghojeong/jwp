package org.apache.coyote.common;

import java.util.Arrays;

public enum ContentType {
    TEXT_PLAIN(".txt", "text/plain"),
    TEXT_HTML(".html", "text/html"),
    TEXT_CSS(".css", "text/css"),
    TEXT_XML(".xml", "text/xml"),
    APPLICATION_JAVASCRIPT(".js", "application/javascript"),
    APPLICATION_JSON(".json", "application/json");
    private final String fileExtension;
    private final String contentType;

    ContentType(String fileExtension, String contentType) {
        this.fileExtension = fileExtension;
        this.contentType = contentType;
    }

    public static ContentType findByFile(String fileExtension) {
        return Arrays.stream(values())
                .filter(type -> fileExtension.endsWith(type.fileExtension))
                .findAny().orElse(TEXT_PLAIN);
    }

    public static ContentType from(String contentType) {
        return Arrays.stream(values())
                .filter(type -> contentType.startsWith(type.contentType))
                .findAny().orElse(TEXT_PLAIN);
    }

    @Override
    public String toString() {
        final String UTF_CHARSET = ";charset=utf-8";
        return contentType + UTF_CHARSET;
    }
}
