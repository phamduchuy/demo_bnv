package com.github.gcacace.signaturepad.expandableRecyclerView.sendRequest;

/**
 * Created by ducqu on 6/1/2018.
 */

public class SendModel {
    private String name = "", time = "";

    public SendModel(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
