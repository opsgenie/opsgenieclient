package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.util.*;

/**
 * User bean
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 */
public class User  implements IBean {
    private static final Map<String, Locale> LOCALES = new HashMap<String, Locale>();
    static {
        for(Locale locale:Locale.getAvailableLocales()){
            LOCALES.put(getLocaleId(locale), locale);
        }
    }

    public static String getLocaleId(Locale locale){
        return locale.toString();
    }
    public static Locale getLocale(String localeId){
        return LOCALES.get(localeId);
    }
    public static enum Role{
        admin,
        owner,
        user
    }
    public static enum State{
        active,
        inactive
    }
    private String id;
    private String username;
    private State state;
    private String fullname;
    private TimeZone timeZone;
    private Locale locale;
    private Role role;
    private List<String> groups;
    private List<String> escalations;
    private List<String> schedules;
    private List<Map<String,String>> contacts;

    /**
     * Id of user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user
     */
    public void setId(String id) {
        this.id = id;
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
     * @see State
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state of user
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
     * @see com.ifountain.client.opsgenie.model.beans.User.Role
     */
    public User.Role getRole() {
        return role;
    }

    /**
     * Sets role of user
     * @see com.ifountain.client.opsgenie.model.beans.User.Role
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
     * Contacts of user
     */
    public List<Map<String, String>> getContacts() {
        return contacts;
    }

    /**
     * Sets contacts of user
     */
    public void setContacts(List<Map<String, String>> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String,Object> json = new HashMap<String,Object>();
        json.put(ClientConstants.API.ID, getId());
        json.put(ClientConstants.API.USERNAME, getUsername());
        json.put(ClientConstants.API.FULLNAME, getFullname());
        if(getState() != null){
            json.put(ClientConstants.API.STATE, getState().name());
        }
        if(getRole() != null){
            json.put(ClientConstants.API.ROLE, getRole().name());
        }
        if(getTimeZone() != null){
            json.put(ClientConstants.API.TIMEZONE, getTimeZone().getID());
        }
        if(getLocale() != null){
            json.put(ClientConstants.API.LOCALE, getLocaleId(locale));
        }
        if(getGroups() != null){
            json.put(ClientConstants.API.GROUPS, getGroups());
        }
        if(getEscalations() != null){
            json.put(ClientConstants.API.ESCALATIONS, getEscalations());
        }
        if(getSchedules() != null){
            json.put(ClientConstants.API.SCHEDULES, getSchedules());
        }
        if(getContacts() != null){
            json.put(ClientConstants.API.CONTACTS, getContacts());
        }
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        setId((String) map.get(ClientConstants.API.ID));
        setUsername((String) map.get(ClientConstants.API.USERNAME));
        setFullname((String) map.get(ClientConstants.API.FULLNAME));
        if(map.containsKey(ClientConstants.API.ROLE)){
            setRole(User.Role.valueOf(((String) map.get(ClientConstants.API.ROLE)).toLowerCase()));
        }
        if(map.containsKey(ClientConstants.API.STATE)){
            setState(State.valueOf(((String) map.get(ClientConstants.API.STATE)).toLowerCase()));
        }
        setGroups((List<String>) map.get(ClientConstants.API.GROUPS));
        setEscalations((List<String>) map.get(ClientConstants.API.ESCALATIONS));
        setSchedules((List<String>) map.get(ClientConstants.API.SCHEDULES));
        setContacts((List<Map<String, String>>) map.get(ClientConstants.API.CONTACTS));
        if(map.get(ClientConstants.API.TIMEZONE) != null){
            Object timezoneObj = map.get(ClientConstants.API.TIMEZONE);
            if(timezoneObj instanceof TimeZone){
                setTimeZone((TimeZone) timezoneObj);
            }
            else{
                setTimeZone(TimeZone.getTimeZone(String.valueOf(timezoneObj)));
            }
        }
        if(map.get(ClientConstants.API.LOCALE) != null){
            Object localeObj = map.get(ClientConstants.API.LOCALE);
            if(localeObj instanceof Locale){
                setLocale((Locale) localeObj);
            }
            else{
                setLocale(User.getLocale(String.valueOf(localeObj)));
            }
        }
    }
}
