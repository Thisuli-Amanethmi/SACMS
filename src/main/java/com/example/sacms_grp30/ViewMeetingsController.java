package com.example.sacms_grp30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewMeetingsController implements Initializable {


    @FXML
    private javafx.scene.control.Button BackButton;
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

    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting the cell value factories to each column of the table
        MeetingTopic.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingTopic"));
        MeetingDate.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingDate"));
        MeetingTime.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingTime"));
        MeetingPlatform.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingPlatform"));
        MeetingDescription.setCellValueFactory(new PropertyValueFactory<ScheduledMeetings, String>("meetingDescription"));

        ObservableList<ScheduledMeetings> list1 = FXCollections.observableArrayList(); //ObservableList to store data

        for (int i = 0; i < Meetings.meetingDetailsList.size(); i++) {
            list1.addAll(new ScheduledMeetings(
                    (String) Meetings.meetingDetailsList.get(i).get(0),
                    (String) Meetings.meetingDetailsList.get(i).get(1),
                    (String) Meetings.meetingDetailsList.get(i).get(2),
                    (String) Meetings.meetingDetailsList.get(i).get(3),
                    (String) Meetings.meetingDetailsList.get(i).get(4)));
        }
        MeetingTable.setItems(list1); //Setting the sorted data to the Table
    }
}

