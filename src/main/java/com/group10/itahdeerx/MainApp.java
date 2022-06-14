package com.group10.itahdeerx;

import com.group10.itahdeerx.components.MyButton;
import com.group10.itahdeerx.components.MyContainer;
import com.group10.itahdeerx.components.MyTextField;
import com.group10.itahdeerx.utils.Theme;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class MainApp extends Application {

    Scene mainScene;
    Stage mainStage;
    Text errorText;
    UsersModel registeredUsers;
    List<UsersModel> users;

    @Override
    public void start(Stage stage) {

        loadUsers();

        Scene scene = new Scene(renderPage(stage, ""));
        this.mainScene = scene;
        this.mainStage = stage;

        stage.setMinWidth(640);
        stage.setMinHeight(480);
        stage.setTitle("i-TahdeerX | Sign In");
        stage.setScene(scene);
        stage.show();
    }

    private void loadUsers() {
        this.users = new UsersDB().loadArr();
        System.out.println(users);
    }

    private void signUp() {

        Scene scene = new Scene(new SignUp().renderPage());
        mainStage.setScene(scene);
        mainStage.setMinWidth(640);
        mainStage.setMinHeight(480);

    }

    private void signIn(String username, String password) {

        loadUsers();

        errorText.setText(""); // Reset error text

        if (users == null) {

            errorText.setText("No users found!");
            return;

        }
        else {

            this.users.forEach(user -> {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    Scene scene = new Scene(new Dashboard(user).renderPage());
                    mainStage.setScene(scene);
                    mainStage.setMinHeight(720);
                    mainStage.setMinWidth(1280);
                    mainStage.setTitle("i-TahdeerX | Dashboard");
                }
            });

        }

        errorText.setText("Invalid login!");

    }

    public VBox renderPage(Stage stage, String message) {
        Text title = new Text();
        title.setText("i-TahdeerX");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 15, 0));

        Text subtitle = new Text();
        subtitle.setText("Log in into your account");
        subtitle.setStyle("-fx-font-size: 16px;");
        VBox.setMargin(subtitle, new Insets(0, 0, 20, 0));

        ImageView imageView = new ImageView(getClass().getResource("images/iium_logo.png").toExternalForm());
        imageView.setFitWidth(300);
        imageView.setFitHeight(87);
        VBox.setMargin(imageView, new Insets(0, 0, 20, 0));

        TextField username = new MyTextField("Username");

        PasswordField password = MyTextField.passwordField("Password");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        HBox.setHgrow(hBox, Priority.ALWAYS);

        Button submitButton = new MyButton("Sign In", Theme.LIGHT);
        submitButton.prefWidthProperty().bind(hBox.widthProperty().multiply(0.5));
        submitButton.setOnMouseClicked(mouseEvent -> signIn(username.getText(), password.getText()));

        Button signUpButton = new MyButton("Sign Up", Theme.LIGHT);
        signUpButton.prefWidthProperty().bind(stage.widthProperty().multiply(0.5));
        signUpButton.setStyle("-fx-background-color: #cce8e6; -fx-background-radius: 20px;");
        signUpButton.setOnMouseClicked(mouseEvent -> signUp());

        errorText = new Text();
        errorText.setFill(Color.RED);
        errorText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        hBox.getChildren().addAll(submitButton, signUpButton);

        VBox formContainer = new VBox();
        formContainer.setSpacing(20);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.getChildren().addAll(username, password, hBox, errorText);

        if (message.length() != 0) {
            errorText = new Text(message);
            errorText.setFill(Color.GREEN);
            errorText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            formContainer.getChildren().add(errorText);
        }

        VBox mainContainer = new MyContainer(Theme.LIGHT);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.getChildren().addAll(imageView, title, subtitle, formContainer);

        return mainContainer;
    }

    public static void main(String[] args) {
        launch();
    }
}