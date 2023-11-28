package com.example.sacms_grp30;

public class ScheduledMeetings {
    private String meetingId;
    private String meetingTopic;
    private String meetingDate;
    private String meetingTime;
    private String meetingPlatform;
    private String meetingDescription;

    public ScheduledMeetings(String meetingId, String meetingTopic, String meetingDate, String meetingTime, String meetingPlatform, String meetingDescription) {
        this.meetingId = meetingId;
        this.meetingTopic = meetingTopic;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.meetingPlatform = meetingPlatform;
        this.meetingDescription = meetingDescription;
    }
    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
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
