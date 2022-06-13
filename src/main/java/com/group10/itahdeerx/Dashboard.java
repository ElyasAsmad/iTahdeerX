package com.group10.itahdeerx;

import com.group10.itahdeerx.components.MyButton;
import com.group10.itahdeerx.utils.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Properties;

public class Dashboard {

    private UsersModel currentUser;

    public Dashboard(UsersModel currentUser) {
        this.currentUser = currentUser;
    }

    public VBox renderPage() {

        Text fullNameDisplay = new Text(currentUser.getFullName());
        fullNameDisplay.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        Text matricDisplay = new Text(currentUser.getMatricNumber());
        matricDisplay.setStyle("-fx-font-size: 16px;");

        VBox infoContainer = new VBox();
        infoContainer.setSpacing(10);
        infoContainer.getChildren().addAll(fullNameDisplay, matricDisplay);

        HBox infoGrowBox = new HBox();
        infoGrowBox.setAlignment(Pos.CENTER_LEFT);
        infoGrowBox.getChildren().add(infoContainer);
        HBox.setHgrow(infoGrowBox, Priority.ALWAYS);

        Button logoutButton = new MyButton("Log Out", Theme.LIGHT);
        logoutButton.setStyle("-fx-background-color: #ff0e0e; -fx-background-radius: 10px; -fx-text-fill: #FFFFFF;");
        logoutButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) infoContainer.getScene().getWindow();
            Scene scene = new Scene(new MainApp().renderPage(stage, "Successfully log out!"));
            stage.setScene(scene);
            stage.setMinWidth(640);
            stage.setMinHeight(480);
        });

        HBox topContainer = new HBox();
        topContainer.setPadding(new Insets(10));
        topContainer.setAlignment(Pos.CENTER_LEFT);
        topContainer.getChildren().addAll(infoGrowBox, logoutButton);
        topContainer.setStyle("-fx-background-color: #cce8e6; -fx-background-radius: 20px;");
        topContainer.setPadding(new Insets(20));

        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        mainContainer.getChildren().addAll(topContainer);

        return mainContainer;
    }

}
