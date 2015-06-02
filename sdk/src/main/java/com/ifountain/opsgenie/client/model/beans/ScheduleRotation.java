package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ScheduleRotation bean
 */
public class ScheduleRotation implements IBean{
    public enum RotationType{
        weekly,
        daily,
        hourly
    }
    private Date startDate;
    private Date endDate;
    private RotationType rotationType;
    private int rotationLength;
    private List<ScheduleParticipant> participants;
    private List<ScheduleRotationRestriction> restrictions;
    private TimeZone scheduleTimeZone;
    private String name;
    private String id;

    /*
     * Id of the rotation
     */
    public String getId() {
        return id;
    }

    /*
     * Sets the id of the rotation
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of schedule rotation
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule rotation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Start date of schedule rotation
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of schedule rotation
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the optional end date of schedule rotation
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of schedule rotation. Optional.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Rotation type of schedule rotation
     * Could be one of hourly, daily, weekly
     * @see RotationType
     */
    public RotationType getRotationType() {
        return rotationType;
    }

    /**
     * Sets rotation type of schedule rotation
     * Could be one of hourly, daily, weekly
     * @see RotationType
     */
    public void setRotationType(RotationType rotationType) {
        this.rotationType = rotationType;
    }

    /**
     * Rotation length of schedule rotation
     */
    public int getRotationLength() {
        return rotationLength;
    }

    /**
     * Sets rotation length of schedule rotation
     */
    public void setRotationLength(int rotationLength) {
        this.rotationLength = rotationLength;
    }

    /**
     * Participants of of schedule rotation
     * @see ScheduleParticipant
     */
    public List<ScheduleParticipant> getParticipants() {
        return participants;
    }

    /**
     * Sets participants of schedule rotation
     * @see ScheduleParticipant
     */
    public void setParticipants(List<ScheduleParticipant> participants) {
        this.participants = participants;
    }

    /**
     * Restriction list of schedule rotation
     * @see ScheduleRotationRestriction
     */
    public List<ScheduleRotationRestriction> getRestrictions() {
        return restrictions;
    }

    /**
     * Sets restriction list of schedule rotation
     * @see ScheduleRotationRestriction
     */
    public void setRestrictions(List<ScheduleRotationRestriction> restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * Will be set by schedule
     * @param scheduleTimeZone
     */
    public void setScheduleTimeZone(TimeZone scheduleTimeZone) {
        this.scheduleTimeZone = scheduleTimeZone;
    }

    @Override
    public Map toMap() {
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if(scheduleTimeZone != null){
            sdf.setTimeZone(scheduleTimeZone);
        }
        Map<String, Object> json = new HashMap<String, Object>();
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(id != null){
            json.put(OpsGenieClientConstants.API.ID, id);
        }
        json.put(OpsGenieClientConstants.API.START_DATE, sdf.format(startDate));
        json.put(OpsGenieClientConstants.API.ROTATION_TYPE, rotationType.name());
        json.put(OpsGenieClientConstants.API.ROTATION_LENGTH, rotationLength);
        if(participants != null){
            List participantNames = new ArrayList();
            for(ScheduleParticipant participant:participants){
                participantNames.add(participant.getParticipant());
            }
            json.put(OpsGenieClientConstants.API.PARTICIPANTS, participantNames);
        }


        if(restrictions != null){
            List<Map> restrictionMaps = new ArrayList<Map>();
            for(ScheduleRotationRestriction restriction:restrictions){
                restrictionMaps.add(restriction.toMap());
            }
            json.put(OpsGenieClientConstants.API.RESTRICTIONS, restrictionMaps);
        }

        if(endDate != null) {
            json.put(OpsGenieClientConstants.API.END_DATE, sdf.format(endDate));
        }

        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if(scheduleTimeZone != null){
            sdf.setTimeZone(scheduleTimeZone);
        }
        Object startDateObj = null;
        startDateObj = map.get(OpsGenieClientConstants.API.START_DATE);
        if(startDateObj != null){
            if(startDateObj instanceof Date){
                startDate = (Date) startDateObj;
            }
            else{
                String startDateStr = (String) startDateObj;
                startDate = sdf.parse(startDateStr);
            }
        }
        Object endDateObj = map.get(OpsGenieClientConstants.API.END_DATE);
        if(endDateObj != null){
            if(endDateObj instanceof Date){
                endDate = (Date) endDateObj;
            }
            else{
                String endDateStr = (String) endDateObj;
                endDate = sdf.parse(endDateStr);
            }
        }
        rotationType = RotationType.valueOf(((String) map.get(OpsGenieClientConstants.API.ROTATION_TYPE)).toLowerCase());
        if(map.containsKey(OpsGenieClientConstants.API.ROTATION_LENGTH)){
            rotationLength = ((Number) map.get(OpsGenieClientConstants.API.ROTATION_LENGTH)).intValue();
        }
        if(map.containsKey(OpsGenieClientConstants.API.NAME)){
            name = ((String) map.get(OpsGenieClientConstants.API.NAME));
        }
        if(map.containsKey(OpsGenieClientConstants.API.ID)){
            id = ((String) map.get(OpsGenieClientConstants.API.ID));
        }
        if(map.containsKey(OpsGenieClientConstants.API.PARTICIPANTS)){
            List<Map> participantMaps = (List<Map>) map.get(OpsGenieClientConstants.API.PARTICIPANTS);
            participants = new ArrayList<ScheduleParticipant>();
            for( Map participantMap:participantMaps){
                ScheduleParticipant participant = new ScheduleParticipant();
                participant.fromMap(participantMap);
                participants.add(participant);
            }
        }
        if(map.containsKey(OpsGenieClientConstants.API.RESTRICTIONS)){
            List<Map> restrictionMaps = (List<Map>) map.get(OpsGenieClientConstants.API.RESTRICTIONS);
            restrictions = new ArrayList<ScheduleRotationRestriction>();
            for( Map restrictionMap:restrictionMaps){
                ScheduleRotationRestriction scheduleRotationRestriction = new ScheduleRotationRestriction();
                scheduleRotationRestriction.fromMap(restrictionMap);
                restrictions.add(scheduleRotationRestriction);
            }
        }
    }
}
