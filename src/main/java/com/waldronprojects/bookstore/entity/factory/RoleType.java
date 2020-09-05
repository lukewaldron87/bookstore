package com.waldronprojects.bookstore.entity.factory;

public enum RoleType {
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String text;

    RoleType(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
