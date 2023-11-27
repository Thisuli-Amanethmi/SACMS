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
    @FXML
    private Label SuccessfulMessage;
    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void addMeetingDetails(){
        ArrayList meeting = new ArrayList();

        meeting.add(MeetingTopic.getText());
        meeting.add(MeetingDate.getValue().toString());
        meeting.add(MeetingTime.getText());
        meeting.add(MeetingPlatform.getText());
        meeting.add(MeetingDescription.getText());
        Meetings.meetingDetailsList.add(meeting);
        SuccessfulMessage.setText("Driver Added Successfully");
    }
    public void handleSubmitButton(ActionEvent event) throws Exception{
        if (MeetingTopic.getText().isEmpty() && MeetingDate.getValue() == null && MeetingTime.getText().isEmpty() && MeetingPlatform.getText().isEmpty() && MeetingDescription.getText().isEmpty()){
            SuccessfulMessage.setText("All the fields are Empty. Please Fill!");
        }
        else if (MeetingTopic.getText().isEmpty() || MeetingDate.getValue() == null || MeetingTime.getText().isEmpty() || MeetingPlatform.getText().isEmpty() || MeetingDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("");
            SuccessfulMessage.setText("Please Fill All the fields!");
        }
        else {
            addMeetingDetails();
            SuccessfulMessage.setText("Driver Added Successfully");
        }
    }
}