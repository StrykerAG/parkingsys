package com.example.parkingsystem.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parking {
    private Map<String, List<ParkingSpace>> mapParking = new LinkedHashMap<>();
    private List<Crossroad> crossroads = new ArrayList<>();
    private List<CrossroadConnection> crossroadConnections = new ArrayList<>();


    @Override
    public String toString() {
        return "Parking{" +
                "mapParking=" + mapParking +
                ", crossroads=" + crossroads +
                ", crossroadConnections=" + crossroadConnections +
                '}';
    }

    public List<CrossroadConnection> getCrossroadConnections() {
        return crossroadConnections;
    }

    public void setCrossroadConnections(List<CrossroadConnection> crossroadConnections) {
        this.crossroadConnections = crossroadConnections;
    }

    public Map<String, List<ParkingSpace>> getMapParking() {
        return mapParking;
    }

    public void setMapParking(Map<String, List<ParkingSpace>> mapParking) {
        this.mapParking = mapParking;
    }

    public List<Crossroad> getCrossroads() {
        return crossroads;
    }

    public void setCrossroads(List<Crossroad> crossroads) {
        this.crossroads = crossroads;
    }
}