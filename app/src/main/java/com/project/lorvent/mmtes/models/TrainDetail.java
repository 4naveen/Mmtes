package com.project.lorvent.mmtes.models;

/**
 * Created by user on 17/6/17.
 */

public class TrainDetail {

    String train_no,stn_name,departure;

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getStn_name() {
        return stn_name;
    }

    public void setStn_name(String stn_name) {
        this.stn_name = stn_name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
