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

public class ViewEventsController implements Initializable {


    @FXML
    private javafx.scene.control.Button BackButton;
    @FXML
    private javafx.scene.control.Button DeleteButton;
    @FXML
    private TableColumn<ScheduledEvents, String> EventID;
    @FXML
    private TableColumn<ScheduledEvents, String> EventName;
    @FXML
    private TableColumn<ScheduledEvents, String> EventDate;
    @FXML
    private TableColumn<ScheduledEvents, String> EventTime;
    @FXML
    private TableView<ScheduledEvents> Table;
    @FXML
    private TableColumn<ScheduledEvents, String> EventLocation;
    @FXML
    private TableColumn<ScheduledEvents, String> EventDescription;
    @FXML
    TextField DeleteIdField;
    @FXML
    Label MessageField;
    @FXML
    Button RefreshButton;
    @FXML
    Button NewEventButton;

    public void handleBackButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setting the cell value factories to each column of the table
        EventID.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        EventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        EventDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        EventTime.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        EventLocation.setCellValueFactory(new PropertyValueFactory<>("eventLocation"));
        EventDescription.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));

        ObservableList<ScheduledEvents> list = FXCollections.observableArrayList();

        list.addAll(retrieveAllEvents());

        Table.setItems(list);
    }

    public static ObservableList<ScheduledEvents> retrieveAllEvents() {
        ObservableList<ScheduledEvents> eventsList = FXCollections.observableArrayList();
        Connection connection = DBConnection.getInstance().getConnection();

        try  {
            String query = "SELECT * FROM events";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String eventId = resultSet.getString("event_id");
                        String eventName = resultSet.getString("event_name");
                        String eventDate = resultSet.getString("event_date");
                        String eventTime = resultSet.getString("event_time");
                        String eventLocation = resultSet.getString("event_location");
                        String eventDescription = resultSet.getString("event_description");

                        ScheduledEvents event = new ScheduledEvents(eventId, eventName, eventDate, eventTime, eventLocation, eventDescription);
                        eventsList.add(event);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventsList;
    }

    private void deleteEvent() {
        String deleteId = DeleteIdField.getText();
        deleteEvent(deleteId);  // Delete from the database

        // Remove from TableView
        ScheduledEvents eventToRemove = null;
        for (ScheduledEvents event : Table.getItems()) {
            if (event.getEventId().equals(deleteId)) {
                eventToRemove = event;
                break;
            }
        }
        if (eventToRemove != null) {
            Table.getItems().remove(eventToRemove);
            MessageField.setText("Event Deleted Successfully");
        } else {
            MessageField.setText("Event not found");
        }
    }
    public static void deleteEvent(String eventId) {
        Connection connection = DBConnection.getInstance().getConnection();
        try  {
            String query = "DELETE FROM events WHERE event_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, eventId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    public void deleteSubmit(ActionEvent event) throws Exception {
        deleteEvent();
    }

    public void handleRefreshButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ViewEvents.fxml"));
        Stage window = (Stage) RefreshButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }
    public void handleNewEventButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ScheduleEventsScene.fxml"));
        Stage window = (Stage) NewEventButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
}