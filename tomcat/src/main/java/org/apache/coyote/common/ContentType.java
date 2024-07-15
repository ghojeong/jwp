package org.apache.coyote.common;

import java.util.Arrays;

public enum ContentType {
    TEXT_PLAIN(".txt", "text/plain"),
    TEXT_HTML(".html", "text/html"),
    TEXT_CSS(".css", "text/css"),
    TEXT_XML(".xml", "text/xml"),
    TEXT_JAVASCRIPT(".js", "text/javascript"),
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
                .findFirst().orElse(TEXT_PLAIN);
    }

    public static ContentType from(String contentType) {
        return Arrays.stream(values())
                .filter(type -> contentType.startsWith(type.contentType))
                .findFirst().orElse(TEXT_PLAIN);
    }

    public String getString() {
        return contentType;
    }
}
