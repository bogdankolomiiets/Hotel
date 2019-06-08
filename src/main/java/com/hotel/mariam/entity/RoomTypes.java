package com.hotel.mariam.entity;

public enum RoomTypes {
    SINGLE(1),
    DOUBLE(2),
    TRIPLE(3),
    QUAD(4),
    QUEEN(5),
    KING(6);

    int intValue;

    public int getIntValue(){
        return intValue;
    }

    RoomTypes(int intValue) {
        this.intValue = intValue;
    }
}
