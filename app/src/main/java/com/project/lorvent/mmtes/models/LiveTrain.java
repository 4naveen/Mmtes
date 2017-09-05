package com.project.lorvent.mmtes.models;

/**
 * Created by user on 10/6/17.
 */

public class LiveTrain {

    String trainNumber,Curr_location,ExpectedArrival;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getCurr_location() {
        return Curr_location;
    }

    public void setCurr_location(String curr_location) {
        Curr_location = curr_location;
    }

    public String getExpectedArrival() {
        return ExpectedArrival;
    }

    public void setExpectedArrival(String expectedArrival) {
        ExpectedArrival = expectedArrival;
    }
}
