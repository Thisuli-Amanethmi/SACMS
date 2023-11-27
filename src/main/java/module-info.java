module com.example.cwsample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.logging;



    exports com.example.sacms_grp30;
    opens com.example.sacms_grp30 to javafx.fxml;
}