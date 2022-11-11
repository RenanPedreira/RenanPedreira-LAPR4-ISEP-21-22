package eapli.base.agvmanagement.dto;

import eapli.base.common.util.Pair;

import java.time.Duration;

public class AGVDTO {
    private String identifier;
    private String model;
    private String technicalDescription;
    private String taskStatus;
    private Duration batteryAutonomy;
    private String baseLocation;
    private double capacity;
    private Pair<Integer,Integer> position;
    private int speed;

    public AGVDTO(String identifier, String model, String technicalDescription, String taskStatus, Duration batteryAutonomy, String baseLocation, double capacity , int speed , Pair<Integer,Integer> position) {
        this.identifier = identifier;
        this.model = model;
        this.technicalDescription = technicalDescription;
        this.taskStatus = taskStatus;
        this.batteryAutonomy = batteryAutonomy;
        this.baseLocation = baseLocation;
        this.capacity = capacity;
        this.position = position;
        this.speed = speed;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getModel() {
        return model;
    }

    public String getTechnicalDescription() {
        return technicalDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public Duration getBatteryAutonomy() {
        return batteryAutonomy;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public double getCapacity() {
        return capacity;
    }

    public Pair<Integer,Integer> getPosition() {
        return position;
    }
    public int getSpeed(){
        return speed;
    }

    @Override
    public String toString() {
       return "AGV id: " + identifier + "\n";
    }
}
