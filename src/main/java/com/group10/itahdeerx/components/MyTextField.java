package com.group10.itahdeerx.components;

import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MyTextField extends TextField {

    public MyTextField(String promptText) {
        super();
        generateStyles();
        this.setPromptText(promptText);
    }

    public static PasswordField passwordField(String promptText) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setStyle("-fx-padding: 10px;\n" +
                "-fx-border-radius: 10px;\n" +
                "-fx-background-radius: 10px;");
        passwordField.setPadding(new Insets(10));
        return passwordField;
    }

    private void generateStyles() {
        this.setPadding(new Insets(10));
        this.setStyle("-fx-padding: 10px;\n" +
                "-fx-border-radius: 10px;\n" +
                "-fx-background-radius: 10px;");
    }

}
