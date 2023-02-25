package com.flotss.goodfood.mvc.utils;

import com.flotss.goodfood.component.NumberTextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ComposantUtils {

    public static String FONT = "Arial";

    public static Label createLabel(String text, int size){
        Label label = new Label(text);

        // Set font
        label.setFont(new Font(FONT, size));

        return label;
    }

    public static TextField createTextFieldPlHd(String text, int size){
        TextField textField = new TextField();
        textField.setPromptText(text);

        // Set font
        textField.setFont(new Font(FONT, size));

        return textField;
    }

    public static TextField createTextField(String text, int size){
        TextField textField = new TextField(text);

        // Set font
        textField.setFont(new Font(FONT, size));

        return textField;
    }

    public static TextField createNumberTextField(String text, int size){
        NumberTextField textField = new NumberTextField();
        textField.setPromptText(text);

        // Set font
        textField.setFont(new Font(FONT, size));

        return textField;
    }

    public static TextArea createTextArea(String text, int size){
        TextArea textArea = new TextArea(text);

        // Set font
        textArea.setFont(new Font(FONT, size));

        return textArea;
    }

    public static PasswordField createPasswordField(String text, int size){
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(text);

        // Set font
        passwordField.setFont(new Font(FONT, size));

        return passwordField;
    }

    public static VBox createVBox(int spacing){
        VBox vBox = new VBox();
        vBox.setSpacing(spacing);

        return vBox;
    }

    public static HBox createHBox(int spacing){
        HBox hBox = new HBox();
        hBox.setSpacing(spacing);

        return hBox;
    }
}
