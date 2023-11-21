package com.example.scams_cm2601_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentRegisterViewController {
    @FXML
    private TextField IDTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button backButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button logInButton;

    @FXML
    private Label messageBox;

    public void backButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateBack(actionEvent);
    }

    private void navigateBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Student-view.fxml"));
        newStage.setTitle("SCAMS - Student");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void registerButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateRegister(actionEvent);
    }

    private void navigateRegister(ActionEvent actionEvent) throws Exception {
        // check conditions
        String messageSuccessful = "Successfully Registered !!!";
        String messageInputInvalid = "Enter Valid Data !!!";
        String messageAlreadyRegister = "You have already registered.";
        messageBox.setText(messageSuccessful);

    }

    public void logInButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateLogIn(actionEvent);
    }

    private void navigateLogIn(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentLogIn-view.fxml"));
        newStage.setTitle("Student - LogIn SACMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
