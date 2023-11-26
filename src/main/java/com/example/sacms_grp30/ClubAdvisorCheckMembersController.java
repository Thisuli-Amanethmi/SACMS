package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClubAdvisorCheckMembersController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableView<?> tblClubMembers;

    @FXML
    private TextField txtSearchClubName;

    @FXML
    void loadManageClub(ActionEvent event) {

    }

    @FXML
    void searchClubName(ActionEvent event) {

    }

}
