package com.example.videolibrarysystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MovieController {

    public VBox getView() {
        Label title = new Label("Movies");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKBLUE);

        // Genres combo
        Label genreLabel = new Label("Genres:");
        genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> genreBox = new ComboBox<>(DataStore.genres);
        genreBox.setPrefWidth(300);
        styleComboBox(genreBox);
        genreBox.setPromptText("Select genre");

        // Name field
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField nameField = new TextField();
        nameField.setPromptText("Enter movie name");
        styleTextField(nameField);

        // Save button
        Button saveBtn = new Button("Save");
        styleButton(saveBtn, "#1a3c8f");

        // Registered combo — filtered by genre
        Label registeredLabel = new Label("Registered:");
        registeredLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> registeredBox = new ComboBox<>();
        registeredBox.setPrefWidth(300);
        styleComboBox(registeredBox);

        // Remove button
        Button removeBtn = new Button("Remove");
        styleButton(removeBtn, "#1a3c8f");

        // Filter registered list when genre changes
        genreBox.setOnAction(e -> {
            String selectedGenre = genreBox.getValue();
            registeredBox.getItems().clear();
            if (selectedGenre != null) {
                for (String movie : DataStore.movies) {
                    if (movie.endsWith("(" + selectedGenre + ")")) {
                        registeredBox.getItems().add(movie);
                    }
                }
            }
        });

        // Save
        saveBtn.setOnAction(e -> {
            String genre = genreBox.getValue();
            String name  = nameField.getText().trim();
            if (genre != null && !name.isEmpty()) {
                String entry = name + " (" + genre + ")";
                if (!DataStore.movies.contains(entry)) {
                    DataStore.movies.add(entry);
                    nameField.clear();
                    // refresh registered list
                    genreBox.getOnAction().handle(null);
                    showAlert("Success", "Movie saved!");
                } else {
                    showAlert("Error", "Movie already exists.");
                }
            } else {
                showAlert("Error", "Please select a genre and enter a movie name.");
            }
        });

        // Remove
        removeBtn.setOnAction(e -> {
            String selected = registeredBox.getValue();
            if (selected != null) {
                DataStore.movies.remove(selected);
                registeredBox.getItems().remove(selected);
                registeredBox.setValue(null);
                showAlert("Success", "Movie removed!");
            } else {
                showAlert("Error", "Please select a movie to remove.");
            }
        });

        // Layout
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

        grid.add(genreLabel,      0, 0);
        grid.add(genreBox,        1, 0);
        grid.add(nameLabel,       0, 1);
        grid.add(nameField,       1, 1);
        grid.add(saveBtn,         1, 2);
        grid.add(registeredLabel, 0, 3);
        grid.add(registeredBox,   1, 3);
        grid.add(removeBtn,       1, 4);

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

    private void styleTextField(TextField tf) {
        tf.setPrefWidth(300);
        tf.setStyle(
                "-fx-border-color: #3a7bd5;" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;" +
                        "-fx-padding: 5;"
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
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}