package com.example.cwsample;

import java.time.LocalDate;

public class ScheduledEvents {
    private String eventName;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private LocalDate eventDate;

    public ScheduledEvents(String eventName, LocalDate eventDate, String eventTime,String eventLocation, String eventDescription){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;

    }
}
