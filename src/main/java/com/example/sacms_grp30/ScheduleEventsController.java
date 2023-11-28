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
        ObservableList<String> eventIDs = FXCollections.observableArrayList("EID001", "EID002", "EID003", "EID004", "EID005", "EID006", "EID007", "EID008");
        EventID.setItems(eventIDs);
    }


    public void handleBackButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }

    public void addEventDetails() {
        ArrayList event = new ArrayList();

        event.add(EventID.getValue().toString());
        event.add(EventName.getText());
        event.add(EventDate.getValue().toString());
        event.add(EventTime.getText());
        event.add(EventLocation.getText());
        event.add(EventDescription.getText());
        Events.eventDetailsList.add(event);
        SuccessfulMessage.setText("Event Scheduled Successfully");
        clearTextFields();
    }

    String existingEvent;

    public void handleSubmitButton(ActionEvent event) throws Exception {
        if (EventID.getValue() == null && EventName.getText().isEmpty() && EventDate.getValue() == null && EventTime.getText().isEmpty() && EventLocation.getText().isEmpty() && EventDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("All the fields are Empty. Please Fill!");
        } else if (EventID.getValue() == null || EventName.getText().isEmpty() || EventDate.getValue() == null || EventTime.getText().isEmpty() || EventLocation.getText().isEmpty() || EventDescription.getText().isEmpty()) {
            SuccessfulMessage.setText("");
            SuccessfulMessage.setText("Please Fill All the fields!");
        } else if (!(EventTime.getText().isEmpty() || EventLocation.getText().isEmpty())) {
            try {
                if (existingEntries((String) EventID.getValue())) {
                    SuccessfulMessage.setText("Event ID Already Taken. Please chose another one.");
                } else {
                    addEventDetails();
                }
            } catch (Exception exception) {

            }
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

    public boolean existingEntries(String existingID) {
        boolean idExisting = false;
        for (int i = 0; i < Events.eventDetailsList.size(); i++) {
            if (existingID.equals(Events.eventDetailsList.get(i).get(0))) {

                existingEvent = Events.eventDetailsList.get(i).get(0) + ", " + Events.eventDetailsList.get(i).get(1) + ", " + Events.eventDetailsList.get(i).get(2) + ", " + Events.eventDetailsList.get(i).get(3) + ", " + Events.eventDetailsList.get(i).get(4) + ", " + Events.eventDetailsList.get(i).get(5);
                idExisting = true;
            }
        }
        return idExisting;
    }

}


