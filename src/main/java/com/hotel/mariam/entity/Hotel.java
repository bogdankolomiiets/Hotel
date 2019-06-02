package com.hotel.mariam.entity;

public class Hotel {
    private String name;
    private String address;
    private String phone;
    private int countOfFloors;
    private int countOfSingleRooms;
    private int countOfDoubleRooms;
    private int countOfTripleRooms;
    private int countOfQuadRooms;
    private int countOfKingRooms;

    public Hotel() {
    }

    public Hotel(String name, String address,
                 String phone, int countOfFloors,
                 int countOfSingleRooms, int countOfDoubleRooms,
                 int countOfTripleRooms, int countOfQuadRooms,
                 int countOfKingRooms) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.countOfFloors = countOfFloors;
        this.countOfSingleRooms = countOfSingleRooms;
        this.countOfDoubleRooms = countOfDoubleRooms;
        this.countOfTripleRooms = countOfTripleRooms;
        this.countOfQuadRooms = countOfQuadRooms;
        this.countOfKingRooms = countOfKingRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCountOfFloors() {
        return countOfFloors;
    }

    public void setCountOfFloors(int countOfFloors) {
        this.countOfFloors = countOfFloors;
    }

    public int getCountOfSingleRooms() {
        return countOfSingleRooms;
    }

    public void setCountOfSingleRooms(int countOfSingleRooms) {
        this.countOfSingleRooms = countOfSingleRooms;
    }

    public int getCountOfDoubleRooms() {
        return countOfDoubleRooms;
    }

    public void setCountOfDoubleRooms(int countOfDoubleRooms) {
        this.countOfDoubleRooms = countOfDoubleRooms;
    }

    public int getCountOfTripleRooms() {
        return countOfTripleRooms;
    }

    public void setCountOfTripleRooms(int countOfTripleRooms) {
        this.countOfTripleRooms = countOfTripleRooms;
    }

    public int getCountOfQuadRooms() {
        return countOfQuadRooms;
    }

    public void setCountOfQuadRooms(int countOfQuadRooms) {
        this.countOfQuadRooms = countOfQuadRooms;
    }

    public int getCountOfKingRooms() {
        return countOfKingRooms;
    }

    public void setCountOfKingRooms(int countOfKingRooms) {
        this.countOfKingRooms = countOfKingRooms;
    }
}
