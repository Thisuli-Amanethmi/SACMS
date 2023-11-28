package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.ClubAdvisor;
import com.example.sacms_grp30.model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClubAdvisorRegisterController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegister;
    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtStaffID;


    @FXML
    void loadHome(ActionEvent event) {

    }

    @FXML
    void registerClubAdvisor(ActionEvent event) {
        Staff staff = new Staff();
        staff.setStaffId(txtStaffID.getText());
        staff.setFirstName(txtFirstName.getText());
        staff.setLastName(txtLastName.getText());
        staff.setEmail(txtEmail.getText());
        staff.setPassword(txtPassword.getText());

        ClubAdvisor clubAdvisor = new ClubAdvisor();
        clubAdvisor.setStaffId(txtStaffID.getText());
        clubAdvisor.setClubAdvisorId("CA001");

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO staff VALUES (?,?,?,?,?)";
        String advisorSql = "INSERT INTO club_advisor VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,staff.getStaffId());
            preparedStatement.setString(2,staff.getFirstName());
            preparedStatement.setString(3,staff.getLastName());
            preparedStatement.setString(4,staff.getEmail());
            preparedStatement.setString(5, staff.getPassword());
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement(advisorSql);
            preparedStatement1.setString(1,clubAdvisor.getClubAdvisorId());
            preparedStatement1.setString(2,clubAdvisor.getStaffId());
            preparedStatement1.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
