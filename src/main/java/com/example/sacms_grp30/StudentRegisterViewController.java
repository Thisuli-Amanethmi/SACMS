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
import java.util.ArrayList;

public class StudentRegisterViewController {
    @FXML
    private TextField IDtxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button backButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button logInButton;

    @FXML
    private Label messageBox;

    // validating inputs - ID
    public int checkIDValid(String ID) {
        if((!ID.matches("^S[0-9]+$"))) {
            return 1; // ID is invalid
        }

        return 0; // all good
    }

    // validating inputs - Name
    public int checkNameValid(String name) {
        if((!name.matches("^[a-zA-Z]+$"))) {
            return 1; // name is invalid
        }

        return 0; // all good
    }

    // validating inputs - Email
    public int checkEmailValid(String email) {
        if((!email.matches("^[a-zA-Z0-9_.+-]+@gmail.com"))) {
            return 1; // email is invalid
        }

        return 0; // all good
    }

    // validating inputs - Password
    public int checkPasswordValid(String password) {
        if (password.length() < 8) {
            return 1; // password is too short
        } else if (password.matches(".*\\s.*")) {
            return 2; // invalid, have whitespace
        }

        return 0; // all good
    }

    // check whether all the required data has been provided or not
    public int checkRegisterValid(String ID, String name, String email, String password, String passwordConfirm) {
        if((ID.isEmpty()) || (name.isEmpty()) || (email.isEmpty()) || (password.isEmpty()) || (passwordConfirm.isEmpty())) {
            return 1; // if anything is empty
        } else {
            return 0; // all good
        }
    }

    public void checkRegister() throws SQLException {
        String studentID = IDtxt.getText();
        String studentName = nameTxt.getText();
        String studentEmail = emailTxt.getText();
        String studentPassword = password.getText();
        String studentPasswordConfirm = confirmPassword.getText();

        // check whether anything is empty or not
        switch (checkRegisterValid(studentID, studentName, studentEmail, studentPassword, studentPasswordConfirm)) {
            case 1:
                messageBox.setText("Enter all required details.");
                break;
            case 0:
                break;
            default:
                messageBox.setText(" ");
        }

        // if all the required data has been provided ---> validating inputs
        if(!messageBox.getText().equals("Enter all required details.")) {
            switch (checkIDValid(studentID)) {
                case 1:
                    IDtxt.setText("Invalid ID");
                    break;
                case 0:
                    break;
                default:
                    IDtxt.setText(" ");
            }

            switch (checkNameValid(studentName)) {
                case 1:
                    nameTxt.setText("Invalid name");
                    break;
                case 0:
                    break;
                default:
                    nameTxt.setText(" ");
            }

            switch (checkEmailValid(studentEmail)) {
                case 1:
                    emailTxt.setText("Invalid email");
                    break;
                case 0:
                    break;
                default:
                    emailTxt.setText(" ");
            }

            switch (checkPasswordValid(studentEmail)) {
                case 1:
                    messageBox.setText("Password is too short");
                    break;
                case 2:
                    messageBox.setText("Invalid Password");
                    break;
                case 0:
                    break;
                default:
                    messageBox.setText(" ");
            }
        }

        // after validating all the inputs ---> checking passwords are equal or not
        if(studentPassword.equals(studentPasswordConfirm)) {
            if(IDtxt.getText().equals("Invalid ID") || nameTxt.getText().equals("Invalid name") || emailTxt.getText().equals("Invalid email")
                    || password.getText().equals("Password is too short") || password.getText().equals("Invalid Password")) {
                messageBox.setText("Registration unsuccessful !!! Enter valid data !!!");
            } else {
                System.out.println("1111");
                navigateRegisterStudentDatabase(); // all good
            }
        } else {
            messageBox.setText("Passwords are not matching.");
        }
    }

    public void registerButtonOnClick() throws SQLException {
        checkRegister();
    }

    private void navigateRegisterStudentDatabase() throws SQLException {
        System.out.println("222");
        String uiStudentID = IDtxt.getText();
        String uiStudentName = nameTxt.getText();
        String uiStudentEmail = emailTxt.getText();
        String uiStudentPassword = password.getText();

        System.out.println(uiStudentID + " " + uiStudentName + " " +  uiStudentEmail + " " +  uiStudentPassword);

        ArrayList<String[]> studentTableDetails = new ArrayList<>(); // to store all the student details

        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class
        System.out.println("1");

        Statement statement = connection.createStatement(); // to execute sql queries
        System.out.println("2");

        String query1 = "SELECT * FROM student";
        // ResultSet is for get some data from the database
        ResultSet result = statement.executeQuery(query1); // executing the query
        System.out.println("3");

        String ID;
        String name;
        String email;
        String password;
        while (result.next()) {
            ID = result.getString(1);
            name = result.getString(2);
            email = result.getString(3);
            password = result.getString(4);

            String[] studentDetails = new String[4]; // to store individual student details in an array
            studentDetails[0] = ID;
            studentDetails[1] = name;
            studentDetails[2] = email;
            studentDetails[3] = password;

            studentTableDetails.add(studentDetails);
        }

        System.out.println("4");

        // Printing the data in userTableDetails
        for(int i=0; i<studentTableDetails.size(); i++) {
            String studentID = studentTableDetails.get(i)[0];
            String studentName = studentTableDetails.get(i)[1];
            String studentEmail = studentTableDetails.get(i)[2];
            String studentPassword = studentTableDetails.get(i)[3];

            if(studentID.equals(uiStudentID)) {
                messageBox.setText("Student ID has already registered.");
            } else if (studentName.equals(uiStudentName)) {
                messageBox.setText("Student Name has already registered.");
            } else if (studentEmail.equals(uiStudentEmail)) {
                messageBox.setText("Student Email has already registered.");
            } else {
                messageBox.setText(" ");
            }

            System.out.println(i + " student ID: " + studentID + " student name: " + studentName + " student email: " + studentEmail + " student password: " + studentPassword);
        }

        if(messageBox.getText().equals(" ") || messageBox.getText().isEmpty()) {
            System.out.println("yeahh");
            // registerStudent(uiStudentID, uiStudentName, uiStudentEmail, uiStudentPassword);
            String query2 = "Insert INTO student VALUES ("; // to add student details to the database
            statement.executeUpdate(query2 + "'" + uiStudentID + "', '" + uiStudentName + "', '"
                    + uiStudentEmail + "', '" + uiStudentPassword + "');"); // executing the query

            System.out.println(query2 + "'" + uiStudentID + ", '" + uiStudentName + "', '" + uiStudentEmail + "', '" + uiStudentPassword + "');");

            System.out.println("www");
            messageBox.setText("Successful Student Registration !!!");
        }

        // connection.close();

        System.out.println("Connection closed.");
    }

    public void backButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateBack(actionEvent);
    }

    private void navigateBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Student-view.fxml"));
        newStage.setTitle("SCAMS - Student");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void logInButtonOnClick(ActionEvent actionEvent) throws Exception {
        navigateLogIn(actionEvent);
    }

    private void navigateLogIn(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentLogIn-view.fxml"));
        newStage.setTitle("Student - LogIn SACMS");
        newStage.setScene(new Scene(root, 800, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
