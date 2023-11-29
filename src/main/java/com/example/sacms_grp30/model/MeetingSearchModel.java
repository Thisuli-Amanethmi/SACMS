package com.example.sacms_grp30.model;

public class MeetingSearchModel {

    private String meeting_id;
    private String meeting_topic;
    private String meeting_date;
    private String meeting_time;
    private String meeting_platform;
    private String meeting_description;

    public MeetingSearchModel(String meeting_id, String meeting_topic, String meeting_date, String meeting_time, String meeting_platform, String meeting_description) {
        this.meeting_id = meeting_id;
        this.meeting_topic = meeting_topic;
        this.meeting_date = meeting_date;
        this.meeting_time = meeting_time;
        this.meeting_platform = meeting_platform;
        this.meeting_description = meeting_description;
    }

    public String getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getMeeting_topic() {
        return meeting_topic;
    }

    public void setMeeting_topic(String meeting_topic) {
        this.meeting_topic = meeting_topic;
    }

    public String getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }

    public String getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(String meeting_time) {
        this.meeting_time = meeting_time;
    }

    public String getMeeting_platform() {
        return meeting_platform;
    }

    public void setMeeting_platform(String meeting_platform) {
        this.meeting_platform = meeting_platform;
    }

    public String getMeeting_description() {
        return meeting_description;
    }

    public void setMeeting_description(String meeting_description) {
        this.meeting_description = meeting_description;
    }
}
