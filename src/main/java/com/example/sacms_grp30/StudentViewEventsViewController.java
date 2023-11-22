package com.example.sacms_grp30;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class StudentViewEventsViewController {
    @FXML
    private Button registerEventButton;

    @FXML
    private TableView eventDetailsTable;

    @FXML
    private TableColumn eventIDColumn;

    @FXML
    private TableColumn eventNameColumn;

    @FXML
    private TableColumn clubNameColumn;

    @FXML
    private TableColumn eventDateColumn;

    @FXML
    private TableColumn eventTimeColumn;

    @FXML
    private TableColumn eventLocationColumn;

    @FXML
    private TextField eventNameRegisterTxt;

    @FXML
    private Label registerEventMessageLabel;

    @FXML
    private TextField searchKeywordsTxt;

    @FXML
    private Button backButton;


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
        registerEventMessageLabel.setText(successfulMessage);
    }
}
