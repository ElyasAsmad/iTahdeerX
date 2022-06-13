package com.group10.itahdeerx.components;

import com.group10.itahdeerx.utils.Theme;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class MyContainer extends VBox {

    public MyContainer(String variant) {
       super();
       generateStyles(variant);
    }

    private void generateStyles(String variant) {

        String backgroundColor = "";

        switch (variant) {
            case Theme.LIGHT:
                backgroundColor = Theme.Light.containerColor;
                break;
            case Theme.DARK:
                backgroundColor = Theme.Dark.containerColor;
                break;
        }

        this.setStyle("-fx-background-color: " + backgroundColor + ";");
        this.setPadding(new Insets(20));

    }

}
