package com.example.sacms_grp30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ScheduleMeetingController {
    @FXML
    private Button BackButton;
    @FXML
    private Button SubmitButton;
    @FXML
    private Button ClearButton;
    @FXML
    private ChoiceBox MeetingID;
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
    @FXML
    public void initialize() {
        ObservableList<String> MeetingIDs = FXCollections.observableArrayList("MID001", "MID002", "MID003", "MID004","MID005", "MID006", "MID007", "MID008");
        MeetingID.setItems(MeetingIDs);
    }
    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
    public void addMeetingDetails(){
        ArrayList meeting = new ArrayList();

        meeting.add((MeetingID.getValue().toString()));
        meeting.add(MeetingTopic.getText());
        meeting.add(MeetingDate.getValue().toString());
        meeting.add(MeetingTime.getText());
        meeting.add(MeetingPlatform.getText());
        meeting.add(MeetingDescription.getText());
        Meetings.meetingDetailsList.add(meeting);
        SuccessfulMessage.setText("Meeting Scheduled Successfully");
    }

    String existingMeeting;
    public void handleSubmitButton(ActionEvent event) throws Exception {
        if (MeetingID.getValue() == null && MeetingTopic.getText().isEmpty() && MeetingDate.getValue() == null && MeetingTime.getText().isEmpty() && MeetingPlatform.getText().isEmpty() && MeetingDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("All the fields are Empty. Please Fill!");
        } else if (MeetingID.getValue() == null ||MeetingTopic.getText().isEmpty() || MeetingDate.getValue() == null || MeetingTime.getText().isEmpty() || MeetingPlatform.getText().isEmpty() || MeetingDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("");
            SuccessfulMessage.setText("Please Fill All the fields!");
        }
        else if (!(MeetingTime.getText().isEmpty() || MeetingPlatform.getText().isEmpty())) {
            try{
                if(existingEntries((String) MeetingID.getValue())){
                    SuccessfulMessage.setText("Meeting ID Already Taken. Please chose another one.");
                }
                else {
                    addMeetingDetails();
                }
            }
            catch (Exception exception){

            }
        }
    }
    public void handleClearButton(ActionEvent event) throws Exception {
        clearTextFields();
        SuccessfulMessage.setText("");
    }
    private void clearTextFields() {
        MeetingID.setValue(null);
        MeetingTopic.clear();
        MeetingDate.setValue(null);
        MeetingTime.clear();
        MeetingPlatform.clear();
        MeetingDescription.clear();
    }
    public boolean existingEntries(String existingID){
        boolean idExisting = false;
        for (int i = 0; i < Meetings.meetingDetailsList.size(); i++) {
            if (existingID.equals(Meetings.meetingDetailsList.get(i).get(0))) {

                existingMeeting = Meetings.meetingDetailsList.get(i).get(0) + ", " + Meetings.meetingDetailsList.get(i).get(1) + ", " + Meetings.meetingDetailsList.get(i).get(2) + ", " + Meetings.meetingDetailsList.get(i).get(3) + ", " + Meetings.meetingDetailsList.get(i).get(4) + ", " + Meetings.meetingDetailsList.get(i).get(5);
                idExisting = true;
            }
        }
        return idExisting;
    }
}
