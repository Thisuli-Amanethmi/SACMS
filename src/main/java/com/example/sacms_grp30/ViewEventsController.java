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

public class ViewEventsController implements Initializable {


    @FXML
    private javafx.scene.control.Button BackButton;
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

    public void handleBackButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting the cell value factories to each column of the table
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
                    (String) Events.eventDetailsList.get(i).get(4)));
        }
        Table.setItems(list); //Setting the sorted data to the Table
    }
}
