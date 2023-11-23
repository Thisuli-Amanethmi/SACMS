package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private AnchorPane clubAdvisorPane;

    @FXML
    void loadClubAdvisorLogin(ActionEvent event) throws IOException {
        //loadWindows(event, "club-advisor-login.fxml", "Club Advisor - Login");
    }

    @FXML
    void loadClubAdvisorRegister(ActionEvent event) throws IOException {
       /* Parent root = FXMLLoader.load(getClass().getResource("club-advisor-register.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Club Advisor - Registration");
        window.show();*/
        loadWindows(event, "club-advisor-register.fxml", "Club Advisor - Registration");
    }

    @FXML
    void loadHome(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("SACMS-view.fxml"));
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root));
//        window.setTitle("Club Advisor");
//        window.show();
        loadWindows(event,"SACMS-view.fxml","Club Advisor");
    }

    public void loadWindows(ActionEvent event,String fxmlFile,String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle(title);
        window.show();
    }
}


