package com.example.cwsample;

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

}