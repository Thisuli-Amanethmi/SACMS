package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.Club;
import com.example.sacms_grp30.model.ClubAdvisor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClubAdvisorCreateClubController implements Initializable {
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
        Club club = new Club();
        club.setClubId("C002");
        club.setClubAdvisorId(txtClubAdvisorID.getText());
        club.setClubName(txtClubName.getText());
        club.setClubCategory(comboClubCategory.getSelectionModel().getSelectedItem());
        club.setDescription(txtDescription.getText());

        Connection connection = DBConnection.getInstance().getConnection();
        String sql="INSERT INTO club VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,club.getClubId());
            preparedStatement.setString(2,club.getClubAdvisorId());
            preparedStatement.setString(3,club.getClubName());
            preparedStatement.setString(4,club.getClubCategory());
            preparedStatement.setString(5,club.getDescription());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add("Academic");
        category.add("Technology");
        category.add("Arts and Creativity");
        category.add("Service");
        category.add("Sports");

        comboClubCategory.setItems(category);

    }
}
