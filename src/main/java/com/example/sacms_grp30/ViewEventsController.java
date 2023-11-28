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

public class ViewEventsController implements Initializable {


    @FXML
    private Button BackButton;
    @FXML
    private Button DeleteButton;
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
    Button FindButton;
    @FXML
    Button RefreshButton;
    @FXML
    Button NewEventButton;
    String foundEvent;

    public void handleBackButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 500));
    }

//    @FXML
//    private void deleteData(ActionEvent event) {
//        TableView.TableViewSelectionModel<ScheduledEvents> selectionModel = Table.getSelectionModel();
//        if (selectionModel.isEmpty()) {
//            System.out.println("You need to select a data before deleting.");
//            return; // Exit the method if nothing is selected
//        }
//
//        ObservableList<ScheduledEvents> selectedItems = selectionModel.getSelectedItems();
//        Table.getItems().removeAll(selectedItems);
//        Events.eventDetailsList.removeAll(selectedItems);
//
//
//        // Clear the selection after deletion
//        selectionModel.clearSelection();
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting the cell value factories to each column of the table
        EventID.setCellValueFactory(new PropertyValueFactory<ScheduledEvents, String>("eventId"));
        EventName.setCellValueFactory(new PropertyValueFactory<ScheduledEvents, String>("eventName"));
        EventDate.setCellValueFactory(new PropertyValueFactory<ScheduledEvents, String>("eventDate"));
        EventTime.setCellValueFactory(new PropertyValueFactory<ScheduledEvents, String>("eventTime"));
        EventLocation.setCellValueFactory(new PropertyValueFactory<ScheduledEvents, String>("eventLocation"));
        EventDescription.setCellValueFactory(new PropertyValueFactory<ScheduledEvents, String>("eventDescription"));

        ObservableList<ScheduledEvents> list = FXCollections.observableArrayList(); //ObservableList to store data

        for (int i = 0; i < Events.eventDetailsList.size(); i++) {
            list.addAll(new ScheduledEvents(
                    (String) Events.eventDetailsList.get(i).get(0),
                    (String) Events.eventDetailsList.get(i).get(1),
                    (String) Events.eventDetailsList.get(i).get(2),
                    (String) Events.eventDetailsList.get(i).get(3),
                    (String) Events.eventDetailsList.get(i).get(4),
                    (String) Events.eventDetailsList.get(i).get(5)));
        }
        Table.setItems(list);

    }

    private void deleteEvent() {
        for (int i = 0; i < Events.eventDetailsList.size(); i++) {
            if (DeleteIdField.getText().equals(Events.eventDetailsList.get(i).get(0))) {
                Events.eventDetailsList.remove(i);
                MessageField.setText("");
                MessageField.setText("Event Deleted Successfully, Click the Refresh Button");
            }
        }
    }

    public boolean findEvent(String findEventId) {
        boolean eventFound = false;
        for (int i = 0; i < Events.eventDetailsList.size(); i++) {
            if (findEventId.equals(Events.eventDetailsList.get(i).get(0))) {
                foundEvent = Events.eventDetailsList.get(i).get(0) + " , " + Events.eventDetailsList.get(i).get(1) + " , " + Events.eventDetailsList.get(i).get(2) + " , " + Events.eventDetailsList.get(i).get(3) + " , " + Events.eventDetailsList.get(i).get(4)+ " , " + Events.eventDetailsList.get(i).get(5);
                eventFound = true;
            }
        }
        return eventFound;
    }

    public void deleteFind(ActionEvent actionEvent) throws Exception {
        if (DeleteIdField.getText().isEmpty()) {
            MessageField.setText("");
            MessageField.setText("Enter the Event ID to delete");
        } else {
            if (findEvent(DeleteIdField.getText())) {
                MessageField.setText("");
                MessageField.setText("Event Found");
            } else {
                MessageField.setText("");
                MessageField.setText("Event not found");
            }
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