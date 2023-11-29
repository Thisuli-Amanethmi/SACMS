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

        //setting club advisor properties
        ClubAdvisor clubAdvisor = new ClubAdvisor();
        clubAdvisor.setClubAdvisorId(autoGenerateClubAdvisorId());
        clubAdvisor.setFirstName(txtFirstName.getText());
        clubAdvisor.setLastName(txtLastName.getText());
        clubAdvisor.setEmail(txtEmail.getText());
        clubAdvisor.setPassword(txtPassword.getText());

        //database connection and query defining
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO club_advisor VALUES (?,?,?,?,?)";

        //validating inputs

        //checking if the clb advisor with the given email is already in the system
        if(isClubAdvisorExists(clubAdvisor.getEmail())){
            new Alert(Alert.AlertType.ERROR,"This User is already exists !").show();
            return;
        }

        //checking the email format
        if(!isValidEmail(clubAdvisor.getEmail())){
            new Alert(Alert.AlertType.ERROR,"Invalid email !").show();
            return;
        }

        //checking if all required fields are filled
        if(!isDataFilled()){
            new Alert(Alert.AlertType.WARNING,"Please fill all details !").show();
            return;
        }

        try {
            //prepare and execute statements to insert to database
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clubAdvisor.getClubAdvisorId());
            preparedStatement.setString(2,clubAdvisor.getFirstName());
            preparedStatement.setString(3,clubAdvisor.getLastName());
            preparedStatement.setString(4,clubAdvisor.getEmail());
            preparedStatement.setString(5, clubAdvisor.getPassword());
            preparedStatement.executeUpdate();

            //prompting message after successful registration
            new Alert(Alert.AlertType.INFORMATION,"User added successfully ! (Club Advisor ID: "+clubAdvisor.getClubAdvisorId()+")").show();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //validating the email
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

    //checking if the club advisor with the given email exists
    public boolean isClubAdvisorExists(String email){
        String sql="SELECT * FROM club_advisor";
        //database connection
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            //preprare and execute SQL statements
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Iterate through the result set to check for the given email
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

    //auto generating club advisor ID
    public String autoGenerateClubAdvisorId(){
        //creating a list to store existing club advisor IDs
        List<String> clubAdvisorIDs=new ArrayList<>();
        //selecting existing IDs from database
        String sql="SELECT * FROM club_advisor";
        //database connection
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            //prepare and execute sql statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Iterate through the result set to collect existing ClubAdvisor IDs
            while(resultSet.next()){
                clubAdvisorIDs.add(resultSet.getString("club_advisor_id"));
            }

            //Check if there are no existing ClubAdvisor IDs
            if(clubAdvisorIDs.isEmpty()){
                return "CA001";
            }else {
                //splitting the ID to check
                String[] split = clubAdvisorIDs.get(clubAdvisorIDs.size() - 1).split("CA");
                int idNumber = Integer.parseInt(split[1]);
                idNumber += 1;
                //defining ID format
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

    //checking if required fileds are filled
    public boolean isDataFilled(){
        if(txtFirstName.getText().isBlank()||txtLastName.getText().isBlank()||txtEmail.getText().isBlank()||
        txtPassword.getText().isBlank()) return false;

        return true;
    }
}
