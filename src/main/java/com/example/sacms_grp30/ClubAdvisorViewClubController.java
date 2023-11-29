package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.ClubAdvisor;
import com.example.sacms_grp30.tables.TableClubAdvisorViewClub;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class ClubAdvisorViewClubController implements Initializable {
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnback;
    @FXML
    private Button btnClear;


    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colCategory;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colClubAdvisorName;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colClubID;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colClubName;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, String> colDescription;

    @FXML
    private TableColumn<TableClubAdvisorViewClub, Integer> colNoOfStudents;
    @FXML
    private TextField txtSearchClubName;

    @FXML
    private TableView<TableClubAdvisorViewClub> tblViewClub;
    //passing the attributes from the TableClubAdvisorViewClub Object

    @FXML
    //clear the data on text field
    void clearData(ActionEvent event) {
        txtSearchClubName.clear();
        loadTableData();
    }
    @FXML
    void loadManageClub(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnback.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-menu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Menu");
        stage.show();

    }

    @FXML
    //Searches for clubs based on the entered club name and populates the TableView with the matching results
    void searchClubName(ActionEvent event) {

        if(txtSearchClubName.getText().isBlank()){
            new Alert(Alert.AlertType.WARNING,"Please enter club name before searching !").show();
            return;
        }

        //Process the entered club name for searching , remove the spaces
        String[] clubNameSearchSplit = txtSearchClubName.getText().replaceAll("\\s","").toUpperCase().split("");

        //Create a list to store TableClubAdvisorViewClub instances
        List<TableClubAdvisorViewClub> viewClubs=new ArrayList<>();

        String sql="SELECT * FROM club";
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String clubName = resultSet.getString("club_name").replaceAll("\\s","").toUpperCase();
                for(String letter:clubNameSearchSplit){
                    if(clubName.contains(letter)){
                        TableClubAdvisorViewClub club=new TableClubAdvisorViewClub();
                        ClubAdvisor clubAdvisor = getClubAdvisor(resultSet.getString("club_advisor_id"));

                        club.setClubAdvisorName(clubAdvisor.getFirstName()+" "+clubAdvisor.getLastName());
                        club.setClubID(resultSet.getString("club_id"));
                        club.setClubName(resultSet.getString("club_name"));
                        club.setCategory(resultSet.getString("club_category"));
                        club.setDescription(resultSet.getString("description"));

                        int noOfStudentsInClub = getNoOfStudentsInClub(club.getClubID());
                        club.setNoOfStudents(noOfStudentsInClub);

                        viewClubs.add(club);
                    }
                }

            }

            tblViewClub.setItems(FXCollections.observableList(viewClubs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    //Initializes the JavaFX controller when the associated FXML file is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateTable();
        loadTableData();
    }

    //load club data from the database and show it in table view
    private void loadTableData() {
        List<TableClubAdvisorViewClub> clubs=new ArrayList<>();

        String sql = "SELECT * FROM club";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            //to execute parameterized query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TableClubAdvisorViewClub club=new TableClubAdvisorViewClub();
                ClubAdvisor clubAdvisor = getClubAdvisor(resultSet.getString("club_advisor_id"));

                //se the properties to tableview class
                club.setClubAdvisorName(clubAdvisor.getFirstName()+" "+clubAdvisor.getLastName());
                club.setClubID(resultSet.getString("club_id"));
                club.setClubName(resultSet.getString("club_name"));
                club.setCategory(resultSet.getString("club_category"));
                club.setDescription(resultSet.getString("description"));

                int noOfStudentsInClub = getNoOfStudentsInClub(club.getClubID());
                club.setNoOfStudents(noOfStudentsInClub);

                clubs.add(club);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblViewClub.setItems(FXCollections.observableList(clubs));
    }

    //get the number of students based on the club id
    private int getNoOfStudentsInClub(String clubID) {
        int count=0;
        String sql="SELECT * FROM club_student";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("club_id").equals(clubID)){
                    count+=1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    //Retrieves a ClubAdvisor from the club_advisor table based on the given clubAdvisorID
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

    //Initializes the columns of the TableView with corresponding cell value
    private void initiateTable() {
        colClubAdvisorName.setCellValueFactory(new PropertyValueFactory<>("clubAdvisorName"));
        colClubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        colClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNoOfStudents.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));
    }
}
