module com.example.artifacthunter {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.artifacthunter to javafx.fxml;
    exports com.example.artifacthunter;
    exports com.example.artifacthunter.test;
}