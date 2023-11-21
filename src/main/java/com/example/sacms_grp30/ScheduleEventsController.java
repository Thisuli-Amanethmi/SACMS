package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ScheduleEventsController {
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
    private TextField EventName;
    @FXML
    private DatePicker EventDate;
    @FXML
    private TextField EventTime;
    @FXML
    private TextField EventLocation;
    @FXML
    private TextField EventDescription;
    @FXML
    private Label SuccessfulMessage;


    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void handleSubmitButton(ActionEvent event) throws Exception{
        SuccessfulMessage.setText("EVENT SCHEDULED SUCCESSFULLY");
    }

}
