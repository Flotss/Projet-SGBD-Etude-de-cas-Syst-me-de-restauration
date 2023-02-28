package com.flotss.goodfood.mvc.scene;

import com.flotss.goodfood.mvc.controller.LoginButtonController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The type Login page.
 */
class LoginPage extends BorderPane {

    /**
     * Instantiates a new Login page.
     */
    public LoginPage() {
        super();


        // Top
        Label topTitle = ComposantUtils.createLabel("GoodFood", 30);
        topTitle.setPadding(new Insets(15, 0, 0, 0));
        setAlignment(topTitle, Pos.CENTER);

        this.setTop(topTitle);

        // Center
        VBox center = new VBox();
        this.setCenter(center);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(7);

        Label centerTitle = ComposantUtils.createLabel("Connexion", 24);
        Label errorLabel = ComposantUtils.createLabel("", 16);
        errorLabel.setTextFill(Color.RED);
        errorLabel.resize(getWidth(), getHeight());
        setAlignment(errorLabel, Pos.CENTER);
        errorLabel.setVisible(false);

        HBox identifiantBox = new HBox();
        identifiantBox.setAlignment(Pos.CENTER);
        identifiantBox.setSpacing(7);
        identifiantBox.setTranslateX(14);


        Label identifiantLabel = ComposantUtils.createLabel("Identifiant", 18);


        TextField identifiantField = ComposantUtils.createTextFieldPlHd("dupont276u", 16);
        identifiantBox.getChildren().addAll(identifiantLabel, identifiantField);


        HBox passwordBox = new HBox();
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(7);


        Label passwordLabel = ComposantUtils.createLabel("Mot de passe", 18);

        PasswordField passwordField = ComposantUtils.createPasswordField("Mot de passe", 16);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);


        Button loginButton = new Button("Se connecter");
        loginButton.setFont(new Font("Arial", 18));


        // Ajout des listeners
        passwordBox.addEventHandler(ActionEvent.ACTION, new LoginButtonController(identifiantField, passwordField, errorLabel));
        identifiantField.addEventHandler(ActionEvent.ACTION, new LoginButtonController(identifiantField, passwordField, errorLabel));
        loginButton.setOnAction(new LoginButtonController(identifiantField, passwordField, errorLabel));


        center.getChildren().addAll(centerTitle, errorLabel, identifiantBox, passwordBox, loginButton);

    }

}
