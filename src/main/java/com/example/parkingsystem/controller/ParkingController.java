package com.example.parkingsystem.controller;

import com.example.parkingsystem.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/times")
public class ParkingController {

    @GetMapping
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
}
