package com.ifountain.opsgenie.client.marid.alert;

public class NoActionSpecifiedException extends IllegalArgumentException{

    public NoActionSpecifiedException(){
        super("No action specified.");
    }

}
