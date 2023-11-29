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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentLogInViewController {
    @FXML
    private PasswordField password;

    @FXML
    private TextField studentIDTxt;

    @FXML
    private Button backButton;

    @FXML
    private Button logInButton;

    @FXML
    private Label logInMessageTxt;


    // validating inputs - ID
    public int checkIDValid(String ID) {
        if((!ID.matches("^S[0-9]+$"))) {
            return 1; // ID is invalid
        }

        return 0; // all good
    }

    // validating inputs - Password
    public int checkPasswordValid(String password) {
        if (password.length() < 8) {
            return 1; // invalid password (password is too short)
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
        String studentID = studentIDTxt.getText();
        String studentPassword = password.getText();

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
                    studentIDTxt.setText("Invalid ID");
                    break;
                case 0:
                    break;
                default:
                    studentIDTxt.setText(" ");
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
        if(studentIDTxt.getText().equals("Invalid ID") || password.getText().equals("Invalid Password")) {
            logInMessageTxt.setText("Unsuccessful log in !!! Enter valid data !!!");
        } else {
            navigateLogInStudent(actionEvent); // all good
        }
    }

    public static String[] joinClubStudentID = new String[1];

    public void navigateLogInStudent(ActionEvent actionEvent) throws SQLException {
        joinClubStudentID[0] = "S001";

        joinClubStudentID[0] = studentIDTxt.getText();
        System.out.println(joinClubStudentID[0]);

        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class

        Statement statement = connection.createStatement(); // to execute sql queries

        String query1 = "SELECT * FROM student WHERE student_id='";
        ResultSet result = statement.executeQuery(query1 + studentIDTxt.getText() + "';"); // executing the query

        String ID;
        String password;
        String[] studentLogInDetails = new String[2]; // to store individual student's login info in an array
        // there is only one entry under one studentID
        // so this while loop will be iterated only once
        while (result.next()) {
            ID = result.getString(1);
            password = result.getString(4);

            studentLogInDetails[0] = ID;
            System.out.println(studentLogInDetails[0]);
            studentLogInDetails[1] = password;
            System.out.println(studentLogInDetails[1]);
        }

        if(studentLogInDetails[0] == null) { // unregistered ID
            studentIDTxt.setText("Unregistered ID.");
            logInMessageTxt.setText("Unsuccessful log in !!! Enter valid data !!!");
        } // registered ID - checking the password
        else if(this.password.getText().equals(studentLogInDetails[1])) {
            try {
                navigateLogIn(actionEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            logInMessageTxt.setText("Incorrect Password !!!");
        }
    }

    public void logInButtonOnClick(ActionEvent actionEvent) throws Exception {
        checkLogIn(actionEvent);
    }

    private void navigateLogIn(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentMenu-view.fxml"));
        newStage.setTitle("Student - Menu");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void backButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigatePreviousPage(actionEvent);
    }

    private void navigatePreviousPage(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Student-view.fxml"));
        newStage.setTitle("SCAMS - Student");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
