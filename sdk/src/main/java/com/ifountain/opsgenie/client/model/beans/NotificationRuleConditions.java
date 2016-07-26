package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

/**
 * NotificationRuleConditions bean
 */
public class NotificationRuleConditions implements IBean {
	public static enum Field {
		actions, alias, description, entity, extraProperties, message, recipients, source, tags, teams;
		public static String getEnumName(Field f){
			if(f == null)
				return null;
			if(f.equals(Field.message))
		        return OpsGenieClientConstants.API.MESSAGE;
			else if(f.equals(Field.extraProperties))
		        return OpsGenieClientConstants.API.EXTRA_PROPERTIES;
			else if(f.equals(Field.description))
		        return OpsGenieClientConstants.API.DESCRIPTION;
			else if(f.equals(Field.recipients))
		        return OpsGenieClientConstants.API.RECIPIENTS;
			else if(f.equals(Field.teams))
				return OpsGenieClientConstants.API.TEAMS;
			else if(f.equals(Field.actions))
				return OpsGenieClientConstants.API.ACTIONS;
			else if(f.equals(Field.alias))
				return OpsGenieClientConstants.API.ALIAS;
			else if(f.equals(Field.entity))
				return OpsGenieClientConstants.API.ENTITY;
			else if(f.equals(Field.source))
				return OpsGenieClientConstants.API.SOURCE;
			else if(f.equals(Field.tags))
				return OpsGenieClientConstants.API.TAGS;
			System.out.println("Error at field "+ f);
			return null;
		}
		public static Field getField(String fieldStr){
			if(fieldStr == null)
				return null;
			if (fieldStr.equals(OpsGenieClientConstants.API.MESSAGE))
				return Field.message;
			else if (fieldStr.equals(OpsGenieClientConstants.API.EXTRA_PROPERTIES))
				return Field.extraProperties;
			else if (fieldStr.equals(OpsGenieClientConstants.API.DESCRIPTION))
				return Field.description;
			else if (fieldStr.equals(OpsGenieClientConstants.API.RECIPIENTS))
				return Field.recipients;
			else if (fieldStr.equals(OpsGenieClientConstants.API.TEAMS))
				return Field.teams;
			else if (fieldStr.equals(OpsGenieClientConstants.API.ACTIONS))
				return Field.actions;
			else if (fieldStr.equals(OpsGenieClientConstants.API.ALIAS))
				return Field.alias;
			else if (fieldStr.equals(OpsGenieClientConstants.API.ENTITY))
				return Field.entity;
			else if (fieldStr.equals(OpsGenieClientConstants.API.SOURCE))
				return Field.source;
			else if (fieldStr.equals(OpsGenieClientConstants.API.TAGS))
				return Field.tags;
			System.out.println("Error at fielName " + fieldStr);
			return null;			
		}
	}

	public static enum Operation {
		contains, equals, startsWith, endsWith, matches, isEmpty, equalsIgnoreWhitespace, containsKey, containsValue, greaterThan, lessThan;
		public static String getEnumName(Operation operation){
			if(operation == null)
				return null;
			if (operation.equals(Operation.matches))
		    	return OpsGenieClientConstants.API.OPERATION_MATCHES;
			else if (operation.equals(Operation.equals))
		    	return OpsGenieClientConstants.API.OPERATION_EQUALS;
			else if (operation.equals(Operation.contains))
		    	return OpsGenieClientConstants.API.OPERATION_CONTAINS;
			else if (operation.equals(Operation.startsWith))
		    	return OpsGenieClientConstants.API.OPERATION_STARTS_WITH;
			else if (operation.equals(Operation.endsWith))
		    	return OpsGenieClientConstants.API.OPERATION_END_WITH;
			else if (operation.equals(Operation.isEmpty))
		    	return OpsGenieClientConstants.API.OPERATION_IS_EMPTY;
			else if (operation.equals(Operation.equalsIgnoreWhitespace))
		    	return OpsGenieClientConstants.API.OPERATION_EQUALS_IGNORE_SPACE;
			else if (operation.equals(Operation.containsKey))
		    	return OpsGenieClientConstants.API.OPERATION_CONTAINS_KEY;
			else if (operation.equals(Operation.containsValue))
		    	return OpsGenieClientConstants.API.OPERATION_CONTAINS_VALUE;
			System.out.println("Error at getEnumname operation = " + operation);
			return null;
		}
		public static Operation getOperation(String operationStr){
			if(operationStr == null)
				return null;
			if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_MATCHES))
				return Operation.matches;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_EQUALS))
				return Operation.equals;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_CONTAINS))
				return Operation.contains;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_STARTS_WITH))
				return Operation.startsWith;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_END_WITH))
				return Operation.endsWith;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_IS_EMPTY))
				return Operation.isEmpty;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_EQUALS_IGNORE_SPACE))
				return Operation.equalsIgnoreWhitespace;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_CONTAINS_KEY))
				return Operation.containsKey;
			else if (operationStr.equals(OpsGenieClientConstants.API.OPERATION_CONTAINS_VALUE))
				return Operation.containsValue;
			System.out.println("Error at getOperation name = " + operationStr);
			return null;
		}
	}

	private Boolean not;
	private Field field;
	private String expectedValue;
	private Operation operation;

	public Boolean getNot() {
		return not;
	}

	public void setNot(Boolean not) {
		this.not = not;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NOT, getNot());
        if(getField() != null)
	        json.put(OpsGenieClientConstants.API.FIELD, Field.getEnumName(this.field));
    	json.put(OpsGenieClientConstants.API.EXPECTED_VALUE, getExpectedValue());
    	if(getOperation() != null)
	    	json.put(OpsGenieClientConstants.API.OPERATION, Operation.getEnumName(this.operation));
        return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
		if (map.containsKey(OpsGenieClientConstants.API.FIELD)) 
			this.field = Field.getField( (String) map.get(OpsGenieClientConstants.API.FIELD));
		if (map.containsKey(OpsGenieClientConstants.API.NOT))
			this.not = (Boolean) map.get(OpsGenieClientConstants.API.NOT);
		if (map.containsKey(OpsGenieClientConstants.API.EXPECTED_VALUE))
			this.expectedValue = (String) map.get(OpsGenieClientConstants.API.EXPECTED_VALUE);
		if (map.containsKey(OpsGenieClientConstants.API.OPERATION)) 
			this.operation = Operation.getOperation((String) map.get(OpsGenieClientConstants.API.OPERATION));

	}

}
