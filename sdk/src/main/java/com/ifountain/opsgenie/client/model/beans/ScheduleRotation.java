package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * ScheduleRotation bean
 */
public class ScheduleRotation extends Bean implements ObjectWithTimeZone {
	public enum RotationType {
		weekly, daily, hourly
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

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
	 * Rotation type of schedule rotation Could be one of hourly, daily, weekly
	 *
	 * @see RotationType
	 */
	public RotationType getRotationType() {
		return rotationType;
	}

	/**
	 * Sets rotation type of schedule rotation Could be one of hourly, daily,
	 * weekly
	 *
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
	 * Participants of schedule rotation
	 *
	 * @see ScheduleParticipant
	 */
	@JsonProperty("participants")
	public List<String> getParticipantsNames() {
		if (participants == null)
			return null;
		List<String> participantList = new ArrayList<String>();
		for (ScheduleParticipant participant : participants)
			participantList.add(participant.getParticipant());
		return participantList;
	}

	/**
	 * Participants of schedule rotation
	 *
	 * @see ScheduleParticipant
	 */
	public List<ScheduleParticipant> getParticipants() {
		return participants;
	}

	/**
	 * Sets participants of schedule rotation
	 *
	 * @see ScheduleParticipant
	 */
	public void setParticipants(List<ScheduleParticipant> participants) {
		this.participants = participants;
	}

	/**
	 * Restriction list of schedule rotation
	 *
	 * @see ScheduleRotationRestriction
	 */
	public List<ScheduleRotationRestriction> getRestrictions() {
		return restrictions;
	}

	/**
	 * Sets restriction list of schedule rotation
	 *
	 * @see ScheduleRotationRestriction
	 */
	public void setRestrictions(List<ScheduleRotationRestriction> restrictions) {
		this.restrictions = restrictions;
	}

	/**
	 * Will be set by schedule
	 *
	 * @param scheduleTimeZone -schedule time zone
	 */
	public void setScheduleTimeZone(TimeZone scheduleTimeZone) {
		this.scheduleTimeZone = scheduleTimeZone;
	}

	@Override
	public TimeZone getObjectTimeZone() {
		return scheduleTimeZone;
	}

}
