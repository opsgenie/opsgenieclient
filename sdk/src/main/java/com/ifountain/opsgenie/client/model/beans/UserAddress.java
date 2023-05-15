package com.ifountain.opsgenie.client.model.beans;

public class UserAddress extends Bean{
    private String country;
    private String state;
    private String city;
    private String line;
    private String zipCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserAddress withCountry(String country) {
        this.country = country;
        return this;
    }

    public UserAddress withCity(String city) {
        this.city = city;
        return this;
    }

    public UserAddress withState(String state) {
        this.state = state;
        return this;
    }

    public UserAddress withLine(String line) {
        this.line = line;
        return this;
    }

    public UserAddress withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
}
