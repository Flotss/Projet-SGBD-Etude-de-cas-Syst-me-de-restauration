package com.flotss.goodfood.component;

import javafx.scene.control.TextField;

/**
 * The type Number text field.
 */
public class NumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    /**
        * Validate the text has been entered
        *
        * @param text the text
        * @return the boolean
        */
    private boolean validate(String text) {
        return text.matches("[0-9]*");
    }
}