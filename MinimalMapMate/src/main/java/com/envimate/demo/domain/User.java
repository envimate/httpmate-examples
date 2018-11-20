package com.envimate.demo.domain;

public class User {
    public final Id id;

    private User(Id id) {
        this.id = id;
    }

    public static User user(Id id) {
        return new User(id);
    }
}
