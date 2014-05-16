package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ScheduleRule bean
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 */
public class ScheduleRule  implements IBean {
    public enum RotationType{
        weekly,
        daily,
        hourly
    }
    private Date startDate;
    private RotationType rotationType;
    private int rotationLength;
    private List<ScheduleParticipant> participants;
    private List<ScheduleRuleRestriction> restrictions;
    private TimeZone scheduleTimeZone;

    /**
     * Start date of schedule rule
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of schedule rule
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Rotation type of schedule rule
     * Could be one of hourly, daily, weekly
     * @see RotationType
     */
    public RotationType getRotationType() {
        return rotationType;
    }

    /**
     * Sets rotation type of schedule rule
     * Could be one of hourly, daily, weekly
     * @see RotationType
     */
    public void setRotationType(RotationType rotationType) {
        this.rotationType = rotationType;
    }

    /**
     * Rotation length of custom schedule rule
     * Splits shifts between users according to the specified rotation length.
     */
    public int getRotationLength() {
        return rotationLength;
    }

    /**
     * Sets rotation length of custom schedule rule
     * Splits shifts between users according to the specified rotation length.
     */
    public void setRotationLength(int rotationLength) {
        this.rotationLength = rotationLength;
    }

    /**
     * Participants of schedule rule
     * @see ScheduleParticipant
     */
    public List<ScheduleParticipant> getParticipants() {
        return participants;
    }

    /**
     * Sets participants of schedule rule
     * @see ScheduleParticipant
     */
    public void setParticipants(List<ScheduleParticipant> participants) {
        this.participants = participants;
    }

    /**
     * Restriction list of schedule rule
     * @see ScheduleRuleRestriction
     */
    public List<ScheduleRuleRestriction> getRestrictions() {
        return restrictions;
    }

    /**
     * Sets restriction list of schedule rule
     * @see ScheduleRuleRestriction
     */
    public void setRestrictions(List<ScheduleRuleRestriction> restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * Will be set by schedule
     */
    public void setScheduleTimeZone(TimeZone scheduleTimeZone) {
        this.scheduleTimeZone = scheduleTimeZone;
    }

    @Override
    public Map<String,Object> toMap() {
        SimpleDateFormat sdf = new SimpleDateFormat(ClientConstants.Common.API_DATE_FORMAT);
        if(scheduleTimeZone != null){
            sdf.setTimeZone(scheduleTimeZone);
        }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ClientConstants.API.START_TIME, sdf.format(startDate));
        json.put(ClientConstants.API.ROTATION_TYPE, rotationType.name());
        json.put(ClientConstants.API.ROTATION_LENGTH, rotationLength);
        if(participants != null){
            List<String> participantNames = new ArrayList<String>();
            for(ScheduleParticipant participant:participants){
                participantNames.add(participant.getParticipant());
            }
            json.put(ClientConstants.API.PARTICIPANTS, participantNames);
        }

        if(restrictions != null){
            List<Map> restrictionMaps = new ArrayList<Map>();
            for(ScheduleRuleRestriction restriction:restrictions){
                restrictionMaps.add(restriction.toMap());
            }
            json.put(ClientConstants.API.RESTRICTIONS, restrictionMaps);
        }

        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(ClientConstants.Common.API_DATE_FORMAT);
        if(scheduleTimeZone != null){
            sdf.setTimeZone(scheduleTimeZone);
        }
        //TODO:we need to fix inconsistency in add schedule and get schedule collector actions
        Object startDateObj = null;
        if(map.containsKey(ClientConstants.API.START_DATE)){
            startDateObj = map.get(ClientConstants.API.START_DATE);
        }
        else{
            startDateObj = map.get(ClientConstants.API.START_TIME);
        }
        if(startDateObj != null){
            if(startDateObj instanceof Date){
                startDate = (Date) startDateObj;
            }
            else{
                String startDateStr = (String) startDateObj;
                startDate = sdf.parse(startDateStr);
            }
        }
        rotationType = RotationType.valueOf(((String) map.get(ClientConstants.API.ROTATION_TYPE)).toLowerCase());
        if(map.containsKey(ClientConstants.API.ROTATION_LENGTH)){
            rotationLength = ((Number) map.get(ClientConstants.API.ROTATION_LENGTH)).intValue();
        }
        if(map.containsKey(ClientConstants.API.PARTICIPANTS)){
            List<Map<String,Object>> participantMaps = (List<Map<String,Object>>) map.get(ClientConstants.API.PARTICIPANTS);
            participants = new ArrayList<ScheduleParticipant>();
            for( Map<String,Object> participantMap:participantMaps){
                ScheduleParticipant participant = new ScheduleParticipant();
                participant.fromMap(participantMap);
                participants.add(participant);
            }
        }
        if(map.containsKey(ClientConstants.API.RESTRICTIONS)){
            List<Map<String,Object>> restrictionMaps = (List<Map<String,Object>>) map.get(ClientConstants.API.RESTRICTIONS);
            restrictions = new ArrayList<ScheduleRuleRestriction>();
            for( Map<String,Object> restrictionMap:restrictionMaps){
                ScheduleRuleRestriction scheduleRuleRestriction = new ScheduleRuleRestriction();
                scheduleRuleRestriction.fromMap(restrictionMap);
                restrictions.add(scheduleRuleRestriction);
            }
        }
    }
}
