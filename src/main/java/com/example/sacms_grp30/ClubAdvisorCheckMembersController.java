package com.example.sacms_grp30;

import com.example.sacms_grp30.tables.TableClubAdvisorCheckMembers;
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

public class ClubAdvisorCheckMembersController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<TableClubAdvisorCheckMembers, String> colEmail;

    @FXML
    private TableColumn<TableClubAdvisorCheckMembers, String> colStudentID;

    @FXML
    private TableColumn<TableClubAdvisorCheckMembers, String> colStudentName;

    @FXML
    private TableView<TableClubAdvisorCheckMembers> tblClubMembers;

    @FXML
    private TextField txtSearchClubName;

    @FXML
    void loadManageClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-manage-club.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Club Management");
        stage.show();

    }

    @FXML
    void searchClubName(ActionEvent event) {

    }

}
