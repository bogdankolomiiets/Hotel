package com.hotel.mariam.entity;

import com.hotel.mariam.constants.RoomLevel;
import com.hotel.mariam.constants.RoomType;

import java.util.Date;

public class Query {
    private int queryId;
    private RoomType roomType;
    private RoomLevel roomLevel;
    private Date roomBookingDate;
    private Date roomStartDate;
    private Date roomEndDate;
    private double queryAmount;
    private String clientEmail;
    private int queryStatus;

    public Query(){

    }

    public Query(RoomType roomType, RoomLevel roomLevel, Date roomBookingDate, Date roomStartDate, Date roomEndDate, double queryAmount, String clientEmail, int queryStatus) {
        this.roomType = roomType;
        this.roomLevel = roomLevel;
        this.roomBookingDate = roomBookingDate;
        this.roomStartDate = roomStartDate;
        this.roomEndDate = roomEndDate;
        this.queryAmount = queryAmount;
        this.clientEmail = clientEmail;
        this.queryStatus = queryStatus;
    }

    public Query(int queryId, RoomType roomType, RoomLevel roomLevel, Date roomBookingDate, Date roomStartDate, Date roomEndDate, double queryAmount, String clientEmail, int queryStatus) {
        this.queryId = queryId;
        this.roomType = roomType;
        this.roomLevel = roomLevel;
        this.roomBookingDate = roomBookingDate;
        this.roomStartDate = roomStartDate;
        this.roomEndDate = roomEndDate;
        this.queryAmount = queryAmount;
        this.clientEmail = clientEmail;
        this.queryStatus = queryStatus;
    }

    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomLevel getRoomLevel() {
        return roomLevel;
    }

    public void setRoomLevel(RoomLevel roomLevel) {
        this.roomLevel = roomLevel;
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

    public double getQueryAmount() {
        return queryAmount;
    }

    public void setQueryAmount(double queryAmount) {
        this.queryAmount = queryAmount;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(int queryStatus) {
        this.queryStatus = queryStatus;
    }

    @Override
    public String toString() {
        return "Query{" +
                "queryId=" + queryId +
                ", roomType=" + roomType +
                ", roomLevel=" + roomLevel +
                ", roomBookingDate=" + roomBookingDate +
                ", roomStartDate=" + roomStartDate +
                ", roomEndDate=" + roomEndDate +
                ", queryAmount=" + queryAmount +
                ", clientEmail='" + clientEmail + '\'' +
                ", queryStatus=" + queryStatus +
                '}';
    }
}
