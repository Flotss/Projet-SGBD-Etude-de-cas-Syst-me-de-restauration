package com.goodfood.app.mvc.scene;

import com.goodfood.app.mvc.utils.ComposantUtils;
import javafx.scene.layout.BorderPane;

/**
 * The type Error scene.
 */
public class ErrorScene extends BorderPane {

    /**
     * Instantiates a new Error scene.
     *
     * @param message the message
     */
    public ErrorScene(String message) {
        super();
        this.setCenter(ComposantUtils.createLabel(message, 30));
    }

}
