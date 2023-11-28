package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubAdvisorConfirmationViewController {
    @FXML
    private PasswordField passwordClubPassword;

    @FXML
    private Label errorMessage;

    @FXML
    private Button enterButton;

    @FXML
    private Button closeButton;

    String password = "CA_1234.";

    public void enterButtonOnClick(ActionEvent actionEvent) throws Exception {
        if(passwordClubPassword.getText().equals("CA_1234.")) {
            navigateEnter(actionEvent);
        } else {
            passwordClubPassword.clear();
            errorMessage.setText("Password Is Incorrect !!!");
        }
    }

    private void navigateEnter(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor.fxml"));
        newStage.setTitle("SCAMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void closeButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateClose(actionEvent);
    }

    private void navigateClose(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SACMS-view.fxml"));
        newStage.setTitle("SCAMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
