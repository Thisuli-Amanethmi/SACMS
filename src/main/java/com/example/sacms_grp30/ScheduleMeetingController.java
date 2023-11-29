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
        ObservableList<String> MeetingIDs = FXCollections.observableArrayList("MID001", "MID002", "MID003", "MID004", "MID005", "MID006", "MID007", "MID008");
        MeetingID.setItems(MeetingIDs);
    }

    public void handleBackButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }

    public void addMeetingDetails() {
        ScheduledMeetings meeting = new ScheduledMeetings(
                MeetingID.getValue().toString(),
                MeetingTopic.getText(),
                MeetingDate.getValue().toString(),
                MeetingTime.getText(),
                MeetingPlatform.getText(),
                MeetingDescription.getText()
        );
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO meetings (meeting_id, meeting_topic, meeting_date, meeting_time, meeting_platform, meeting_description) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, meeting.getMeetingId());
            preparedStatement.setString(2, meeting.getMeetingTopic());
            preparedStatement.setString(3, meeting.getMeetingDate());
            preparedStatement.setString(4, meeting.getMeetingTime());
            preparedStatement.setString(5, meeting.getMeetingPlatform());
            preparedStatement.setString(6, meeting.getMeetingDescription());

            preparedStatement.executeUpdate();
            SuccessfulMessage.setText("Meeting Scheduled Successfully");
            clearTextFields();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                // Duplicate entry error (SQLState "23000" and ErrorCode 1062)
                SuccessfulMessage.setText("Meeting ID Already Taken. Please choose another one.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void handleSubmitButton(ActionEvent event) throws Exception {
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
}