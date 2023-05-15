package com.ifountain.opsgenie.client.model.beans;

import java.util.Objects;

/**
 * UserRole bean Users can use standard roles (admin-owner-user) or they can create their own custom
 * roles with constructor
 *
 * @author Mehmet Mustafa Demir
 */
public class UserRole extends BeanWithId {

    private String name;

    private UserRole(String name) {
        this.name = name;
    }

    public UserRole(){

    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
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

        return Objects.equals(name, userRole.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
