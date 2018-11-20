package com.envimate.demo.domain;

public class User {
    public final Id id;

    public User(Id id) {
        this.id = id;
    }

    public static User create(Id id) {
        return new User(id);

    }
}
