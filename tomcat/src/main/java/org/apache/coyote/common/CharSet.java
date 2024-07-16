package org.apache.coyote.common;

public enum CharSet {
    UTF8("utf-8");

    private final String charSet;

    CharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getString() {
        final String PREFIX = ";charset=";
        return PREFIX + charSet;
    }
}
