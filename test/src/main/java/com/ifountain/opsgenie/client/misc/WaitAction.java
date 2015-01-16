package com.ifountain.opsgenie.client.misc;

public interface WaitAction {
	/**
     * Implementation of this method should check what it wants to check and throw exception 
     * if the check fails (assertions also throw exception).
     * Sample implementation: 
     * public void check(){
     *  assertEquals(2, ConfigurationManager.getUsers().size());
     * }
     */
    public void check() throws Exception;
}
