package com.example.sacms_grp30;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EventSchedulingController {
    @FXML
    private Button EventButton;
    @FXML
    private Button MeetingButton;
    @FXML
    private Button ViewEventButton;
    @FXML
    private Button ViewMeetingsButton;
    public void handleEventButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ScheduleEventsScene.fxml"));
        Stage window = (Stage) EventButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void handleMeetingButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ScheduleMeetingScene.fxml"));
        Stage window = (Stage) MeetingButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void handleViewEventsButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewEvents.fxml"));
        Stage window = (Stage) ViewEventButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void handleViewMeetingsButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewMeetings.fxml"));
        Stage window = (Stage) ViewEventButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }

}