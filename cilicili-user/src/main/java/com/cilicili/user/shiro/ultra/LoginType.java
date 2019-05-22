package com.cilicili.user.shiro.ultra;

public enum LoginType {
	
    USER("Users"),  ADMIN("AdminUser");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}

