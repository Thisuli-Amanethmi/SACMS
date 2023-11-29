package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.EventSearchModel;
import com.example.sacms_grp30.model.MeetingSearchModel;
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

public class StudentViewMeetingsViewController implements Initializable {
    @FXML
    private Button backButton;

    @FXML
    private TextField searchKeywordsTxt;

    @FXML
    private Button registerMeetingButton;

    @FXML
    private TableView<MeetingSearchModel> meetingDetailsTable;

    @FXML
    private TableColumn<MeetingSearchModel, String> MeetingIDColumn;

    @FXML
    private TableColumn<MeetingSearchModel, String> meetingTopicColumn;

    @FXML
    private TableColumn<MeetingSearchModel, String> meetingDateColumn;

    @FXML
    private TableColumn<MeetingSearchModel, String> meetingPlatformColumn;

    @FXML
    private TableColumn<MeetingSearchModel, String> meetingTimeColumn;

    @FXML
    private TableColumn<MeetingSearchModel, String> meetingDescriptionColumn;

    @FXML
    private TextField eventNameRegisterTxt;

    @FXML
    private Label registerMeetingMessageLabel;

    ObservableList<MeetingSearchModel> meetingSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) { // initializable interface
        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class
        String meetingSearchQuery = "SELECT meeting_id, meeting_topic, meeting_date, meeting_time, meeting_platform, meeting_description FROM meetings;"; // sql query

        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(meetingSearchQuery);

            while (rs.next()) {
                String meetingID = rs.getString("meeting_id");
                String meetingTopic = rs.getString("meeting_topic");
                String meetingDate = rs.getString("meeting_date");
                String meetingTime = rs.getString("meeting_time");
                String meetingPlatform = rs.getString("meeting_platform");
                String meetingDescription = rs.getString("meeting_description");

                // populate the observableList
                meetingSearchModelObservableList.add(new MeetingSearchModel(meetingID, meetingTopic, meetingDate, meetingTime, meetingPlatform, meetingDescription));
            }

            // PropertyValueFactory corresponds to the new clubSearchModel fields
            // The table column is, the one we annotate above
            MeetingIDColumn.setCellValueFactory(new PropertyValueFactory<>("meeting_id"));
            meetingTopicColumn.setCellValueFactory(new PropertyValueFactory<>("meeting_topic"));
            meetingDateColumn.setCellValueFactory(new PropertyValueFactory<>("meeting_date"));
            meetingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("meeting_time"));
            meetingPlatformColumn.setCellValueFactory(new PropertyValueFactory<>("meeting_platform"));
            meetingDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("meeting_description"));

            meetingDetailsTable.setItems(meetingSearchModelObservableList);

            // initial filtered list
            FilteredList<MeetingSearchModel> filteredData = new FilteredList<>(meetingSearchModelObservableList, b -> true);

            searchKeywordsTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(meetingSearchModel -> {
                    // if no value, then display all records or whatever records it currently have. no changes
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if(meetingSearchModel.getMeeting_date().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club_id
                    } else if(meetingSearchModel.getMeeting_topic().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club_name
                    } else if (meetingSearchModel.getMeeting_date().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club category
                    } else if (meetingSearchModel.getMeeting_time().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club description
                    } else if (meetingSearchModel.getMeeting_platform().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club category
                    } else if (meetingSearchModel.getMeeting_description().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club description
                    } else {
                        return false; // no match found
                    }
                });
            });

            SortedList<MeetingSearchModel> sortedData = new SortedList<>(filteredData);

            // bind sorted result with the table view
            sortedData.comparatorProperty().bind(meetingDetailsTable.comparatorProperty());

            // apply filtered and sorted data to the table view
            meetingDetailsTable.setItems(sortedData);

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

    public void registerEventButtonOnClick(ActionEvent actionEvent) {
        String successfulMessage = "Successfully Registered !!!";
        registerMeetingMessageLabel.setText(successfulMessage);
    }
}
