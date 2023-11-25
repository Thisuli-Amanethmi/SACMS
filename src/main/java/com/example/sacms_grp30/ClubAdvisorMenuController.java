package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorMenuController {
    @FXML
    private Button btnCreateClub;

    @FXML
    private Button btnEventSchedule;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageClub;

    @FXML
    private Button btnViewClub;

    @FXML
    void backToHome(ActionEvent event) {

    }

    @FXML
    void createClub(ActionEvent event) {

    }

    @FXML
    void manageClub(ActionEvent event) {

    }

    @FXML
    void scheduleEvent(ActionEvent event) {
        //loadWindows(event, "club-advisor-register.fxml", "Club Advisor - Registration");

    }

    @FXML
    void viewClub(ActionEvent event) {

    }

    /*public void loadWindows(ActionEvent event,String fxmlFile,String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle(title);
        window.show();
    }*/
}
