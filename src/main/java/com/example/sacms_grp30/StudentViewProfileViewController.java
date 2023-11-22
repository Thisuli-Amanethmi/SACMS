package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StudentViewProfileViewController {
    @FXML
    private Button backButton;

    @FXML
    private Button viewDetailsButton;

    @FXML
    private Label studentIDLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label studentEmailLabel;

    @FXML
    private Label studentRegisteredEventIDLabel;

    @FXML
    private Label studentRegisteredMeetingIDLabel;

    @FXML
    private Label studentRegisteredClubIDLabel;

    public void backButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateBack(actionEvent);
    }

    private void navigateBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentMenu-view.fxml"));
        newStage.setTitle("Student - Menu");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void viewDetailsButtonOnClick(ActionEvent actionEvent) {

    }
}
