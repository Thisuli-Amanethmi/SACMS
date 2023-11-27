package com.example.sacms_grp30;

import java.time.LocalDate;

public class ScheduledEvents {
    private String eventName;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private String eventDate;

    public ScheduledEvents(String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription, String text){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;

    }

}
