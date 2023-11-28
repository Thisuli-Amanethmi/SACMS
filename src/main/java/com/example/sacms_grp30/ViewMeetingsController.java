package com.example.sacms_grp30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewMeetingsController implements Initializable {


    @FXML
    private javafx.scene.control.Button BackButton;
    @FXML
    private TableColumn<ScheduledMeetings, String> MeetingId;
    @FXML
    private TableColumn<ScheduledMeetings, String> MeetingTopic;
    @FXML
    private TableColumn<ScheduledMeetings, String> MeetingDate;
    @FXML
    private TableColumn<ScheduledMeetings, String> MeetingTime;
    @FXML
    private TableView<ScheduledMeetings> MeetingTable;
    @FXML
    private TableColumn<ScheduledMeetings, String> MeetingPlatform;
    @FXML
    private TableColumn<ScheduledMeetings, String> MeetingDescription;
    @FXML
    TextField DeleteIdField;
    @FXML
    Label MessageField;
    @FXML
    Button FindButton;
    @FXML
    Button RefreshButton;
    @FXML
    Button NewMeetingButton;
    String foundMeeting;

    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting the cell value factories to each column of the table
        MeetingId.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingId"));
        MeetingTopic.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingTopic"));
        MeetingDate.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingDate"));
        MeetingTime.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingTime"));
        MeetingPlatform.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingPlatform"));
        MeetingDescription.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingDescription"));

        ObservableList<ScheduledMeetings> list1 = FXCollections.observableArrayList();

        for (int i = 0; i < Meetings.meetingDetailsList.size(); i++) {
            list1.addAll(new ScheduledMeetings(
                    (String) Meetings.meetingDetailsList.get(i).get(0),
                    (String) Meetings.meetingDetailsList.get(i).get(1),
                    (String) Meetings.meetingDetailsList.get(i).get(2),
                    (String) Meetings.meetingDetailsList.get(i).get(3),
                    (String) Meetings.meetingDetailsList.get(i).get(4),
                    (String) Meetings.meetingDetailsList.get(i).get(5)));
        }
        MeetingTable.setItems(list1);
    }
    private void deleteMeeting() {
        for (int i = 0; i < Meetings.meetingDetailsList.size(); i++) {
            if (DeleteIdField.getText().equals(Meetings.meetingDetailsList.get(i).get(0))) {
                Meetings.meetingDetailsList.remove(i);
                MessageField.setText("");
                MessageField.setText("Meeting Deleted Successfully, Click the Refresh Button");
            }
        }
    }
    public boolean findMeeting(String findMeetingId) {
        boolean meetingFound = false;
        for (int i = 0; i < Meetings.meetingDetailsList.size(); i++) {
            if (findMeetingId.equals(Meetings.meetingDetailsList.get(i).get(0))) {
                foundMeeting = Meetings.meetingDetailsList.get(i).get(0) + " , " + Meetings.meetingDetailsList.get(i).get(1) + " , " + Meetings.meetingDetailsList.get(i).get(2) + " , " + Meetings.meetingDetailsList.get(i).get(3) + " , " + Meetings.meetingDetailsList.get(i).get(4)+ " , " + Meetings.meetingDetailsList.get(i).get(5);
                meetingFound = true;
            }
        }
        return meetingFound;
    }
    public void deleteFind(ActionEvent actionEvent) throws Exception {
        if (DeleteIdField.getText().isEmpty()) {
            MessageField.setText("");
            MessageField.setText("Enter the Meeting ID to delete");
        } else {
            if (findMeeting(DeleteIdField.getText())) {
                MessageField.setText("");
                MessageField.setText("Meeting Found");
            } else {
                MessageField.setText("");
                MessageField.setText("Meeting not found");
            }
        }
    }
    public void deleteSubmit(ActionEvent event) throws Exception {
        deleteMeeting();
    }

    public void handleRefreshButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ViewMeetings.fxml"));
        Stage window = (Stage) RefreshButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }
    public void handleNewEventButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ScheduleEventsScene.fxml"));
        Stage window = (Stage) NewMeetingButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
}
