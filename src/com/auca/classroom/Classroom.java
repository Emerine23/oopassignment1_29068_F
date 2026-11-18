package com.auca.classroom;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;

import java.util.Locale;

public class Classroom implements Identifiable, Registrable {

    private String classroomId;
    private String buildingName;
    private String roomNumner;
    private int capacity;
    private int floorNumber;
    private boolean available;

    public Classroom(String classroomId, String buildingName, String roomNumner, int capacity, int floorNumber, boolean available) {
        this.classroomId = classroomId;
        this.buildingName = buildingName;
        this.roomNumner = roomNumner;
        this.capacity = capacity;
        this.floorNumber = floorNumber;
        this.available = available;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumner() {
        return roomNumner;
    }

    public void setRoomNumner(String roomNumner) {
        this.roomNumner = roomNumner;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String getId() {
        return classroomId;
    }

    @Override
    public String getDisplayName() {
        return  buildingName+ "- Room" + roomNumner;
    }

    @Override
    public boolean isActive() {
        return available;
    }

    @Override
    public void validate() throws ValidationException {
        if (classroomId == null || classroomId.trim().isEmpty()){
            throw new ValidationException("Classroom ID is required");
        }
        if (buildingName == null || buildingName.trim().isEmpty()){
            throw new ValidationException("Building name is required");
        }
        if (roomNumner == null || roomNumner.trim().isEmpty()){
            throw new ValidationException("Room number is required");
        }
        if (capacity<=0){
            throw new ValidationException("Classroom capacity must be greater than zero");
        }
        if (floorNumber<0){
            throw new ValidationException("Floor number cannot be negative");
        }
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Classroom:" + getDisplayName();
    }

    @Override
    public String toString() {
        return "\nclassroomId:" + classroomId+
                "\nbuildingName:" + buildingName+
                "\nroomNumner:" + roomNumner+
                "\ncapacity:" + capacity +
                "\nfloorNumber:" + floorNumber +
                "\navailable=" + available;
    }
}
