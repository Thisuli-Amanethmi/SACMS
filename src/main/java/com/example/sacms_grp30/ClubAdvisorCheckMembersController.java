package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.Student;
import com.example.sacms_grp30.tables.TableClubAdvisorCheckMembers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class ClubAdvisorCheckMembersController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<TableClubAdvisorCheckMembers, String> colEmail;

    @FXML
    private TableColumn<TableClubAdvisorCheckMembers, String> colStudentID;

    @FXML
    private TableColumn<TableClubAdvisorCheckMembers, String> colStudentName;

    @FXML
    private TableView<TableClubAdvisorCheckMembers> tblClubMembers;


    public static String clubId = "";

    @FXML
    void loadManageClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-manage-club.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Club Management");
        stage.show();

    }

    //Initializes the JavaFX controller when the associated FXML file is loaded.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            initializeTable();
            loadStudents();
        });
    }

    //load and displays club details in a table view
    private void loadStudents() {
        List<TableClubAdvisorCheckMembers> members=new ArrayList<>();
        System.out.println(clubId);
        String sql="SELECT * FROM club_student";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getString("club_id").equals(clubId)){
                    //Get the student information based on the student_id
                    Student student = getStudent(resultSet.getString("student_id"));
                    //Create the TableClubAdvisorCheckMembers instance and set its properties
                    TableClubAdvisorCheckMembers member = new TableClubAdvisorCheckMembers();
                    member.setStudentID(student.getStudentId());
                    member.setStudentName(student.getStudentName());
                    member.setEmail(student.getEmail());
                    members.add(member);
                }

            }

            tblClubMembers.setItems(FXCollections.observableList(members));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //retrieves a student from the database according to the student id
    private Student getStudent(String studentId) {
        String sql="SELECT * FROM student";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Iterate through the result set to find the student with the specified ID
            while (resultSet.next()){
                if(resultSet.getString("student_id").equals(studentId)){
                    //Create a Student instance and set its properties
                    Student student=new Student();
                    student.setStudentId(resultSet.getString("student_id"));
                    student.setStudentName(resultSet.getString("student_name"));
                    student.setEmail(resultSet.getString("email"));
                    return student;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    //Initializes the columns of the TableView with corresponding cell values
    private void initializeTable() {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    //Sets the clubId property with the provided value
    public void setClubId(String club){
        System.out.println(club);
        clubId=club;
    }
}
