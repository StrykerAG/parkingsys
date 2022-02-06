package com.example.parkingsystem.entity;

public class Camera {

    int cameraId;

    int eventCarOnCrossroad(Car car) {
        return car.getCurrentCrossroadPosition();
    }

    String eventCarDirection(Parking parking, Car car) {
        String direction = "Unknown direction";
        for (int i = 0; i < parking.getCrossroadConnections().size(); i++) {
            if (parking.getCrossroadConnections().get(i).getCrossroadA() == car.getCurrentCrossroadPosition()
                    && parking.getCrossroadConnections().get(i).getCrossroadB() == car.getNextCrossroadPosition()) {
                direction = parking.getCrossroadConnections().get(i).getDirection();
                return direction;
            }
        }
        return direction;
    }

    int eventCarHasParked(Car car) {
        return car.getParkingSpaceForCar();
    }

    void eventCarWrongParked(Car car) {
    }
}
