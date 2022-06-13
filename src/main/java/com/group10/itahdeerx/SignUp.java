package com.group10.itahdeerx;

import com.group10.itahdeerx.components.MyButton;
import com.group10.itahdeerx.components.MyTextField;
import com.group10.itahdeerx.utils.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUp {

    public SignUp() {

    }

    public VBox renderPage() {

        Text title = new Text();
        title.setText("i-TahdeerX");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 15, 0));

        Text subtitle = new Text();
        subtitle.setText("Create a new account to login into i-TahdeerX");
        subtitle.setStyle("-fx-font-size: 16px;");
        VBox.setMargin(subtitle, new Insets(0, 0, 20, 0));

        ImageView imageView = new ImageView(getClass().getResource("images/iium_logo.png").toExternalForm());
        imageView.setFitWidth(300);
        imageView.setFitHeight(87);
        VBox.setMargin(imageView, new Insets(0, 0, 20, 0));

        TextField username = new MyTextField("Username");

        PasswordField passwordField = MyTextField.passwordField("Password");

        TextField fullName = new MyTextField("Full Name");

        TextField matricNumber = new MyTextField("Matric Number");
        VBox mainContainer = new VBox();

        Button signUpButton = new MyButton("Sign Up", Theme.LIGHT);
        signUpButton.setOnMouseClicked(mouseEvent -> {
            new UsersDB().saveArr(new UsersModel(username.getText(), passwordField.getText(), matricNumber.getText(), fullName.getText()));
            Scene scene = new Scene(new MainApp().renderPage((Stage) mainContainer.getScene().getWindow(), "Registration successful!"));
            Stage stage = (Stage) mainContainer.getScene().getWindow();
            stage.setScene(scene);
        });

        VBox formContainer = new VBox();
        formContainer.setSpacing(20);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.getChildren().addAll(username, passwordField, fullName, matricNumber, signUpButton);

        Text message = new Text();
        message.setStyle("-fx-text-fill: #4BB543; -fx-font-weight: bold; -fx-font-size: 16px;");
        VBox.setMargin(message, new Insets(5, 0, 5, 0));

        mainContainer.setPadding(new Insets(20));
        mainContainer.setSpacing(20);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setMinWidth(640);
        mainContainer.setMinHeight(640);
        mainContainer.getChildren().addAll(imageView, title, subtitle, formContainer, message);

        return mainContainer;
    }

}
