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

import java.util.ArrayList;
import java.util.List;

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

    private List<ScheduledEvents> events;

    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void handleSubmitButton() throws Exception{

    }
    public void scheduleEvent(){
        ArrayList event = new ArrayList(); //ArrayList to store event details

        event.add(ClubName.getText());
        event.add(EventName.getText());
        event.add(EventDate.getValue().toString());
        event.add(EventTime.getText());
        event.add(EventLocation.getText());
        event.add(EventDescription.getText());
        SuccessfulMessage.setText("EVENT SCHEDULED SUCCESSFULLY");
    }
}
//    private void printEventDetails(ScheduledEvents event) {
//        System.out.println("Event Details:");
//        System.out.println("Event Name: " + event.eventName);
//        System.out.println("Event Date: " + event.date);
//        System.out.println("Event Time: " + event.time);
//        System.out.println("Event Location: " + event.location);
//        System.out.println("Event Description: " + event.description);
//    }

