package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
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
import java.sql.*;

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

    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setting the cell value factories to each column of the table
        MeetingId.setCellValueFactory(new PropertyValueFactory<>("meetingId"));
        MeetingTopic.setCellValueFactory(new PropertyValueFactory<>("meetingTopic"));
        MeetingDate.setCellValueFactory(new PropertyValueFactory<>("meetingDate"));
        MeetingTime.setCellValueFactory(new PropertyValueFactory<>("meetingTime"));
        MeetingPlatform.setCellValueFactory(new PropertyValueFactory<>("meetingPlatform"));
        MeetingDescription.setCellValueFactory(new PropertyValueFactory<>("meetingDescription"));

        ObservableList<ScheduledMeetings> list = FXCollections.observableArrayList();

        list.addAll(retrieveAllEvents());

        MeetingTable.setItems(list);
    }
    public static ObservableList<ScheduledMeetings> retrieveAllEvents() {
        ObservableList<ScheduledMeetings> meetingsList = FXCollections.observableArrayList();
        Connection connection = DBConnection.getInstance().getConnection();

        try  {
            String query = "SELECT * FROM meetings";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String meetingId = resultSet.getString("meeting_id");
                        String meetingTopic = resultSet.getString("meeting_Topic");
                        String meetingDate = resultSet.getString("meeting_date");
                        String meetingTime = resultSet.getString("meeting_time");
                        String meetingPlatform = resultSet.getString("meeting_platform");
                        String meetingDescription = resultSet.getString("meeting_description");

                        ScheduledMeetings meeting = new ScheduledMeetings(meetingId, meetingTopic, meetingDate, meetingTime, meetingPlatform, meetingDescription);
                        meetingsList.add(meeting);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meetingsList;
    }
    private void deleteMeeting() {
        String deleteId = DeleteIdField.getText();
        deleteMeeting(deleteId);  // Delete from the database

        // Remove from TableView
        ScheduledMeetings meetingToRemove = null;
        for (ScheduledMeetings meeting : MeetingTable.getItems()) {
            if (meeting.getMeetingId().equals(deleteId)) {
                meetingToRemove = meeting;
                break;
            }
        }
        if (meetingToRemove != null) {
            MeetingTable.getItems().remove(meetingToRemove);
            MessageField.setText("Meeting Deleted Successfully");
        } else {
            MessageField.setText("Meeting not found");
        }
    }
    public static void deleteMeeting(String meetingId) {
        Connection connection = DBConnection.getInstance().getConnection();
        try  {
            String query = "DELETE FROM meetings WHERE meeting_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, meetingId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
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
