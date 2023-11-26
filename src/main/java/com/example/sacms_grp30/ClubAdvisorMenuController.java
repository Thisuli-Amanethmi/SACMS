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
    void backToHome(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnLogout.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor");
        stage.show();

    }

    @FXML
    void createClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnCreateClub.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-create-club.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Club Creation");
        stage.show();
    }

    @FXML
    void manageClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnManageClub.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-manage-club.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Club Management");
        stage.show();
    }

    @FXML
    void scheduleEvent(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnEventSchedule.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Event Scheduling");
        stage.show();
    }

    @FXML
    void viewClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnViewClub.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-view-club.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - View Clubs");
        stage.show();
    }
}
