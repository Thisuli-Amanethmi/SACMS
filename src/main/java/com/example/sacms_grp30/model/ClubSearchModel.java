package com.example.sacms_grp30.model;

public class ClubSearchModel {
    private String club_id;
    private String club_name;
    private String club_category;
    private String description;

    public ClubSearchModel(String club_id, String club_name, String club_category, String description) {
        this.club_id = club_id;
        this.club_name = club_name;
        this.club_category = club_category;
        this.description = description;
    }

    public String getClub_id() {
        return club_id;
    }

    public void setClub_id(String club_id) {
        this.club_id = club_id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_category() {
        return club_category;
    }

    public void setClub_category(String club_category) {
        this.club_category = club_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
