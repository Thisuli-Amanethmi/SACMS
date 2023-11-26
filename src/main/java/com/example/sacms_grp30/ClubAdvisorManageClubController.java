package com.example.sacms_grp30;

import com.example.sacms_grp30.tables.TableClubAdvisorManageClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorManageClubController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnCheckMembers;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEventScheduling;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colCategory;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colClubAdvisorName;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colClubID;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colClubName;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colDescription;

    @FXML
    private TableView<TableClubAdvisorManageClub> tblClub;

    @FXML
    private TextField txtClubAdvisorID;

    @FXML
    private TextField txtClubID;

    @FXML
    private TextField txtClubName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSearchClubName;

    @FXML
    void deleteClub(ActionEvent event) {

    }

    @FXML
    void loadCheckMembers(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnCheckMembers.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-check-members.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Members");
        stage.show();

    }

    @FXML
    void loadClubAdvisorMenu(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-menu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Menu");
        stage.show();

    }

    @FXML
    void loadEventSchedule(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnEventScheduling.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Event Scheduling");
        stage.show();

    }

    @FXML
    void searchClub(ActionEvent event) {

    }

    @FXML
    void updateClub(ActionEvent event) {

    }
}
