package com.example.scams_cm2601_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentMenuViewController {
    @FXML
    private Button backButton;

    @FXML
    private Button selectClubButton;

    @FXML
    private Button viewEventsButton;

    @FXML
    private Button joinMeetingButton;

    public void backButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateBack(actionEvent);
    }

    private void navigateBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SACMS-view.fxml"));
        newStage.setTitle("SCAMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void selectClubButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateClubs(actionEvent);
    }

    private void navigateClubs(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentViewClub-view.fxml"));
        newStage.setTitle("SCAMS - Clubs");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void viewEventsButtonOnClick(ActionEvent actionEvent) throws Exception {

    }

    public void joinMeetingButtonOnClick(ActionEvent actionEvent) throws Exception {

    }

    public void viewProfileButtonOnClick(ActionEvent actionEvent) throws Exception {
    }
}
