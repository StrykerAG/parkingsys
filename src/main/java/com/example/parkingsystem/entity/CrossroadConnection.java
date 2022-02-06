package com.example.parkingsystem.entity;

public class CrossroadConnection {
    private int crossroadA;
    private int crossroadB;
    private String direction;

    public CrossroadConnection(int crossroadA, int crossroadB) {
        this.crossroadA = crossroadA;
        this.crossroadB = crossroadB;
    }

    public CrossroadConnection(int crossroadA, int crossroadB, String direction) {
        this.crossroadA = crossroadA;
        this.crossroadB = crossroadB;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "CrossroadConnection{" +
                "crossroadA=" + crossroadA +
                ", crossroadB=" + crossroadB +
                ", direction='" + direction + '\'' +
                '}';
    }

    public int getCrossroadA() {
        return crossroadA;
    }

    public void setCrossroadA(int crossroadA) {
        this.crossroadA = crossroadA;
    }

    public int getCrossroadB() {
        return crossroadB;
    }

    public void setCrossroadB(int crossroadB) {
        this.crossroadB = crossroadB;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
