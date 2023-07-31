package com.ifountain.opsgenie.client.model.beans;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.List;

public class TimeRestriction extends Bean{
    private String type;
    private List<Restriction> restrictions;

    public void validateTimeRestriction() throws OpsGenieClientValidationException {
        if(TimeRestrictionType.getFromValues(type) == null)
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.TIME_RESTRICTION_TYPE);
        if(TimeRestrictionType.WEEKDAY_AND_TIME_OF_DAY.getValue().equalsIgnoreCase(type)){
            validateRestrictions(TimeRestrictionType.WEEKDAY_AND_TIME_OF_DAY);
        }
        else if(TimeRestrictionType.TIME_OF_DAY.getValue().equalsIgnoreCase(type)) {
            validateRestrictions(TimeRestrictionType.TIME_OF_DAY);
        }
    }

    public void validateRestrictions(TimeRestrictionType timeRestrictionType) throws OpsGenieClientValidationException {
        if(restrictions == null || restrictions.isEmpty())
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.RESTRICTIONS);
        for(Restriction restriction: restrictions){
            restriction.validateRestriction(timeRestrictionType);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }
}
