module com.example.cw {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cw to javafx.fxml;
    exports com.example.cw;
}