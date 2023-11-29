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
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentViewProfileViewController {
    @FXML
    private Button backButton;

    @FXML
    private Button viewDetailsButton;

    @FXML
    private Label studentIDLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label studentEmailLabel;

    @FXML
    private Label studentRegisteredEventIDLabel;

    @FXML
    private Label studentRegisteredMeetingIDLabel;

    @FXML
    private Label studentRegisteredClubIDLabel;



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

    public void viewDetailsButtonOnClick(ActionEvent actionEvent) throws SQLException {
        navigateViewDetails(actionEvent);
    }

    private void navigateViewDetails(ActionEvent actionEvent) throws SQLException {
        String studentID = StudentLogInViewController.joinClubStudentID[0];

        Connection connection = DBConnection.getInstance().getConnection(); // to use the created Connection in DBConnection class
        Statement statement = connection.createStatement(); // to execute sql queries

        // to get student details from student table
        String queryGetStudentDetails = "SELECT * FROM student WHERE student_id='"; // to add student details to the database
        System.out.println(queryGetStudentDetails + studentID + "';");
        ResultSet result1 = statement.executeQuery(queryGetStudentDetails + studentID + "';"); // executing the query

        String studentName;
        String studentEmail;
        String[] studentDetails = new String[2]; // to store individual student's name and email in an array
        while (result1.next()) {
            studentName = result1.getString(2);
            studentEmail = result1.getString(3);

            studentDetails[0] = studentName;
            studentDetails[1] = studentEmail;
        }

        studentIDLabel.setText(studentID);
        studentNameLabel.setText(studentDetails[0]);
        studentEmailLabel.setText(studentDetails[1]);

        // to get student club details from club_student table
        String queryGetClubStudentDetails = "SELECT * FROM club_student WHERE student_id='"; // to add student details to the database
        System.out.println(queryGetClubStudentDetails + studentID + "';");
        ResultSet result2 = statement.executeQuery(queryGetClubStudentDetails + studentID + "';"); // executing the query

        String clubID;
        int size = 20;
        String[] studentClubDetails = new String[size]; // to store individual student's name and email in an array
        int count = 0;
        while (result2.next()) {
            clubID = result2.getString(1);

            studentClubDetails[count] = clubID;

            count++;
        }

        String clubIDs = "";
        for(int i=0; i<studentClubDetails.length; i++) {
            if(studentClubDetails[i] != null) {
                clubIDs = clubIDs + studentClubDetails[i];
                clubIDs = clubIDs + ", ";
            }
        }

        studentRegisteredClubIDLabel.setText(clubIDs);


        // to get student event details from club_student table
        String queryGetEventStudentDetails = "SELECT * FROM event_student WHERE student_id='"; // to add student details to the database
        System.out.println(queryGetEventStudentDetails + studentID + "';");
        ResultSet result3 = statement.executeQuery(queryGetEventStudentDetails + studentID + "';"); // executing the query

        String eventID;
        String[] studentEventDetails = new String[size]; // to store individual student's name and email in an array
        while (result3.next()) {
            eventID = result3.getString(1);

            studentEventDetails[count] = eventID;

            count++;
        }

        String eventIDs = "";
        for(int i=0; i<studentEventDetails.length; i++) {
            if(studentEventDetails[i] != null) {
                eventID = eventIDs + studentEventDetails[i];
                eventIDs = eventIDs + ", ";
            }
        }

        studentRegisteredEventIDLabel.setText(eventIDs);


        // to get student meeting details from club_student table
        String queryGetMeetingStudentDetails = "SELECT * FROM meeting_student WHERE student_id='"; // to add student details to the database
        System.out.println(queryGetMeetingStudentDetails + studentID + "';");
        ResultSet result4 = statement.executeQuery(queryGetMeetingStudentDetails + studentID + "';"); // executing the query

        String meetingID;
        String[] studentMeetingDetails = new String[size]; // to store individual student's name and email in an array
        while (result4.next()) {
            meetingID = result4.getString(1);

            studentMeetingDetails[count] = meetingID;

            count++;
        }

        String meetingIDs = "";
        for(int i=0; i<studentMeetingDetails.length; i++) {
            if(studentMeetingDetails[i] != null) {
                meetingIDs = meetingIDs + studentMeetingDetails[i];
                meetingIDs = meetingIDs + ", ";
            }
        }

        studentRegisteredMeetingIDLabel.setText(meetingIDs);
    }
}
