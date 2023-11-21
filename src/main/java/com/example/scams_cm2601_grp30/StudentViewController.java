package com.example.scams_cm2601_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentViewController {
    @FXML
    private Button homeButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    public void homeButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateHome(actionEvent);
    }

    private void navigateHome(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SACMS-view.fxml"));
        newStage.setTitle("SCAMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void loginButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateLogin(actionEvent);
    }

    private void navigateLogin(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentLogIn-view.fxml"));
        newStage.setTitle("Student - Log In SACMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void registerSACMSButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateRegister(actionEvent);
    }

    private void navigateRegister(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentRegister-view.fxml"));
        newStage.setTitle("Student - Register SACMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
