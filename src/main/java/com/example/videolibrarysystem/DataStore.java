package com.example.videolibrarysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
    public static ObservableList<String> genres =
            FXCollections.observableArrayList();

    // Movie stored as "MovieName (GenreName)"
    public static ObservableList<String> movies =
            FXCollections.observableArrayList();

    public static ObservableList<String> customers =
            FXCollections.observableArrayList();

    // Rental stored as "CustomerName - MovieName"
    public static ObservableList<String> borrowed =
            FXCollections.observableArrayList();

    public static ObservableList<String> returned =
            FXCollections.observableArrayList();
}