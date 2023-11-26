package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClubAdvisorViewClubController {
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnback;

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
    private TableColumn<?, ?> colNoOfStudents;

    @FXML
    private TableView<?> tblViewClub;

    @FXML
    void loadManageClub(ActionEvent event) {

    }

    @FXML
    void searchClubName(ActionEvent event) {

    }
}
