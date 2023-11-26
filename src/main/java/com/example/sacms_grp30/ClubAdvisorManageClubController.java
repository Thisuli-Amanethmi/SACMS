package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colClubAdvisorName;

    @FXML
    private TableColumn<?, ?> colClubID;

    @FXML
    private TableColumn<?, ?> colClubName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableView<?> tblClub;

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
    void loadCheckMembers(ActionEvent event) {

    }

    @FXML
    void loadClubAdvisorMenu(ActionEvent event) {

    }

    @FXML
    void loadEventSchedule(ActionEvent event) {

    }

    @FXML
    void searchClub(ActionEvent event) {

    }

    @FXML
    void updateClub(ActionEvent event) {

    }
}
