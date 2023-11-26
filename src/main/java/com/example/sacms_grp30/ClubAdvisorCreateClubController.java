package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorCreateClubController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> comboClubCategory;

    @FXML
    private TextField txtClubAdvisorID;

    @FXML
    private TextField txtClubName;

    @FXML
    private TextField txtDescription;

    @FXML
    void clearData(ActionEvent event) {

    }

    @FXML
    void loadMainMenu(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-menu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //when login set the window title to this
        stage.setTitle("Club Advisor - Menu");
        stage.show();

    }

    @FXML
    void saveData(ActionEvent event) {

    }
}
