package com.example.sacms_grp30;

import com.example.sacms_grp30.tables.TableClubAdvisorViewClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorViewClubController {
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnback;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colCategory;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colClubAdvisorName;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colClubID;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colClubName;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colDescription;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, Integer> colNoOfStudents;

    @FXML
    private TableView<TableClubAdvisorViewClub> tblViewClub;
    //passing the attributes from the TableClubAdvisorViewClub Object

    @FXML
    void loadManageClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnback.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-menu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Menu");
        stage.show();

    }

    @FXML
    void searchClubName(ActionEvent event) {

    }
}
