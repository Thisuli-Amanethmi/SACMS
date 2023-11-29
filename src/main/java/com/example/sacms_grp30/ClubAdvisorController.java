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
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //when login set the window title to this
        stage.setTitle("Club Advisor - Login");
        stage.show();
    }

    @FXML
    void loadClubAdvisorRegister(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-register.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //when login set the window title to this
        stage.setTitle("Club Advisor - Register");
        stage.show();
    }

    @FXML
    void loadHome(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("SACMS-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //when login set the window title to this
        stage.setTitle("SACMS");
        stage.show();
    }
}


