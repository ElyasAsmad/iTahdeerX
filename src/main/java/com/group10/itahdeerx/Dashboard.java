package com.group10.itahdeerx;

import com.group10.itahdeerx.components.MyButton;
import com.group10.itahdeerx.components.TimeTable;
import com.group10.itahdeerx.utils.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dashboard {

    private final UsersModel currentUser;

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
        VBox.setMargin(topContainer, new Insets(20));

        Text title = new Text("i-TahdeerX");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ImageView imageView = new ImageView(getClass().getResource("images/iium.png").toExternalForm());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Text brandText = new Text("i-TahdeerX");
        brandText.setFill(Color.WHITE);
        brandText.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        HBox brandContainer = new HBox();
        brandContainer.setPrefHeight(80);
        brandContainer.setStyle("-fx-background-color: #009290;");
        brandContainer.setPadding(new Insets(20));
        brandContainer.setAlignment(Pos.CENTER_LEFT);
        brandContainer.setSpacing(10);
        brandContainer.getChildren().addAll(imageView, brandText);
        HBox.setHgrow(brandContainer, Priority.ALWAYS);

        HBox topBarContainer = new HBox();
        topBarContainer.setAlignment(Pos.CENTER);
        topBarContainer.getChildren().addAll(brandContainer);

        TimeTable myTimetable = new TimeTable(300, 500);
        myTimetable.enableActionButton();

        VBox contentContainer = new VBox();
        contentContainer.getChildren().add(myTimetable.render());
        VBox.setMargin(contentContainer, new Insets(0, 20, 0, 20));

        VBox mainContainer = new VBox();
        mainContainer.getChildren().addAll(topBarContainer, topContainer, contentContainer);

        return mainContainer;
    }

}
