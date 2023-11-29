package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.ClubSearchModel;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentViewClubViewController implements Initializable {
    @FXML
    private TableView<ClubSearchModel> clubDetailsTable;

    @FXML
    private TableColumn<ClubSearchModel, String> clubIDColumn;

    @FXML
    private TableColumn<ClubSearchModel, String> clubNameColumn;

    @FXML
    private TableColumn<ClubSearchModel, String> clubCategoryColumn;

    @FXML
    private TableColumn<ClubSearchModel, String> clubDescriptionColumn;

    @FXML
    private TextField clubNameJoinTxt;

    @FXML
    private Label joinClubMessageLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button joinClubButton;

    @FXML
    private TextField searchKeywordsTxt;

    ObservableList<ClubSearchModel> clubSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) { // initializable interface
        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class
        String clubSearchQuery = "SELECT club_id, club_name, club_category, description FROM club;"; // sql query

        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(clubSearchQuery);

            while (rs.next()) {
                String clubID = rs.getString("club_id");
                String clubName = rs.getString("club_name");
                String clubCategory = rs.getString("club_category");
                String clubDescription = rs.getString("description");

                // populate the observableList
                clubSearchModelObservableList.add(new ClubSearchModel(clubID, clubName, clubCategory, clubDescription));
            }

            // PropertyValueFactory corresponds to the new clubSearchModel fields
            // The table column is, the one we annotate above
            clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("club_id"));
            clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("club_name"));
            clubCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("club_category"));
            clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            clubDetailsTable.setItems(clubSearchModelObservableList);

            // initial filtered list
            FilteredList<ClubSearchModel> filteredData = new FilteredList<>(clubSearchModelObservableList, b -> true);

            searchKeywordsTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(clubSearchModel -> {
                    // if no value, then display all records or whatever records it currently have. no changes
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                     return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if(clubSearchModel.getClub_id().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club_id
                    } else if(clubSearchModel.getClub_name().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club_name
                    } else if (clubSearchModel.getClub_category().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club category
                    } else if (clubSearchModel.getDescription().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // means we found a match club description
                    } else {
                        return false; // no match found
                    }
                });
            });

            SortedList<ClubSearchModel> sortedData = new SortedList<>(filteredData);

            // bind sorted result with the table view
            sortedData.comparatorProperty().bind(clubDetailsTable.comparatorProperty());

            // apply filtered and sorted data to the table view
            clubDetailsTable.setItems(sortedData);

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

    public void joinClubButtonOnClick(ActionEvent actionEvent) throws Exception {
        String successfulMessage = "Successfully Joined !!!";
        joinClubMessageLabel.setText(successfulMessage);

    }

}
