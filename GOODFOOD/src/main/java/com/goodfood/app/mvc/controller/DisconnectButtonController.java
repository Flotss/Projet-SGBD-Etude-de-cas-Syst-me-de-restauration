package com.goodfood.app.mvc.controller;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.scene.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Screen;

/**
 * The type Disconnect button controller.
 */
public class DisconnectButtonController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        GoodFoodApplication.MODEL.disconnectDB();
        GoodFoodApplication.STAGE.setScene(ScenesEnum.LOGIN.getScene());

        // Set stage in the middle of the screen with Screen.getPrimary().getVisualBounds().getWidth() and Screen.getPrimary().getVisualBounds().getHeight()
        GoodFoodApplication.STAGE.setX((Screen.getPrimary().getVisualBounds().getWidth() - GoodFoodApplication.STAGE.getWidth()) / 2);
        GoodFoodApplication.STAGE.setY((Screen.getPrimary().getVisualBounds().getHeight() - GoodFoodApplication.STAGE.getHeight()) / 2);
    }

}
