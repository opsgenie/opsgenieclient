package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ifountain.opsgenie.client.util.JsonUtils;


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

    private Boolean blocked;
    private Boolean verified;
    private String username;
    private String fullName;
    private UserRole role;
    private String skypeUsername;

    private TimeZone timeZone;
    private Locale locale;
    private List<String> tags;
    private UserAddress userAddress;
    private Details details;
    private String createdAt;
    @JsonProperty("userContacts")
    private List<Contact> contacts;

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

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
     * Fullname of user
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets fullname of user
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     * @deprecated Use getUserRole
     */
    @JsonIgnore
    @Deprecated
    public User.Role getRole() {
        return role == null ? null : Role.fromName(role.getName());
    }

    /**
     * Role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.UserRole
     */
    @JsonProperty("role")
    public UserRole getUserRole() {
        return role;
    }

    /**
     * Sets role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.UserRole
     */
    public void setUserRole(UserRole role) {
        this.role = role;
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
     * Contacts of user
     */
    @JsonProperty("userContacts")
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

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public User withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public User withSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
        return this;
    }

    public User withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public User withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public User withRole(UserRole role) {
        this.role = role;
        return this;
    }

    public User withContacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public User withBlocked(Boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public User withVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    public User withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public User withDetails(Details details) {
        this.details = details;
        return this;
    }

    public User withUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
        return this;
    }
    /**
     * Role enum is deprecated in order to give support to custom roles
     * Please use UserRole class
     */
    @Deprecated
    public enum Role {
        admin, owner, user, custom;

        @JsonCreator
        public static Role fromName(String name) {
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(name))
                    return role;
            }
            return custom;
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
