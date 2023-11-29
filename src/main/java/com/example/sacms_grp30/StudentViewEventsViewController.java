package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.ClubSearchModel;
import com.example.sacms_grp30.model.EventSearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentViewEventsViewController implements Initializable {
    @FXML
    private Button registerEventButton;

    @FXML
    private TableView<EventSearchModel> eventDetailsTable;

    @FXML
    private TableColumn<EventSearchModel, String> eventIDColumn;

    @FXML
    private TableColumn<EventSearchModel, String> eventNameColumn;

    @FXML
    private TableColumn<EventSearchModel, String> eventDateColumn;

    @FXML
    private TableColumn<EventSearchModel, String> eventTimeColumn;

    @FXML
    private TableColumn<EventSearchModel, String> eventLocationColumn;

    @FXML
    private TableColumn<EventSearchModel, String> eventDescriptionColumn;

    @FXML
    private TextField eventNameRegisterTxt;

    @FXML
    private Label registerEventMessageLabel;

    @FXML
    private TextField searchKeywordsTxt;

    @FXML
    private Button backButton;

    ObservableList<EventSearchModel> eventSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) { // initializable interface
        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class
        String clubSearchQuery = "SELECT event_id, event_name, event_date, event_time, event_location, event_description FROM events"; // sql query

        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(clubSearchQuery);

            while (rs.next()) {
                String eventID = rs.getString("event_id");
                String eventName = rs.getString("event_name");
                String eventDate = rs.getString("event_date");
                String eventTime = rs.getString("event_time");
                String eventLocation = rs.getString("event_location");
                String eventDescription = rs.getString("event_description");

                // populate the observableList
                eventSearchModelObservableList.add(new EventSearchModel(eventID, eventName, eventDate, eventTime, eventLocation, eventDescription));
            }

            // PropertyValueFactory corresponds to the new clubSearchModel fields
            // The table column is, the one we annotate above
            eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));
            eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("event_time"));
            eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("event_location"));
            eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("event_description"));

            eventDetailsTable.setItems(eventSearchModelObservableList);

            // initial filtered list
            FilteredList<EventSearchModel> filteredData = new FilteredList<>(eventSearchModelObservableList, b -> true);

            searchKeywordsTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(eventSearchModel -> {
                    // if no value, then display all records or whatever records it currently have. no changes
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if(eventSearchModel.getEvent_id().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club_id
                    } else if(eventSearchModel.getEvent_name().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club_name
                    } else if (eventSearchModel.getEvent_date().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club category
                    } else if (eventSearchModel.getEvent_time().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club description
                    } else if (eventSearchModel.getEvent_location().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club category
                    } else if (eventSearchModel.getEvent_description().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club description
                    } else {
                        return false; // no match found
                    }
                });
            });

            SortedList<EventSearchModel> sortedData = new SortedList<>(filteredData);

            // bind sorted result with the table view
            sortedData.comparatorProperty().bind(eventDetailsTable.comparatorProperty());

            // apply filtered and sorted data to the table view
            eventDetailsTable.setItems(sortedData);

        } catch(SQLException e) {
            Logger.getLogger(StudentViewClubViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void backButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateBack(actionEvent);
    }

    private void navigateBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentMenu-view.fxml"));
        newStage.setTitle("Student - Menu");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void registerEventButtonOnClick(ActionEvent actionEvent) throws SQLException {
        navigateRegisterEvent(actionEvent);
    }

    private void navigateRegisterEvent(ActionEvent actionEvent) throws SQLException {
        String studentID = StudentLogInViewController.joinClubStudentID[0];
        String eventIDJoin = eventNameRegisterTxt.getText();

        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class
        Statement statement = connection.createStatement(); // to execute sql queries

        String query2 = "Insert INTO event_student VALUES ("; // to add student details to the database
        System.out.println(query2 + "'" + eventIDJoin + "', '" + studentID + "');");
        statement.executeUpdate(query2 + "'" + eventIDJoin + "', '" + studentID + "');"); // executing the query

        String successfulMessage = "Successfully Registered !!!";
        registerEventMessageLabel.setText(successfulMessage);
    }
}
