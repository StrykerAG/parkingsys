package com.example.parkingsystem.entity;

public class ParkingSpace {
    private int parkingSpaceId;
    private String zoneId;
    private int carId;
    private int reserveCarId;
    private boolean availableSpace = true;


    public ParkingSpace(int parkingSpaceId, String zoneId, boolean availablePlace) {
        this.parkingSpaceId = parkingSpaceId;
        this.zoneId = zoneId;
        this.availableSpace = availablePlace;
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "parkingSpaceId=" + parkingSpaceId +
                ", zoneId='" + zoneId + '\'' +
                ", carId=" + carId +
                ", reserveCarId=" + reserveCarId +
                ", availableSpace=" + availableSpace +
                '}';
    }

    public int getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getReserveCarId() {
        return reserveCarId;
    }

    public void setReserveCarId(int reserveCarId) {
        this.reserveCarId = reserveCarId;
    }

    public boolean isAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(boolean availableSpace) {
        this.availableSpace = availableSpace;
    }
}
