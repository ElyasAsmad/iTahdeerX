package com.group10.itahdeerx.components;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;

public class MyComboBox extends ComboBox<String> {

    public MyComboBox(ObservableList<String> observableList, String promptText) {
        super(observableList);
        this.setPromptText(promptText);
        generateStyles();
    }

    private void generateStyles() {

        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px;");

    }

}
