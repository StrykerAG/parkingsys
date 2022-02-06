package com.example.parkingsystem.entity;

import com.example.parkingsystem.service.TimeService;

import java.util.*;

public class Start {
    private List<Car> carList = new ArrayList<>();

    private Scanner input = new Scanner(System.in);
    private Scanner scanner = new Scanner(System.in);

    private Car car = new Car();
    private Monitor monitor = new Monitor();
    private Navigation navigation = new Navigation();

    private long timeStart;
    private long overTime;
    private int timeForTwoMinute;
    private int counter;
    private int carId;
    private String parkingZoneForCar;
    private int parkingSpaceForCar;


    void averageTime(){
        if(timeForTwoMinute > 120000){
            TimeService.writeToJson(new Time((timeForTwoMinute / counter) / 1000));
            timeForTwoMinute = 0;
            counter = 0;
        }
    }

    Car enterData(Parking parking){
        try {
            System.out.print("System : Choose car id : ");
            carId = input.nextInt();
            System.out.print("System : Choose one of this parking zone for car with id " + carId + "\n");
            showAllZone(parking);
            System.out.print("System : Choose : ");
            parkingZoneForCar = scanner.nextLine();
            System.out.println("System : Choose one of this parking space :");
            showAvailableParkingSpace(parking, parkingZoneForCar);
            System.out.print("System : Choose : ");
            parkingSpaceForCar = input.nextInt();
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return new Car(carId, parkingZoneForCar, parkingSpaceForCar);
    }

    void setCarId(Parking parking) {
        timeStart = System.currentTimeMillis();
            try {
                carList.add(enterData(parking));
                for (int i = 0; i < parking.getMapParking().get(parkingZoneForCar).size(); i++) {
                    if (parking.getMapParking().get(parkingZoneForCar).get(i).getParkingSpaceId() == parkingSpaceForCar &&
                            parking.getMapParking().get(parkingZoneForCar).get(i).isAvailableSpace()) {
                        parking.getMapParking().get(parkingZoneForCar).get(i).setReserveCarId(carId);
                        break;
                    }
                }
                car = carList.get(carList.size() - 1);
                monitor.showWelcomeMessage(car);
            } catch (Exception e) {
                System.out.println("Something went wrong : " + e.getMessage());
            }
    }

    void carIsMoving(Parking parking) {

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

            try{
                 currentPosition = navigation.navi(car.getCurrentCrossroadPosition(), dest, parking).get(0);
                 nextPosition = navigation.navi(car.getCurrentCrossroadPosition(), dest, parking).get(1);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Wrong input....");
            }

            if(car.getCurrentCrossroadPosition()>1){
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
                        overTime = System.currentTimeMillis() - timeStart;
                        timeForTwoMinute = (int) (timeForTwoMinute + overTime);
                        counter++;
                    }
                }


        }
    }

    void showAllZone(Parking parking) {
        int zoneIsEmpty = 0;
        for (Map.Entry<String, List<ParkingSpace>> p : parking.getMapParking().entrySet()) {
            for (int i = 0; i < p.getValue().size(); i++) {
                if (p.getValue().get(i).isAvailableSpace()){
                    zoneIsEmpty++;
                    break;
                }
            }
            if (zoneIsEmpty > 0){
                System.out.print(p.getKey() + " ");
                zoneIsEmpty = 0;
            }
        }
        System.out.println();
    }

    void showAvailableParkingSpace(Parking parking, String parkingZone) {
        for (int i = 0; i < parking.getMapParking().get(parkingZone).size(); i++) {
            if (parking.getMapParking().get(parkingZone).get(i).isAvailableSpace()) {
                System.out.print(parking.getMapParking().get(parkingZone).get(i).getParkingSpaceId() + " ");
            }
        }
        System.out.println();
    }

}
