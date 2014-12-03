package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:15 PM
 */
public class Team implements IBean {
    private String id;
    private String name;
    private List<TeamMember> members;

    /**
     * Id of the team.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the team.
     */
    public void setId(String id) {
        this.id = id;
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

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NAME, name);
        json.put(OpsGenieClientConstants.API.ID, id);
        if (members != null) {
            List<Map> memberList = new ArrayList<Map>();
            for (TeamMember member : members) {
                memberList.add(member.toMap());
            }
            json.put(OpsGenieClientConstants.API.MEMBERS, memberList);
        }
        return json;
    }

    @Override
    public void fromMap(Map resp) throws ParseException {
        setId((String) resp.get(OpsGenieClientConstants.API.ID));
        setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        List<TeamMember> members = new ArrayList<TeamMember>();
        List<Map> memberList = (List<Map>) resp.get(OpsGenieClientConstants.API.MEMBERS);
        for (Map map : memberList) {
            TeamMember member = new TeamMember();
            member.fromMap(map);
            members.add(member);
        }
        setMembers(members);
    }

    public static class TeamMember implements IBean {
        private String user;
        private Role role;

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

        public static enum Role {
            admin, user
        }

        @Override
        public Map toMap() {
            Map result = new HashMap();
            result.put(OpsGenieClientConstants.API.ROLE, getRole().toString());
            result.put(OpsGenieClientConstants.API.USER, getUser());
            return result;
        }

        @Override
        public void fromMap(Map map) throws ParseException {
            setUser((String) map.get(OpsGenieClientConstants.API.USER));
            setRole(Role.valueOf((String) map.get(OpsGenieClientConstants.API.USER)));
        }

    }


}
