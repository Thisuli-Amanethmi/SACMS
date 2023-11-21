module com.example.cwsample {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cwsample to javafx.fxml;
    exports com.example.cwsample;
}