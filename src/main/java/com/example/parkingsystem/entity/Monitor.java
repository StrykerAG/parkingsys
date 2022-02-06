package com.example.parkingsystem.entity;

public class Monitor {
    int monitorId;
    Camera camera = new Camera();

    void showWelcomeMessage(Car car) {
        if (car.getParkingZoneForCar().equals("a")){
            System.out.println("_______________________________________\n" +
                    "|Monitor 1 :\t\t|\n" +
                    "|Welcome to the park|" +
                    "\n|Your parking zone " + car.getParkingZoneForCar() + "|" +
                    "\n|Parking space number " + car.getParkingSpaceForCar() + "|" +
                    "\n_______________________________________\n");
        }
    }


    void showCrossroadDirection(Car car, Parking parking) {
        System.out.println("_______________________________________\n" +
                "|Monitor id : " + camera.eventCarOnCrossroad(car) + "C|\n" +
                "|Crossroad : " + camera.eventCarOnCrossroad(car) + "|\n" +
                "|Move " + camera.eventCarDirection(parking, car) + "|\n" +
                "_______________________________________\n");
    }

    void showFinishCrossroadDirection(Car car, Parking parking) {
        System.out.println("_______________________________________\n" +
                "|Monitor id : " + car.getNextCrossroadPosition() + "C|\n" +
                "|Crossroad : " + car.getNextCrossroadPosition() + "|\n" +
                "|Move to zone " + car.getParkingZoneForCar() + "|\n" +
                "_______________________________________\n");
    }

    void showWelcomeMessageToParkingSpace(Car car) {
        System.out.println(
                "_______________________________________\n" +
                "|Monitor id : " + camera.eventCarHasParked(car) + "P|\n" +
                "|Parking Space : " + camera.eventCarHasParked(car) + "|\n" +
                "Welcome to parking space\n" +
                "_______________________________________\n");
    }
}
