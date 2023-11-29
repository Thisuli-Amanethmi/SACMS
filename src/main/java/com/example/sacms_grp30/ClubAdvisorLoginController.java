package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorLoginController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtClubAdvisorID;

    @FXML
    private TextField txtPassword;

    @FXML
    void loadClubAdvisorHome(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor");
        stage.show();
    }

    @FXML
    void loadClubAdvisorMenu(ActionEvent event) {

    }

}
