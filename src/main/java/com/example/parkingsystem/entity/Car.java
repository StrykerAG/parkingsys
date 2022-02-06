package com.example.parkingsystem.entity;

public class Car {
    private int carId;
    private String parkingZoneForCar;
    private int parkingSpaceForCar;
    private int currentCrossroadPosition = 1;
    private int previousCrossroadPosition = 1;
    private int nextCrossroadPosition;

    public Car() {
    }

    public Car(int carId, String parkingZoneForCar, int parkingSpaceForCar) {
        this.carId = carId;
        this.parkingZoneForCar = parkingZoneForCar;
        this.parkingSpaceForCar = parkingSpaceForCar;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", parkingZoneForCar='" + parkingZoneForCar + '\'' +
                ", parkingSpaceForCar=" + parkingSpaceForCar +
                ", currentCrossroadPosition=" + currentCrossroadPosition +
                ", previousCrossroadPosition=" + previousCrossroadPosition +
                ", nextCrossroadPosition=" + nextCrossroadPosition +
                '}';
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getParkingZoneForCar() {
        return parkingZoneForCar;
    }

    public void setParkingZoneForCar(String parkingZoneForCar) {
        this.parkingZoneForCar = parkingZoneForCar;
    }

    public int getParkingSpaceForCar() {
        return parkingSpaceForCar;
    }

    public void setParkingSpaceForCar(int parkingSpaceForCar) {
        this.parkingSpaceForCar = parkingSpaceForCar;
    }

    public int getCurrentCrossroadPosition() {
        return currentCrossroadPosition;
    }

    public void setCurrentCrossroadPosition(int currentCrossroadPosition) {
        this.currentCrossroadPosition = currentCrossroadPosition;
    }

    public int getPreviousCrossroadPosition() {
        return previousCrossroadPosition;
    }

    public void setPreviousCrossroadPosition(int previousCrossroadPosition) {
        this.previousCrossroadPosition = previousCrossroadPosition;
    }

    public int getNextCrossroadPosition() {
        return nextCrossroadPosition;
    }

    public void setNextCrossroadPosition(int nextCrossroadPosition) {
        this.nextCrossroadPosition = nextCrossroadPosition;
    }
}
