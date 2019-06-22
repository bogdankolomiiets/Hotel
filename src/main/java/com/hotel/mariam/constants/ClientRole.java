package com.hotel.mariam.constants;

public enum ClientRole {
    USER(0),
    MANAGER(1),
    ADMINISTRATOR(2);


    private int intValue;

    public int getIntValue(){
        return intValue;
    }

    ClientRole(int intValue) {
        this.intValue = intValue;
    }
}
