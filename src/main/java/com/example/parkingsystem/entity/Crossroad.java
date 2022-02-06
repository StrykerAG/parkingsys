package com.example.parkingsystem.entity;

import java.util.List;
import java.util.Map;

public class Crossroad {
    Map<Integer, List<Zone>> mapCrossroads;

    @Override
    public String toString() {
        return "Crossroad{" +
                "mapCrossroads=" + mapCrossroads +
                '}';
    }

    public Crossroad(Map<Integer, List<Zone>> mapCrossroads) {
        this.mapCrossroads = mapCrossroads;
    }

    public Map<Integer, List<Zone>> getMapCrossroads() {
        return mapCrossroads;
    }

    public void setMapCrossroads(Map<Integer, List<Zone>> mapCrossroads) {
        this.mapCrossroads = mapCrossroads;
    }
}
