package com.envimate.demo.domain;

public class Id {
    private final String value;

    private Id(final String value) {
        this.value = value;
    }

    public static Id id(String value) {
        return new Id(value);
    }

    public String getValue() {
        return this.value;
    }
}
