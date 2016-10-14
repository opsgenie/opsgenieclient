package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.util.JsonUtils;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.*;

/**
 * User bean
 */
public class User extends BeanWithId {
    private static final Map<String, Locale> LOCALES = new HashMap<String, Locale>();

    static {
        for (Locale locale : Locale.getAvailableLocales()) {
            LOCALES.put(getLocaleId(locale), locale);
        }
    }

    private String username;
    private State state;
    private String fullname;
    private String skypeUsername;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private Locale locale;
    private Role role;
    private List<String> groups;
    private List<String> escalations;
    private List<String> schedules;
    private List<Contact> contacts;

    public static String getLocaleId(Locale locale) {
        return locale.toString();
    }

    public static Locale getLocale(String localeId) {
        return LOCALES.get(localeId);
    }

    /**
     * Username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * State of user
     *
     * @see State
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state of user
     *
     * @see State
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Fullname of user
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets fullname of user
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Timezone of user
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone of user
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Locale of user
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets locale of user
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public User.Role getRole() {
        return role;
    }

    /**
     * Sets role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public void setRole(User.Role role) {
        this.role = role;
    }

    /**
     * Groups of user
     */
    public List<String> getGroups() {
        return groups;
    }

    /**
     * Sets groups of user
     */
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    /**
     * Escalations of user
     */
    public List<String> getEscalations() {
        return escalations;
    }

    /**
     * Sets escalations of user
     */
    public void setEscalations(List<String> escalations) {
        this.escalations = escalations;
    }

    /**
     * Schedules of user
     */
    public List<String> getSchedules() {
        return schedules;
    }

    /**
     * Sets schedules of user
     */
    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }

    /**
     * @deprecated Use getUserContacts
     */
    @Deprecated
    @JsonIgnore
    public List<Map<String, String>> getContacts() {
        if (contacts == null)
            return null;
        List<Map<String, String>> contactMapList = new ArrayList();
        for (Contact contact : contacts) {
            try {
                contactMapList.add(JsonUtils.toMap(contact));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contactMapList;
    }

    /**
     * @deprecated Use setUserContacts
     */
    @Deprecated
    public void setContacts(List<Map<String, String>> contacts) {
        if (contacts != null) {
            this.contacts = new ArrayList<Contact>();
            for (Map<String, String> map : contacts) {
                Contact contact = new Contact();
                try {
                    JsonUtils.fromMap(contact, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.contacts.add(contact);
            }
        } else {
            this.contacts = null;
        }
    }

    /**
     * Contacts of user
     */
    @JsonProperty("contacts")
    public List<Contact> getUserContacts() {
        return contacts;
    }

    /**
     * Sets contacts of user
     */
    public void setUserContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getSkypeUsername() {
        return skypeUsername;
    }

    public void setSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
    }

    public enum Role {
        admin, owner, user;

        @JsonCreator
        public static Role fromName(String name) {
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(name))
                    return role;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return name();
        }
    }

    public enum State {
        active, waitingverification, inactive
    }

}
