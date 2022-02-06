package com.example.parkingsystem.entity;

import java.util.List;

public class Zone {
    private String parkingZoneId;
    List<ParkingSpace> parkingPlaces;

    @Override
    public String toString() {
        return "Zone{" +
                "parkingZoneId='" + parkingZoneId + '\'' +
                ", parkingPlaces=" + parkingPlaces +
                '}';
    }

    public Zone(String parkingZoneId) {
        this.parkingZoneId = parkingZoneId;
    }

    public String getParkingZoneId() {
        return parkingZoneId;
    }

    public void setParkingZoneId(String parkingZoneId) {
        this.parkingZoneId = parkingZoneId;
    }
}
