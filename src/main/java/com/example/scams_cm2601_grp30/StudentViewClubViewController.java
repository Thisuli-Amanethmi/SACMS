package com.example.scams_cm2601_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentViewClubViewController {
    @FXML
    private Button backButton;

    @FXML
    private Button joinClubButton;

    @FXML
    private TextField searchKeywordsTxt;

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

    public void joinClubButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateJoinClub(actionEvent);
    }

    private void navigateJoinClub(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentJoinClub-view.fxml"));
        newStage.setTitle("Student - Join Club");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
