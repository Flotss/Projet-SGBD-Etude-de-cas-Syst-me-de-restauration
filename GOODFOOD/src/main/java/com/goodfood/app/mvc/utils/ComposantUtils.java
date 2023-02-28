package com.goodfood.app.mvc.utils;

import com.goodfood.app.component.NumberTextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * The type Composant utils.
 */
public class ComposantUtils {

    /**
     * The constant FONT.
     */
    public static String FONT = "Arial";

    /**
     * Create label
     *
     * @param text the text
     * @param size the size
     * @return the label
     */
    public static Label createLabel(String text, int size) {
        Label label = new Label(text);

        // Set font
        label.setFont(new Font(FONT, size));

        return label;
    }

    /**
     * Create textfield placeHolder text field.
     *
     * @param text the text
     * @param size the size
     * @return the text field
     */
    public static TextField createTextFieldPlHd(String text, int size) {
        TextField textField = new TextField();
        textField.setPromptText(text);

        // Set font
        textField.setFont(new Font(FONT, size));

        return textField;
    }

    /**
     * Create textfield
     *
     * @param text the text
     * @param size the size
     * @return the text field
     */
    public static TextField createTextField(String text, int size) {
        TextField textField = new TextField(text);

        // Set font
        textField.setFont(new Font(FONT, size));

        return textField;
    }

    /**
     * Create number textfield
     *
     * @param text the text
     * @param size the size
     * @return the text field
     */
    public static TextField createNumberTextField(String text, int size) {
        NumberTextField textField = new NumberTextField();
        textField.setPromptText(text);

        // Set font
        textField.setFont(new Font(FONT, size));

        return textField;
    }

    /**
     * Create textarea
     *
     * @param text the text
     * @param size the size
     * @return the text area
     */
    public static TextArea createTextArea(String text, int size) {
        TextArea textArea = new TextArea(text);

        // Set font
        textArea.setFont(new Font(FONT, size));

        return textArea;
    }

    /**
     * Create passwordfield
     *
     * @param text the text
     * @param size the size
     * @return the password field
     */
    public static PasswordField createPasswordField(String text, int size) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(text);

        // Set font
        passwordField.setFont(new Font(FONT, size));

        return passwordField;
    }

    /**
     * Create vbox
     *
     * @param spacing the spacing
     * @return the v box
     */
    public static VBox createVBox(int spacing) {
        VBox vBox = new VBox();
        vBox.setSpacing(spacing);

        return vBox;
    }

    /**
     * Create hbox
     *
     * @param spacing the spacing
     * @return the h box
     */
    public static HBox createHBox(int spacing) {
        HBox hBox = new HBox();
        hBox.setSpacing(spacing);

        return hBox;
    }
}
