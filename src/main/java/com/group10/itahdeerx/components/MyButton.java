package com.group10.itahdeerx.components;

import com.group10.itahdeerx.utils.Theme;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class MyButton extends Button {

    public MyButton(String variant) {
        this("", variant);
    }

    public MyButton(String s, String variant) {
        super(s);
        generateStyles(variant);
    }

    private void generateStyles(String variant) {

        String backgroundColor = "";

        switch (variant) {
            case Theme.LIGHT:
                backgroundColor = Theme.Light.primaryColor;
                break;
            case Theme.DARK:
                backgroundColor = Theme.Dark.primaryColor;
                break;
        }

        this.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: #FFFFFF; -fx-background-radius: 20px;");
        this.setPadding(new Insets(10, 40, 10, 40));
    }

}
