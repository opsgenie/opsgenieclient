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
    private List<String> escalations;
    private List<String> schedules;

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

    public List<String> getEscalations() {
        return escalations;
    }

    public void setEscalations(List<String> escalations) {
        this.escalations = escalations;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
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

    public Team withEscalations(List<String> escalations) {
        this.escalations = escalations;
        return this;
    }

    public Team withSchedules(List<String> schedules) {
        this.schedules = schedules;
        return this;
    }

    public static class TeamMember extends Bean {
        private String user;
        private Role role;

        public TeamMember() {
        }

        public TeamMember(String user) {
            this(user, Role.user);
        }

        public TeamMember(String user, Role role) {
            this.user = user;
            this.role = role;
        }

        /**
         * Username of the member.
         */
        public String getUser() {
            return user;
        }

        /**
         * Sets the username of the member.
         */
        public void setUser(String user) {
            this.user = user;
        }

        /**
         * Role of the member.
         */
        public Role getRole() {
            return role;
        }

        /**
         * Sets the role of the member.
         */
        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            TeamMember that = (TeamMember) o;

            if (role != that.role)
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
    }

}
