package com.example.videolibrarysystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab genreTab = new Tab("Genres", new GenreController().getView());
        genreTab.setClosable(false);

        Tab movieTab = new Tab("Movies", new MovieController().getView());
        movieTab.setClosable(false);

        Tab customerTab = new Tab("Customers", new CustomerController().getView());
        customerTab.setClosable(false);

        Tab rentalTab = new Tab("Rentals", new RentalController().getView());
        rentalTab.setClosable(false);

        tabPane.getTabs().addAll(genreTab, movieTab, customerTab, rentalTab);

        // Global styling
        tabPane.setStyle("-fx-background-color: #f0f4f8;");

        Scene scene = new Scene(tabPane, 700, 500);
        primaryStage.setTitle("Video Library Rental System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
