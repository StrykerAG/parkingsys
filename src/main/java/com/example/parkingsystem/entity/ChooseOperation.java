package com.example.parkingsystem.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseOperation {

    private String operation;
    private Integer carId;
    private String parkingZone;
    private int parkingSpace;

}
