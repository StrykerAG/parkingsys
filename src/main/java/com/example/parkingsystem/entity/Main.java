package com.example.parkingsystem.entity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/parkingMap.json"));
        Parking parking = new Gson().fromJson(br, Parking.class);
        Scanner scanner = new Scanner(System.in);
        Start start = new Start();

        String stop = "";
        while (!stop.equals("stop")){
            start.setCarId(parking);
            start.carIsMoving(parking);
            start.averageTime();
            System.out.println("Do you want stop program? Enter \"stop\", to stop program or press enter to continue : ");
            stop = scanner.nextLine();
        }
    }




}
