package com.skynest.uitesting.api;

public enum Roles {

    WORKER("role_worker"),
    MANAGER("role_manager"),
    ADMIN("role_admin");

    private final String string;

    Roles(String text) {
        this.string = text;
    }

    public String getString() {
        return string;
    }
}
