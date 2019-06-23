package com.hotel.mariam.entity;

public class Hotel {
    private int hotelId;
    private String name;
    private String address;
    private String phone;
    private int countOfFloors;
    private int countOfStars;

    public Hotel() {
    }

    public Hotel(String name, String address, String phone, int countOfFloors, int countOfStars) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.countOfFloors = countOfFloors;
        this.countOfStars = countOfStars;
    }

    public Hotel(int hotelId, String name, String address, String phone, int countOfFloors, int countOfStars) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.countOfFloors = countOfFloors;
        this.countOfStars = countOfStars;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public int getCountOfStars() {
        return countOfStars;
    }

    public void setCountOfStars(int countOfStars) {
        this.countOfStars = countOfStars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", countOfFloors=" + countOfFloors +
                ", countOfStars=" + countOfStars +
                '}';
    }
}
