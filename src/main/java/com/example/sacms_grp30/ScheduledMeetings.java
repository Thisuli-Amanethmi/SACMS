package com.example.sacms_grp30;

public class ScheduledMeetings {
    private String meetingTopic;
    private String meetingDate;
    private String meetingTime;
    private String meetingPlatform;
    private String meetingDescription;

    public ScheduledMeetings(String meetingTopic, String meetingDate, String meetingTime, String meetingPlatform, String meetingDescription) {
        this.meetingTopic = meetingTopic;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.meetingPlatform = meetingPlatform;
        this.meetingDescription = meetingDescription;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingPlatform() {
        return meetingPlatform;
    }

    public void setMeetingPlatform(String meetingPlatform) {
        this.meetingPlatform = meetingPlatform;
    }

    public String getMeetingDescription() {
        return meetingDescription;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription = meetingDescription;
    }
}

