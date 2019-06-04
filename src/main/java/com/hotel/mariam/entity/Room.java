package com.hotel.mariam.entity;

import java.util.Calendar;

public class Room {
    private int roomId;
    private RoomTypes roomType;
    private double roomPrice;
    private Calendar roomBookingDate;
    private Calendar roomStartDate;
    private Calendar roomEndDate;
    private int hotelID;
    private int clientID;

    public Room() {
    }

    public Room(int roomId, RoomTypes roomType, double roomPrice, Calendar roomBookingDate,
                Calendar roomStartDate, Calendar roomEndDate, int hotelID, int clientID) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomBookingDate = roomBookingDate;
        this.roomStartDate = roomStartDate;
        this.roomEndDate = roomEndDate;
        this.hotelID = hotelID;
        this.clientID = clientID;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypes roomType) {
        this.roomType = roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Calendar getRoomBookingDate() {
        return roomBookingDate;
    }

    public void setRoomBookingDate(Calendar roomBookingDate) {
        this.roomBookingDate = roomBookingDate;
    }

    public Calendar getRoomStartDate() {
        return roomStartDate;
    }

    public void setRoomStartDate(Calendar roomStartDate) {
        this.roomStartDate = roomStartDate;
    }

    public Calendar getRoomEndDate() {
        return roomEndDate;
    }

    public void setRoomEndDate(Calendar roomEndDate) {
        this.roomEndDate = roomEndDate;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
