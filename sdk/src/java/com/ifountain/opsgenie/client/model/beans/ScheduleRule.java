package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ScheduleRule bean
 */
public class ScheduleRule  implements IBean{
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
     * Rotation length of schedule rule
     */
    public int getRotationLength() {
        return rotationLength;
    }

    /**
     * Sets rotation length of schedule rule
     */
    public void setRotationLength(int rotationLength) {
        this.rotationLength = rotationLength;
    }

    /**
     * Participants of of schedule rule
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
        json.put(OpsGenieClientConstants.API.START_TIME, sdf.format(startDate));
        json.put(OpsGenieClientConstants.API.ROTATION_TYPE, rotationType.name());
        json.put(OpsGenieClientConstants.API.ROTATION_LENGTH, rotationLength);
        if(participants != null){
            List participantNames = new ArrayList();
            for(ScheduleParticipant participant:participants){
                participantNames.add(participant.getName());
            }
            json.put(OpsGenieClientConstants.API.PARTICIPANTS, participantNames);
        }

        if(restrictions != null){
            List<Map> restrictionMaps = new ArrayList<Map>();
            for(ScheduleRuleRestriction restriction:restrictions){
                restrictionMaps.add(restriction.toMap());
            }
            json.put(OpsGenieClientConstants.API.RESTRICTIONS, restrictionMaps);
        }

        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if(scheduleTimeZone != null){
            sdf.setTimeZone(scheduleTimeZone);
        }
        Object startDateObj = map.get(OpsGenieClientConstants.API.START_TIME);
        if(startDateObj != null){
            if(startDateObj instanceof Date){
                startDate = (Date) startDateObj;
            }
            else{
                String startDateStr = (String) map.get(OpsGenieClientConstants.API.START_TIME);
                startDate = sdf.parse(startDateStr);
            }
        }
        rotationType = RotationType.valueOf(((String) map.get(OpsGenieClientConstants.API.ROTATION_TYPE)).toLowerCase());
        if(map.containsKey(OpsGenieClientConstants.API.ROTATION_LENGTH)){
            rotationLength = ((Number) map.get(OpsGenieClientConstants.API.ROTATION_LENGTH)).intValue();
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
            restrictions = new ArrayList<ScheduleRuleRestriction>();
            for( Map restrictionMap:restrictionMaps){
                ScheduleRuleRestriction scheduleRuleRestriction = new ScheduleRuleRestriction();
                scheduleRuleRestriction.fromMap(restrictionMap);
                restrictions.add(scheduleRuleRestriction);
            }
        }
    }
}
