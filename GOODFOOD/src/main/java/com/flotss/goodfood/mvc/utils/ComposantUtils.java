package com.flotss.goodfood.mvc.utils;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class ComposantUtils {

    public static Label createLabel(String text, int size){
        Label label = new Label(text);

        // Set font
        label.setFont(new Font("Arial", size));

        return label;
    }

    public static TextField createTextField(String text, int size){
        TextField textField = new TextField();
        textField.setPromptText(text);

        // Set font
        textField.setFont(new Font("Arial", size));

        return textField;
    }

    public static TextArea createTextArea(String text, int size){
        TextArea textArea = new TextArea(text);

        // Set font
        textArea.setFont(new Font("Arial", size));

        return textArea;
    }

    public static PasswordField createPasswordField(String text, int size){
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(text);

        // Set font
        passwordField.setFont(new Font("Arial", size));

        return passwordField;
    }
}
