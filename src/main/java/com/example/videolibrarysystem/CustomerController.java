package com.example.videolibrarysystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomerController {

    public VBox getView() {
        Label title = new Label("Customers");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKBLUE);

        Label nameLabel  = new Label("Name:");
        Label phoneLabel = new Label("Phone:");
        Label emailLabel = new Label("Email:");
        for (Label l : new Label[]{nameLabel, phoneLabel, emailLabel})
            l.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        TextField nameField  = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();
        for (TextField tf : new TextField[]{nameField, phoneField, emailField}) {
            tf.setPrefWidth(300);
            styleTextField(tf);
        }
        nameField.setPromptText("Full name");
        phoneField.setPromptText("Phone number");
        emailField.setPromptText("Email address");

        Button saveBtn = new Button("Save");
        styleButton(saveBtn, "#1a3c8f");

        Label registeredLabel = new Label("Registered:");
        registeredLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> registeredBox = new ComboBox<>(DataStore.customers);
        registeredBox.setPrefWidth(300);
        styleComboBox(registeredBox);

        Button removeBtn = new Button("Remove");
        styleButton(removeBtn, "#1a3c8f");

        // Save
        saveBtn.setOnAction(e -> {
            String name  = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                String entry = name + " | " + phone + " | " + email;
                if (!DataStore.customers.contains(entry)) {
                    DataStore.customers.add(entry);
                    nameField.clear(); phoneField.clear(); emailField.clear();
                    showAlert("Success", "Customer saved!");
                } else {
                    showAlert("Error", "Customer already exists.");
                }
            } else {
                showAlert("Error", "Please fill in all fields.");
            }
        });

        // Remove
        removeBtn.setOnAction(e -> {
            String selected = registeredBox.getValue();
            if (selected != null) {
                DataStore.customers.remove(selected);
                registeredBox.setValue(null);
                showAlert("Success", "Customer removed!");
            } else {
                showAlert("Error", "Please select a customer.");
            }
        });

        // Grid layout
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

        grid.add(nameLabel,       0, 0); grid.add(nameField,       1, 0);
        grid.add(phoneLabel,      0, 1); grid.add(phoneField,      1, 1);
        grid.add(emailLabel,      0, 2); grid.add(emailField,      1, 2);
        grid.add(saveBtn,         1, 3);
        grid.add(registeredLabel, 0, 4); grid.add(registeredBox,   1, 4);
        grid.add(removeBtn,       1, 5);

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
        a.setTitle(title); a.setHeaderText(null); a.setContentText(msg);
        a.showAndWait();
    }
}
