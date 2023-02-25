package com.flotss.goodfood.mvc.scene;

import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.scene.layout.BorderPane;

public class ErrorScene extends BorderPane {

    public ErrorScene(String message) {
        this.setCenter(ComposantUtils.createLabel(message, 30));
    }

}
