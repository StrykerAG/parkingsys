package com.example.parkingsystem.service;

import com.example.parkingsystem.entity.Parking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class TimeService {

    public static void writeToJson(Time time){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/main/resources/Time.json")) {
            gson.toJson(time, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int readFromJson() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Time.json"));
        Time time = new Gson().fromJson(br, Time.class);
        br.close();
        return time.getTime();
    }

}
