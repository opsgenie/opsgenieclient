package com.ifountain.opsgenie.client.model.beans;

import java.util.List;
import java.util.Objects;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:15 PM
 */
public class Team extends BeanWithId {
    private String name;
    private String description;
    private List<TeamMember> members;

    private Links links;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the team.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Members of the team.
     */
    public List<TeamMember> getMembers() {
        return members;
    }

    /**
     * Sets the members of the team.
     */
    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Team withName(String name) {
        this.name = name;
        return this;
    }

    public Team withDescription(String description) {
        this.description = description;
        return this;
    }

    public Team withMembers(List<TeamMember> members) {
        this.members = members;
        return this;
    }

    public static class TeamMember extends Bean {
        private BaseUserObj user;
        private String role;

        public TeamMember() {
        }

        public TeamMember(BaseUserObj user, String role) {
            this.user = user;
            this.role = role;
        }

        /**
         * Username of the member.
         */
        public BaseUserObj getUser() {
            return user;
        }

        /**
         * Sets the username of the member.
         */
        public void setUser(BaseUserObj user) {
            this.user = user;
        }

        /**
         * Role of the member.
         */
        public String getRole() {
            return role;
        }

        /**
         * Sets the role of the member.
         */
        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            TeamMember that = (TeamMember) o;

            if (!Objects.equals(role, that.role))
                return false;
            if (!Objects.equals(user, that.user))
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = user != null ? user.hashCode() : 0;
            result = 31 * result + (role != null ? role.hashCode() : 0);
            return result;
        }

        public enum Role {
            admin, user
        }
    }
}
