package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * UserRole bean Users can use standard roles (admin-owner-user) or they can create their own custom
 * roles with constructor
 *
 * @author Mehmet Mustafa Demir
 */
public final class UserRole extends Bean {
    public static final UserRole ADMIN = new UserRole("Admin");
    public static final UserRole OWNER = new UserRole("Owner");
    public static final UserRole USER = new UserRole("User");

    private String name;

    private UserRole(String name) {
        this.name = name;
    }

    @JsonCreator
    public static UserRole valueOf(String name) {
        if (ADMIN.getName().equalsIgnoreCase(name)) {
            return ADMIN;
        } else if (OWNER.getName().equalsIgnoreCase(name)) {
            return OWNER;
        } else if (USER.getName().equalsIgnoreCase(name)) {
            return USER;
        }
        return new UserRole(name);
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return name != null ? name.equals(userRole.name) : userRole.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
