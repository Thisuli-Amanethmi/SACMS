package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.ClubAdvisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubAdvisorRegisterController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;



    @FXML
    void loadHome(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor");
        stage.show();

    }

    @FXML
    void registerClubAdvisor(ActionEvent event) {

        ClubAdvisor clubAdvisor = new ClubAdvisor();
        clubAdvisor.setClubAdvisorId(autoGenerateClubAdvisorId());
        clubAdvisor.setFirstName(txtFirstName.getText());
        clubAdvisor.setLastName(txtLastName.getText());
        clubAdvisor.setEmail(txtEmail.getText());
        clubAdvisor.setPassword(txtPassword.getText());

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO club_advisor VALUES (?,?,?,?,?)";

        if(isClubAdvisorExists(clubAdvisor.getEmail())){
            new Alert(Alert.AlertType.ERROR,"This User is already exists !").show();
            return;
        }

        if(!isValidEmail(clubAdvisor.getEmail())){
            new Alert(Alert.AlertType.ERROR,"Invalid email !").show();
            return;
        }

        if(!isDataFilled()){
            new Alert(Alert.AlertType.WARNING,"Please fill all details !").show();
            return;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clubAdvisor.getClubAdvisorId());
            preparedStatement.setString(2,clubAdvisor.getFirstName());
            preparedStatement.setString(3,clubAdvisor.getLastName());
            preparedStatement.setString(4,clubAdvisor.getEmail());
            preparedStatement.setString(5, clubAdvisor.getPassword());
            preparedStatement.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION,"User added successfully !").show();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidEmail(String email){
        if(!email.contains("@")){
            System.out.println("@ is missing");
            return false;
        }else{
            String[] split = email.split("@");
            if(split[1].equals("gmail.com")){
                System.out.println("valid email");
                return true;
            }else{
                System.out.println("not a gmail");
                return false;
            }
        }
    }

    public boolean isClubAdvisorExists(String email){
        String sql="SELECT * FROM club_advisor";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("email").equals(email)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public String autoGenerateClubAdvisorId(){
        List<String> clubAdvisorIDs=new ArrayList<>();
        String sql="SELECT * FROM club_advisor";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                clubAdvisorIDs.add(resultSet.getString("club_advisor_id"));
            }

            if(clubAdvisorIDs.isEmpty()){
                return "CA001";
            }else {
                String[] split = clubAdvisorIDs.get(clubAdvisorIDs.size() - 1).split("CA");
                int idNumber = Integer.parseInt(split[1]);
                idNumber += 1;
                if (idNumber < 9) {
                    return "CA00" + idNumber;
                } else if (idNumber < 99) {
                    return "CA0" + idNumber;
                } else {
                    return "CA" + idNumber;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDataFilled(){
        if(txtFirstName.getText().isBlank()||txtLastName.getText().isBlank()||txtEmail.getText().isBlank()||
        txtPassword.getText().isBlank()) return false;

        return true;
    }
}
