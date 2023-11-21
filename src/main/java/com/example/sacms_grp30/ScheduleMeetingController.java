package com.example.sacms_grp30;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ScheduleMeetingController {
    @FXML
    private Button BackButton;
    @FXML
    private Button FindButton;
    @FXML
    private Button SubmitButton;
    @FXML
    private Button ClearButton;
    @FXML
    private TextField ClubName;
    @FXML
    private TextField MeetingTopic;
    @FXML
    private DatePicker MeetingDate;
    @FXML
    private TextField MeetingTime;
    @FXML
    private TextField MeetingPlatform;
    @FXML
    private TextField MeetingDescription;
    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
}
