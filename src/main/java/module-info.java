module com.example.cwsample {
    requires javafx.controls;
    requires javafx.fxml;



    exports com.example.sacms_grp30;
    opens com.example.sacms_grp30 to javafx.fxml;
}