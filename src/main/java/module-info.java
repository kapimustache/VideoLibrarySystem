module com.example.videolibrarysystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.videolibrarysystem to javafx.fxml;
    exports com.example.videolibrarysystem;
}