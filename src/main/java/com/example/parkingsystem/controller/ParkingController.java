package com.example.parkingsystem.controller;

import com.example.parkingsystem.entity.ChooseOperation;
import com.example.parkingsystem.service.ParkingService;
import com.example.parkingsystem.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping("/times")
    public ResponseEntity getTime(){
        try {
            if (TimeService.readFromJson() > 1){
                return ResponseEntity.ok("Average parking time for the last two minutes : " + TimeService.readFromJson() + " seconds");
            }else
                return ResponseEntity.ok("2 minutes have not yet elapsed since the entry of the first car");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong : " + e.getMessage());
        }
    }

    @PostMapping("/choose")
    public String choose(@RequestBody ChooseOperation operation) {
        return parkingService.chooseCar(operation);
    }

    @GetMapping("/move/{carId}")
    public String move(@PathVariable("carId") Integer cardId) {
        parkingService.carIsMoving(cardId);
        // todo return all info in res
        return "Moved car, please check logs";
    }
}
