package com.example.sacms_grp30.tables;

import java.security.PrivateKey;

public class TableClubAdvisorViewClub {
    private String clubAdvisorName;
    private String clubID;
    private String clubName;
    private String category;
    private String description;
    private int noOfStudents;

    public String getClubAdvisorName() {
        return clubAdvisorName;
    }

    public void setClubAdvisorName(String clubAdvisorName) {
        this.clubAdvisorName = clubAdvisorName;
    }

    public String getClubID() {
        return clubID;
    }

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }
}
