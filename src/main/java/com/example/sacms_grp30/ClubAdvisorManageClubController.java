package com.example.sacms_grp30;

import com.example.sacms_grp30.db.DBConnection;
import com.example.sacms_grp30.model.ClubAdvisor;
import com.example.sacms_grp30.tables.TableClubAdvisorManageClub;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

public class ClubAdvisorManageClubController implements Initializable {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnClear;


    @FXML
    private Button btnCheckMembers;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEventScheduling;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colCategory;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colClubAdvisorName;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colClubID;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colClubName;

    @FXML
    private TableColumn<TableClubAdvisorManageClub, String> colDescription;

    @FXML
    private TableView<TableClubAdvisorManageClub> tblClub;

    @FXML
    private TextField txtClubAdvisorID;

    @FXML
    private TextField txtClubID;

    @FXML
    private TextField txtClubName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSearchClubName;

    public static String selectedClubId="";

    @FXML
    void clearData(ActionEvent event) {
        txtClubAdvisorID.clear();
        txtClubID.clear();
        txtDescription.clear();
        txtClubName.clear();
        txtSearchClubName.clear();

        loadClubDetails();
    }
    @FXML
    void loadDataToTextFields(MouseEvent event) {
        TableClubAdvisorManageClub selectedItem = tblClub.getSelectionModel().getSelectedItem();

        txtClubID.setText(selectedItem.getClubID());
        txtDescription.setText(selectedItem.getDescription());
        txtClubAdvisorID.setText(selectedItem.getClubAdvisorID());
        txtClubName.setText(selectedItem.getClubName());
    }



    @FXML
    void loadCheckMembers(ActionEvent event) throws IOException {

        TableClubAdvisorManageClub selectedItem = tblClub.getSelectionModel().getSelectedItem();
        selectedClubId=selectedItem.getClubID();

       // btnCheckMembers.getScene().getWindow();

        //closing the existing UI
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        //opening a new window on a newly created stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("club-advisor-check-members.fxml"));

        window.setScene(new Scene(fxmlLoader.load()));
        ClubAdvisorCheckMembersController controller = fxmlLoader.getController();
        controller.setClubId(selectedClubId);
        window.setUserData(selectedItem);
        window.setTitle("Club Advisor - Members");
        window.show();

    }

    @FXML
    void loadClubAdvisorMenu(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("club-advisor-menu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Menu");
        stage.show();

    }

    @FXML
    void loadEventSchedule(ActionEvent event) throws IOException {
        //closing the existing UI
        Stage window = (Stage) btnEventScheduling.getScene().getWindow();
        window.close();

        //opening a new window on a newly created stage
        Parent root = FXMLLoader.load(getClass().getResource("EventSchedulingScene.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Club Advisor - Event Scheduling");
        stage.show();

    }

    @FXML
    void searchClub(ActionEvent event) {

        if(txtSearchClubName.getText().isBlank()){
            new Alert(Alert.AlertType.WARNING,"Please enter club name before searching !").show();
            return;
        }

        String[] clubNameSearchSplit = txtSearchClubName.getText().replaceAll("\\s","").toUpperCase().split("");

        List<TableClubAdvisorManageClub> viewClubs=new ArrayList<>();

        String sql="SELECT * FROM club";
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String clubName = resultSet.getString("club_name").replaceAll("\\s","").toUpperCase();
                for(String letter:clubNameSearchSplit){
                    if(clubName.contains(letter)){
                        TableClubAdvisorManageClub club=new TableClubAdvisorManageClub();
                        ClubAdvisor clubAdvisor = getClubAdvisor(resultSet.getString("club_advisor_id"));

                        club.setClubAdvisorID(resultSet.getString("club_advisor_id"));
                        club.setClubAdvisorName(clubAdvisor.getFirstName()+" "+clubAdvisor.getLastName());
                        club.setClubID(resultSet.getString("club_id"));
                        club.setClubName(resultSet.getString("club_name"));
                        club.setCategory(resultSet.getString("club_category"));
                        club.setDescription(resultSet.getString("description"));

                        viewClubs.add(club);
                    }
                }

            }

            tblClub.setItems(FXCollections.observableList(viewClubs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateClub(ActionEvent event) {

        if(!isDataFilled()){
            new Alert(Alert.AlertType.ERROR,"Please enter details before update !").show();
            return;
        }

        String clubIDText = txtClubID.getText();
        String advisorIDText = txtClubAdvisorID.getText();
        String descriptionText = txtDescription.getText();
        String clubNameText = txtClubName.getText();

        String sql="UPDATE club SET club_advisor_id=? , club_name=? ,description=? WHERE club_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,advisorIDText);
            preparedStatement.setString(2,clubNameText);
            preparedStatement.setString(3,descriptionText);
            preparedStatement.setString(4,clubIDText);

            preparedStatement.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION,"Club details updated successfully !").show();

            loadClubDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDataFilled(){
        if(txtClubID.getText().isBlank()||txtClubAdvisorID.getText().isBlank()||txtClubName.getText().isBlank()||
        txtDescription.getText().isBlank()) return false;

        return true;
    }

    @FXML
    void deleteClub(ActionEvent event) {
        if(txtClubID.getText().isBlank()){
            new Alert(Alert.AlertType.ERROR,"Please select a club before delete !").show();
            return;
        }

        String clubIDText = txtClubID.getText();

        String clubStudentSQL="DELETE FROM club_student WHERE club_id=?";
        String sql = "DELETE FROM club WHERE club_id =?";

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement clubStudentStatement = connection.prepareStatement(clubStudentSQL);
            PreparedStatement clubStatement = connection.prepareStatement(sql);

            clubStatement.setString(1,clubIDText);
            clubStudentStatement.setString(1,clubIDText);

            clubStudentStatement.executeUpdate();
            clubStatement.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION,"Club Deleted successfully !").show();

            loadClubDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateTable();
        loadClubDetails();
    }

    private void loadClubDetails() {
        List<TableClubAdvisorManageClub> clubs = new ArrayList<>();

        String sql="SELECT * FROM club";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TableClubAdvisorManageClub club=new TableClubAdvisorManageClub();
                ClubAdvisor clubAdvisor = getClubAdvisor(resultSet.getString("club_advisor_id"));

                club.setClubAdvisorID(resultSet.getString("club_advisor_id"));
                club.setClubAdvisorName(clubAdvisor.getFirstName()+" "+clubAdvisor.getLastName());
                club.setClubID(resultSet.getString("club_id"));
                club.setClubName(resultSet.getString("club_name"));
                club.setCategory((resultSet.getString("club_category")));
                club.setDescription(resultSet.getString("description"));


                clubs.add(club);
            }

            tblClub.setItems(FXCollections.observableList(clubs));

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

    private void initiateTable() {
        colClubAdvisorName.setCellValueFactory(new PropertyValueFactory<>("clubAdvisorName"));
        colClubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        colClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public String getSelectedClubId(){
        return selectedClubId;
    }
}
