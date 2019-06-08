package com.hotel.mariam.entity;

import java.util.Date;

public class Room {
    private int roomNumber;
    private RoomTypes roomType;
    private double roomPrice;
    private Date roomBookingDate;
    private Date roomStartDate;
    private Date roomEndDate;
    private int hotelID;
    private int clientID;

    public Room() {
    }

    public Room(int roomNumber, RoomTypes roomType, double roomPrice, int hotelID) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.hotelID = hotelID;
    }

    public Room(int roomNumber, RoomTypes roomType, double roomPrice, Date roomBookingDate,
                Date roomStartDate, Date roomEndDate, int hotelID, int clientID) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomBookingDate = roomBookingDate;
        this.roomStartDate = roomStartDate;
        this.roomEndDate = roomEndDate;
        this.hotelID = hotelID;
        this.clientID = clientID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public Date getRoomBookingDate() {
        return roomBookingDate;
    }

    public void setRoomBookingDate(Date roomBookingDate) {
        this.roomBookingDate = roomBookingDate;
    }

    public Date getRoomStartDate() {
        return roomStartDate;
    }

    public void setRoomStartDate(Date roomStartDate) {
        this.roomStartDate = roomStartDate;
    }

    public Date getRoomEndDate() {
        return roomEndDate;
    }

    public void setRoomEndDate(Date roomEndDate) {
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

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", roomPrice=" + roomPrice +
                ", roomBookingDate=" + roomBookingDate +
                ", roomStartDate=" + roomStartDate +
                ", roomEndDate=" + roomEndDate +
                ", hotelID=" + hotelID +
                ", clientID=" + clientID +
                '}';
    }
}
