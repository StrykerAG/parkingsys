package com.example.parkingsystem.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogMonitor {
    int monitorId;
    Camera camera = new Camera();

    public void showWelcomeMessage(Car car) {
        if (car.getParkingZoneForCar().equals("a")) {
            log.info("_______________________________________\n" +
                    "|Monitor 1 :\t\t|\n" +
                    "|Welcome to the park|" +
                    "\n|Your parking zone " + car.getParkingZoneForCar() + "|" +
                    "\n|Parking space number " + car.getParkingSpaceForCar() + "|" +
                    "\n_______________________________________\n");
        }
    }


    public void showCrossroadDirection(Car car, Parking parking) {
        log.info("_______________________________________\n" +
                "|Monitor id : " + camera.eventCarOnCrossroad(car) + "C|\n" +
                "|Crossroad : " + camera.eventCarOnCrossroad(car) + "|\n" +
                "|Move " + camera.eventCarDirection(parking, car) + "|\n" +
                "_______________________________________\n");
    }

    public void showFinishCrossroadDirection(Car car, Parking parking) {
        log.info("_______________________________________\n" +
                "|Monitor id : " + car.getNextCrossroadPosition() + "C|\n" +
                "|Crossroad : " + car.getNextCrossroadPosition() + "|\n" +
                "|Move to zone " + car.getParkingZoneForCar() + "|\n" +
                "_______________________________________\n");
    }

    public void showWelcomeMessageToParkingSpace(Car car) {
        log.info(
                "_______________________________________\n" +
                        "|Monitor id : " + camera.eventCarHasParked(car) + "P|\n" +
                        "|Parking Space : " + camera.eventCarHasParked(car) + "|\n" +
                        "Welcome to parking space\n" +
                        "_______________________________________\n");
    }
}
