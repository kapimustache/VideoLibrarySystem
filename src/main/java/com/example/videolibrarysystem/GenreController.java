package com.example.videolibrarysystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GenreController {

    public VBox getView() {
        // Title
        Label title = new Label("Genres");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKBLUE);

        // Name label and field
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField nameField = new TextField();
        nameField.setPromptText("Enter genre name");
        nameField.setPrefWidth(300);
        styleTextField(nameField);

        // Save button
        Button saveBtn = new Button("Save");
        styleButton(saveBtn, "#1a3c8f");

        // Registered label and combo box
        Label registeredLabel = new Label("Registered:");
        registeredLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> registeredBox = new ComboBox<>(DataStore.genres);
        registeredBox.setPrefWidth(300);
        styleComboBox(registeredBox);

        // Remove button
        Button removeBtn = new Button("Remove");
        styleButton(removeBtn, "#1a3c8f");

        // Save action
        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty() && !DataStore.genres.contains(name)) {
                DataStore.genres.add(name);
                nameField.clear();
                showAlert("Success", "Genre '" + name + "' saved!");
            } else {
                showAlert("Error", "Invalid or duplicate genre name.");
            }
        });

        // Remove action
        removeBtn.setOnAction(e -> {
            String selected = registeredBox.getValue();
            if (selected != null) {
                DataStore.genres.remove(selected);
                registeredBox.setValue(null);
                showAlert("Success", "Genre '" + selected + "' removed!");
            } else {
                showAlert("Error", "Please select a genre to remove.");
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

        grid.add(nameLabel,      0, 0);
        grid.add(nameField,      1, 0);
        grid.add(saveBtn,        1, 1);
        grid.add(registeredLabel,0, 2);
        grid.add(registeredBox,  1, 2);
        grid.add(removeBtn,      1, 3);

        VBox root = new VBox(15, title, grid);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f0f4f8;");

        return root;
    }

    // ── helpers ──────────────────────────────────────────────
    private void styleButton(Button btn, String color) {
        btn.setPrefWidth(200);
        btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13pt;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;"
        );
        btn.setOnMouseEntered(e ->
                btn.setStyle(
                        "-fx-background-color: #2255cc;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 13pt;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 6;"
                )
        );
        btn.setOnMouseExited(e ->
                btn.setStyle(
                        "-fx-background-color: " + color + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 13pt;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 6;"
                )
        );
    }

    private void styleTextField(TextField tf) {
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}