package com.example.parkingsystem.service;

import com.example.parkingsystem.entity.Car;
import com.example.parkingsystem.entity.ChooseOperation;
import com.example.parkingsystem.entity.LogMonitor;
import com.example.parkingsystem.entity.Navigation;
import com.example.parkingsystem.entity.Parking;
import com.example.parkingsystem.entity.ParkingSpace;
import com.example.parkingsystem.entity.Zone;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ParkingService {

    @Autowired
    private LogMonitor monitor;
    //todo use DI
    private Navigation navigation = new Navigation();

    private Parking parking;
    private List<Car> carList = new ArrayList<>();
    private Map<Integer, Car> carMap = new HashMap<>();

    private long timeStart;

    @PostConstruct
    public void init() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/parkingMap.json"));
            parking = new Gson().fromJson(br, Parking.class);
            log.info(parking.toString());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    public String chooseCar(ChooseOperation operation) {
        String response = "default response";
        switch (operation.getOperation()) {
            case "carId": {
                int carId = operation.getCarId();
                Car car = new Car();
                car.setCarId(carId);
                carMap.put(carId, car);
                response = showAllZone();
            }
            break;
            case "parkingZone": {
                Car car = carMap.get(operation.getCarId());
                car.setParkingZoneForCar(operation.getParkingZone());
                response = showAvailableParkingSpace(operation.getParkingZone());
            }
            break;
            case "parkingSpace": {
                Car car = carMap.get(operation.getCarId());
                car.setParkingSpaceForCar(operation.getParkingSpace());
                reserveCar(car);
                response = "Reserved space for car: " + car;
            }
        }
        return response;
    }

    public void carIsMoving(int carId) {
        Car car = carMap.get(carId);
        int dest = 0;
        boolean stop = false;
        boolean carOnFinishCrossroad = false;
        boolean exit = true;

        for (Map.Entry<Integer, List<Zone>> foundZone : parking.getCrossroads().get(0).getMapCrossroads().entrySet()) {
            for (int i = 0; i < foundZone.getValue().size(); i++) {
                if (foundZone.getValue().get(i).getParkingZoneId().equals(car.getParkingZoneForCar())) {
                    dest = foundZone.getKey();
                    if (dest != 0) {
                        stop = true;
                    }
                    break;
                }
            }
            if (stop) {
                break;
            }
        }

        while (exit) {
            if (dest == 1) {
                carOnFinishCrossroad = true;
                break;
            }
            int currentPosition = 0;
            int nextPosition = 0;

            try {
                currentPosition = navigation.navi(car.getCurrentCrossroadPosition(), dest, parking).get(0);
                nextPosition = navigation.navi(car.getCurrentCrossroadPosition(), dest, parking).get(1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong input....");
            }

            if (car.getCurrentCrossroadPosition() > 1) {
                car.setPreviousCrossroadPosition(car.getCurrentCrossroadPosition());
            }
            car.setCurrentCrossroadPosition(currentPosition);
            car.setNextCrossroadPosition(nextPosition);
            monitor.showCrossroadDirection(car, parking);
            car.setCurrentCrossroadPosition(car.getNextCrossroadPosition());
            if ((car.getCurrentCrossroadPosition() == dest)) {
                carOnFinishCrossroad = true;
                exit = false;
            }
        }
        if (carOnFinishCrossroad) {
            monitor.showFinishCrossroadDirection(car, parking);
            for (int i = 0; i < parking.getMapParking().get(car.getParkingZoneForCar()).size(); i++) {
                if (parking.getMapParking().get(car.getParkingZoneForCar()).get(i).getReserveCarId() == car.getCarId()) {
                    parking.getMapParking().get(car.getParkingZoneForCar()).get(i).setAvailableSpace(false);
                    parking.getMapParking().get(car.getParkingZoneForCar()).get(i).setCarId(car.getCarId());
                    monitor.showWelcomeMessageToParkingSpace(car);
                    //todo uncomment
//                    overTime = System.currentTimeMillis() - timeStart;
//                    timeForTwoMinute = (int) (timeForTwoMinute + overTime);
//                    counter++;
                }
            }


        }
    }

    private void reserveCar(Car car) {
        String parkingZoneForCar = car.getParkingZoneForCar();
        int parkingSpaceForCar = car.getParkingSpaceForCar();
        for (int i = 0; i < parking.getMapParking().get(parkingZoneForCar).size(); i++) {
            if (parking.getMapParking().get(parkingZoneForCar).get(i).getParkingSpaceId() == parkingSpaceForCar &&
                    parking.getMapParking().get(parkingZoneForCar).get(i).isAvailableSpace()) {
                parking.getMapParking().get(parkingZoneForCar).get(i).setReserveCarId(car.getCarId());
                break;
            }
        }
    }

    private String showAllZone() {
        StringBuilder stringBuilder = new StringBuilder();
        int zoneIsEmpty = 0;
        for (Map.Entry<String, List<ParkingSpace>> p : parking.getMapParking().entrySet()) {
            for (int i = 0; i < p.getValue().size(); i++) {
                if (p.getValue().get(i).isAvailableSpace()) {
                    zoneIsEmpty++;
                    break;
                }
            }
            if (zoneIsEmpty > 0) {
                stringBuilder.append(p.getKey() + " ");
                zoneIsEmpty = 0;
            }
        }
        return "System : Choose one of this parking zone: " + stringBuilder;
    }

    private String showAvailableParkingSpace(String parkingZone) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < parking.getMapParking().get(parkingZone).size(); i++) {
            if (parking.getMapParking().get(parkingZone).get(i).isAvailableSpace()) {
                stringBuilder.append(parking.getMapParking().get(parkingZone).get(i).getParkingSpaceId() + " ");
            }
        }
        return "System : Choose one of this parking space : " + stringBuilder;
    }

}
