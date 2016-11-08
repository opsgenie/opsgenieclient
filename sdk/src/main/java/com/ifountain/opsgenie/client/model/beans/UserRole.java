package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.annotate.JsonValue;

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

    public UserRole(String name) {
        this.name = name;
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
