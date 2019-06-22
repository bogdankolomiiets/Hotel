package com.hotel.mariam.constants;

public enum RoomLevel {
    ECONOMY(1),
    STANDARD(2),
    IMPROVED(3),
    DELUXE(4);

    int intValue;

    public int getIntValue(){
        return intValue;
    }

    RoomLevel(int intValue) {
        this.intValue = intValue;
    }
}
