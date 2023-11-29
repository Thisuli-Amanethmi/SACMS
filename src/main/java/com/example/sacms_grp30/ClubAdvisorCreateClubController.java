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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        txtClubAdvisorID.clear();
        txtDescription.clear();
        txtClubName.clear();
        comboClubCategory.getSelectionModel().clearSelection();

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

        if(!isDataFilled()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please fill all details !");
            alert.show();
            return;
        }

        if(getClubAdvisor(txtClubAdvisorID.getText())==null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"This Club Advisor does not exist !");
            alert.show();
            return;
        }

        if(isClubAdvisorHasClub(txtClubAdvisorID.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR,"This Club Advisor has a club already !");
            alert.show();
            return;
        }

        Club club = new Club();
        club.setClubId(autoGenerateClubID());
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

            new Alert(Alert.AlertType.INFORMATION,"Club added successfully.").show();
            clearData(event);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ClubAdvisor getClubAdvisor(String clubAdvisorId) {
        String sql="SELECT * FROM club_advisor";
        //session between java application and the database
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //maintains a cursor pointing to the table
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("club_advisor_id").equals(clubAdvisorId)){
                    ClubAdvisor advisor=new ClubAdvisor();
                    advisor.setClubAdvisorId(resultSet.getString("club_advisor_id"));
                    advisor.setFirstName(resultSet.getString("first_name"));
                    advisor.setLastName(resultSet.getString("last_name"));

                    return advisor;

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
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

    public boolean isDataFilled(){

        if(txtClubAdvisorID.getText().isBlank()||comboClubCategory.getSelectionModel()==null||
        txtClubName.getText().isBlank()||txtDescription.getText().isBlank()) return false;

        return true;
    }

    public String autoGenerateClubID(){
        List<String> clubIDs=new ArrayList<>();
        String sql="SELECT * FROM club";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                clubIDs.add(resultSet.getString("club_id"));
            }

            if(clubIDs.isEmpty()){
                return "C001";
            }else {
                String[] split = clubIDs.get(clubIDs.size() - 1).split("C");
                int idNumber = Integer.parseInt(split[1]);
                idNumber += 1;
                if (idNumber < 9) {
                    return "C00" + idNumber;
                } else if (idNumber < 99) {
                    return "C0" + idNumber;
                } else {
                    return "C" + idNumber;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isClubAdvisorHasClub(String clubAdvisorId){
        String sql="SELECT * FROM club";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("club_advisor_id").equals(clubAdvisorId)){
                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
