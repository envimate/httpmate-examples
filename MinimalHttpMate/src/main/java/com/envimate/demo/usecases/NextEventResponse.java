package com.envimate.demo.usecases;

public class NextEventResponse {
    public final String currentTime;
    public final String timeZone;

    public NextEventResponse(String currentTime, String timeZone) {
        this.currentTime = currentTime;
        this.timeZone = timeZone;
    }
}
