package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

/**
 * User bean
 */
public class User extends BaseUserObj {
    private static final Map<String, Locale> LOCALES = new HashMap<String, Locale>();

    static {
        for (Locale locale : Locale.getAvailableLocales()) {
            LOCALES.put(getLocaleId(locale), locale);
        }
    }

    private Boolean blocked;
    private Boolean verified;
    private String fullName;
    private UserRole role;
    private String skypeUsername;

    private TimeZone timeZone;
    private Locale locale;
    private List<String> tags;
    private UserAddress userAddress;
    private Map<String, List<String>> details;
    private String createdAt;
    @JsonProperty("userContacts")
    private List<Contact> contacts;
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

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
    public UserRole getRole() {
        return role;
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

    public Map<String, List<String>> getDetails() {
        return details;
    }

    public void setDetails(Map<String, List<String>> details) {
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

    public String getSkypeUsername() {
        return skypeUsername;
    }

    public void setSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
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

    public User withDetails(Map<String, List<String>> details) {
        this.details = details;
        return this;
    }

    public User withUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
        return this;
    }
}
