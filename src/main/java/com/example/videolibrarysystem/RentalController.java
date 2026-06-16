package com.example.videolibrarysystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RentalController {

    public VBox getView() {
        Label title = new Label("Rentals");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKBLUE);

        // Customer
        Label customerLabel = new Label("Customer:");
        customerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> customerBox = new ComboBox<>(DataStore.customers);
        customerBox.setPrefWidth(300);
        styleComboBox(customerBox);
        customerBox.setPromptText("Select customer");

        // Genre
        Label genreLabel = new Label("Genre:");
        genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> genreBox = new ComboBox<>(DataStore.genres);
        genreBox.setPrefWidth(300);
        styleComboBox(genreBox);
        genreBox.setPromptText("Select genre");

        // Movies (filtered by genre)
        Label moviesLabel = new Label("Movies:");
        moviesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> moviesBox = new ComboBox<>();
        moviesBox.setPrefWidth(300);
        styleComboBox(moviesBox);

        // Save Rental
        Button saveBtn = new Button("Save Rental");
        styleButton(saveBtn, "#1a3c8f");

        // Borrowed
        Label borrowedLabel = new Label("Borrowed:");
        borrowedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> borrowedBox = new ComboBox<>();
        borrowedBox.setPrefWidth(300);
        styleComboBox(borrowedBox);

        // Return Movie
        Button returnBtn = new Button("Return Movie");
        styleButton(returnBtn, "#1a3c8f");

        // Returned
        Label returnedLabel = new Label("Returned:");
        returnedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> returnedBox = new ComboBox<>();
        returnedBox.setPrefWidth(300);
        styleComboBox(returnedBox);

        // Filter movies by genre
        genreBox.setOnAction(e -> {
            String genre = genreBox.getValue();
            moviesBox.getItems().clear();
            if (genre != null) {
                for (String movie : DataStore.movies) {
                    if (movie.endsWith("(" + genre + ")"))
                        moviesBox.getItems().add(movie);
                }
            }
        });

        // Filter borrowed by customer
        customerBox.setOnAction(e -> {
            String customer = customerBox.getValue();
            borrowedBox.getItems().clear();
            returnedBox.getItems().clear();
            if (customer != null) {
                for (String rental : DataStore.borrowed) {
                    if (rental.startsWith(customer + " -> "))
                        borrowedBox.getItems().add(rental);
                }
                for (String rental : DataStore.returned) {
                    if (rental.startsWith(customer + " -> "))
                        returnedBox.getItems().add(rental);
                }
            }
        });

        // Save rental
        saveBtn.setOnAction(e -> {
            String customer = customerBox.getValue();
            String movie    = moviesBox.getValue();
            if (customer != null && movie != null) {
                String entry = customer + " -> " + movie;
                if (!DataStore.borrowed.contains(entry)) {
                    DataStore.borrowed.add(entry);
                    borrowedBox.getItems().add(entry);
                    showAlert("Success", "Rental saved!");
                } else {
                    showAlert("Error", "Already rented.");
                }
            } else {
                showAlert("Error", "Select customer and movie.");
            }
        });

        // Return movie
        returnBtn.setOnAction(e -> {
            String selected = borrowedBox.getValue();
            if (selected != null) {
                DataStore.borrowed.remove(selected);
                borrowedBox.getItems().remove(selected);
                DataStore.returned.add(selected);
                returnedBox.getItems().add(selected);
                borrowedBox.setValue(null);
                showAlert("Success", "Movie returned!");
            } else {
                showAlert("Error", "Select a borrowed movie.");
            }
        });

        // Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #3a7bd5;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;"
        );

        grid.add(customerLabel, 0, 0); grid.add(customerBox, 1, 0);
        grid.add(genreLabel,    0, 1); grid.add(genreBox,    1, 1);
        grid.add(moviesLabel,   0, 2); grid.add(moviesBox,   1, 2);
        grid.add(saveBtn,       1, 3);
        grid.add(borrowedLabel, 0, 4); grid.add(borrowedBox, 1, 4);
        grid.add(returnBtn,     1, 5);
        grid.add(returnedLabel, 0, 6); grid.add(returnedBox, 1, 6);

        VBox root = new VBox(15, title, grid);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f0f4f8;");

        return root;
    }

    private void styleButton(Button btn, String color) {
        btn.setPrefWidth(200);
        btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13pt;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;"
        );
    }

    private void styleComboBox(ComboBox<?> cb) {
        cb.setStyle(
                "-fx-border-color: #3a7bd5;" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;"
        );
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title); a.setHeaderText(null); a.setContentText(msg);
        a.showAndWait();
    }
}