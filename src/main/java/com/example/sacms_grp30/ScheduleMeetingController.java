package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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

    // Initialization method to set up the ChoiceBox with predefined meeting IDs
    @FXML
    public void initialize() {
        ObservableList<String> MeetingIDs = FXCollections.observableArrayList("MID001", "MID002", "MID003", "MID004", "MID005", "MID006", "MID007", "MID008");
        MeetingID.setItems(MeetingIDs);
    }

    // Event handler for the BackButton, which navigates back to the main event scheduling scene
    public void handleBackButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }

    // Method to add meeting details to the database
    public void addMeetingDetails() {
        // Creating a ScheduledMeetings object with user input
        ScheduledMeetings meeting = new ScheduledMeetings(
                MeetingID.getValue().toString(),
                MeetingTopic.getText(),
                MeetingDate.getValue().toString(),
                MeetingTime.getText(),
                MeetingPlatform.getText(),
                MeetingDescription.getText()
        );
        //Validate the event date to ensure if it's today or a future date
        LocalDate selectedDate = MeetingDate.getValue();
        LocalDate currentDate = LocalDate.now();

        if (selectedDate != null && selectedDate.isBefore(currentDate)) {
            SuccessfulMessage.setText("Meeting Date should be today or a future date.");
            return;
        }
        // Establishing a database connection and inserting the meeting details
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO meetings (meeting_id, meeting_topic, meeting_date, meeting_time, meeting_platform, meeting_description) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, meeting.getMeetingId());
            preparedStatement.setString(2, meeting.getMeetingTopic());
            preparedStatement.setString(3, meeting.getMeetingDate());
            preparedStatement.setString(4, meeting.getMeetingTime());
            preparedStatement.setString(5, meeting.getMeetingPlatform());
            preparedStatement.setString(6, meeting.getMeetingDescription());

            // Executing the SQL query to insert the meeting details
            preparedStatement.executeUpdate();
            SuccessfulMessage.setText("Meeting Scheduled Successfully");
            // Clearing the input fields after successful scheduling
            clearTextFields();
        } catch (SQLException e) {
            // Handling SQL exceptions, including duplicate entry error
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                // Duplicate entry error (SQLState "23000" and ErrorCode 1062)
                SuccessfulMessage.setText("Meeting ID Already Taken. Please choose another one.");
            } else {
                e.printStackTrace();
            }
        }
    }

    // Event handler for the SubmitButton, triggering the addition of meeting details
    public void handleSubmitButton(ActionEvent event) throws Exception {
        // Checking if all required fields are filled before attempting to add meeting details
        if (MeetingID.getValue() == null && MeetingTopic.getText().isEmpty() && MeetingDate.getValue() == null && MeetingTime.getText().isEmpty() && MeetingPlatform.getText().isEmpty() && MeetingDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("All the fields are Empty. Please Fill!");
        } else if (MeetingID.getValue() == null || MeetingTopic.getText().isEmpty() || MeetingDate.getValue() == null || MeetingTime.getText().isEmpty() || MeetingPlatform.getText().isEmpty() || MeetingDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("");
            SuccessfulMessage.setText("Please Fill All the fields!");
            addMeetingDetails();
        } else {
            addMeetingDetails();
        }
    }

    // Event handler for the ClearButton, resetting input fields and clearing success message
    public void handleClearButton(ActionEvent event) throws Exception {
        clearTextFields();
        SuccessfulMessage.setText("");
    }

    // Helper method to clear all input fields
    private void clearTextFields() {
        MeetingID.setValue(null);
        MeetingTopic.clear();
        MeetingDate.setValue(null);
        MeetingTime.clear();
        MeetingPlatform.clear();
        MeetingDescription.clear();
    }
}