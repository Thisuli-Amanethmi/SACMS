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


public class ScheduleEventsController {
    @FXML
    private Button BackButton;
    @FXML
    private Button SubmitButton;
    @FXML
    private Button ClearButton;
    @FXML
    private ChoiceBox EventID;
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

    @FXML
    public void initialize() {
        ObservableList<String> eventIDs = FXCollections.observableArrayList("EID001", "EID002", "EID003", "EID004", "EID005", "EID006", "EID007", "EID008","EID009","EID010");
        EventID.setItems(eventIDs);
    }


    public void handleBackButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }

    public void addEventDetails() {
        ScheduledEvents event = new ScheduledEvents(
                EventID.getValue().toString(),
                EventName.getText(),
                EventDate.getValue().toString(),
                EventTime.getText(),
                EventLocation.getText(),
                EventDescription.getText()
        );

        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO events (event_id, event_name, event_date, event_time, event_location, event_description) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, event.getEventId());
            preparedStatement.setString(2, event.getEventName());
            preparedStatement.setString(3, event.getEventDate());
            preparedStatement.setString(4, event.getEventTime());
            preparedStatement.setString(5, event.getEventLocation());
            preparedStatement.setString(6, event.getEventDescription());

            preparedStatement.executeUpdate();
            SuccessfulMessage.setText("Event Scheduled Successfully");
            clearTextFields();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                // Duplicate entry error (SQLState "23000" and ErrorCode 1062)
                SuccessfulMessage.setText("Event ID Already Taken. Please choose another one.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void handleSubmitButton(ActionEvent event) throws Exception {
        if (EventID.getValue() == null && EventName.getText().isEmpty() && EventDate.getValue() == null && EventTime.getText().isEmpty() && EventLocation.getText().isEmpty() && EventDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("All the fields are Empty. Please Fill!");
        } else if (EventID.getValue() == null || EventName.getText().isEmpty() || EventDate.getValue() == null || EventTime.getText().isEmpty() || EventLocation.getText().isEmpty() || EventDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("");
            SuccessfulMessage.setText("Please Fill All the fields!");
            addEventDetails();
        } else{
            addEventDetails();
        }
    }
    public void handleClearButton(ActionEvent event) throws Exception {
        clearTextFields();
        SuccessfulMessage.setText("");
    }

    private void clearTextFields() {
        EventID.setValue(null);
        EventName.clear();
        EventDate.setValue(null);
        EventTime.clear();
        EventLocation.clear();
        EventDescription.clear();
    }
}


