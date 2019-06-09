package com.hotel.mariam.entity;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2),
    TRIPLE(3),
    QUAD(4),
    KING(5);

    int intValue;

    public int getIntValue(){
        return intValue;
    }

    RoomType(int intValue) {
        this.intValue = intValue;
    }
}
