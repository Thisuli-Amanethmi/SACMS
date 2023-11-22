package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class StudentViewMeetingsViewController {
    @FXML
    private Button backButton;

    @FXML
    private TextField searchKeywordsTxt;

    @FXML
    private Button registerMeetingButton;

    @FXML
    private TableView meetingDetailsTable;

    @FXML
    private TableColumn MeetingIDColumn;

    @FXML
    private TableColumn meetingNameColumn;

    @FXML
    private TableColumn meetingTopicColumn;

    @FXML
    private TableColumn meetingDateColumn;

    @FXML
    private TableColumn meetingPlatformColumn;

    @FXML
    private TableColumn meetingTimeColumn;

    @FXML
    private TextField eventNameRegisterTxt;

    @FXML
    private Label registerMeetingMessageLabel;

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

    public void registerEventButtonOnClick(ActionEvent actionEvent) {
        String successfulMessage = "Successfully Registered !!!";
        registerMeetingMessageLabel.setText(successfulMessage);
    }
}
