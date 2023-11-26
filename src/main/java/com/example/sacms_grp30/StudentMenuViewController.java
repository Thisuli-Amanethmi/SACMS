package com.example.sacms_grp30;

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

    public void logOutButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateLogOut(actionEvent);
    }

    private void navigateLogOut(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SACMS-view.fxml"));
        newStage.setTitle("SCAMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void selectClubButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateViewClubs(actionEvent);
    }

    private void navigateViewClubs(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentViewClub-view.fxml"));
        newStage.setTitle("SCAMS - Clubs");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void viewEventsButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateViewEvents(actionEvent);
    }

    private void navigateViewEvents(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentViewEvents-view.fxml"));
        newStage.setTitle("SCAMS - Events");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void joinMeetingButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateViewMeetings(actionEvent);
    }

    private void navigateViewMeetings(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentViewMeetings-view.fxml"));
        newStage.setTitle("SCAMS - Meetings");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void viewProfileButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateViewStudentProfile(actionEvent);
    }

    private void navigateViewStudentProfile(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentViewProfile-view.fxml"));
        newStage.setTitle("SACMS - Student Profile");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
