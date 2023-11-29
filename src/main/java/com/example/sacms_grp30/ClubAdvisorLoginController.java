package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClubAdvisorLoginController {

    @FXML
    private Label logInMessageTxt;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtClubAdvisorID;

    @FXML
    private TextField txtPassword;

    // validating inputs - ID
    public int checkIDValid(String ID) {
        if(!ID.matches("^CA[0-9]+$")) {
            return 1; // ID is invalid
        }

        return 0; // all good
    }

    // validating inputs - Password
    public int checkPasswordValid(String password) {
        if (!password.matches("^[a-zA-Z0-9]+$")) {
            return 1; // invalid password
        } else if (password.matches(".*\\s.*")) {
            return 2; // invalid, have whitespace
        }

        return 0; // all good
    }

    // check whether all the required data has been provided or not
    public int checkLogInValid(String ID,String password) {
        if((ID.isEmpty()) || (password.isEmpty())) {
            return 1; // if anything is empty
        } else {
            return 0; // all good
        }
    }

    public void checkLogIn(ActionEvent actionEvent) throws SQLException {
        String studentID = txtClubAdvisorID.getText();
        String studentPassword = txtPassword.getText();

        // check whether anything is empty or not
        switch (checkLogInValid(studentID, studentPassword)) {
            case 1:
                logInMessageTxt.setText("Enter all required details.");
                break;
            case 0:
                break;
            default:
                logInMessageTxt.setText(" ");
        }

        // if all the required data has been provided ---> validating inputs
        if(!logInMessageTxt.getText().equals("Enter all required details.")) {
            switch (checkIDValid(studentID)) {
                case 1:
                    txtClubAdvisorID.setText("Invalid ID");
                    break;
                case 0:
                    break;
                default:
                    txtClubAdvisorID.setText(" ");
            }

            switch (checkPasswordValid(studentPassword)) {
                case 1:
                    logInMessageTxt.setText("Invalid Password");
                    break;
                case 0:
                    break;
                default:
                    logInMessageTxt.setText(" ");
            }
        }

        // after validating all the inputs
        if(txtClubAdvisorID.getText().equals("Invalid ID") || txtPassword.getText().equals("Invalid Password")) {
            logInMessageTxt.setText("Unsuccessful log in !!! Enter valid data !!!");
        } else {
            navigateLogInClubAdvisor(actionEvent); // all good
        }
    }

    private void navigateLogInClubAdvisor(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class

        Statement statement = connection.createStatement(); // to execute sql queries

        String query1 = "SELECT * FROM club_advisor WHERE club_advisor_id='";
        ResultSet result = statement.executeQuery(query1 + txtClubAdvisorID.getText() + "';"); // executing the query

        String ID;
        String password;
        String[] studentLogInDetails = new String[2]; // to store individual student's login info in an array
        // there is only one entry under one studentID
        // so this while loop will be iterated only once
        while (result.next()) {
            ID = result.getString(1);
            password = result.getString(5);

            studentLogInDetails[0] = ID;
            System.out.println(studentLogInDetails[0]);
            studentLogInDetails[1] = password;
            System.out.println(studentLogInDetails[1]);
        }

        if(studentLogInDetails[0] == null) { // unregistered ID
            txtClubAdvisorID.setText("Unregistered ID.");
            logInMessageTxt.setText("Unsuccessful log in !!! Enter valid data !!!");
        } // registered ID - checking the password
        else if(this.txtPassword.getText().equals(studentLogInDetails[1])) {
            try {
                navigateLogIn(actionEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            logInMessageTxt.setText("Incorrect Password !!!");
        }
    }

    public void loadClubAdvisorMenu(ActionEvent actionEvent) throws Exception {
        checkLogIn(actionEvent);
    }

    private void navigateLogIn(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-menu.fxml"));
        newStage.setTitle("Club Advisor - Menu");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


    @FXML
    void loadClubAdvisorHome(ActionEvent event) throws IOException {
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

}
