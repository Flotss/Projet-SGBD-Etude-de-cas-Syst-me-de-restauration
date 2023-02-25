package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.scene.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;

import java.sql.SQLException;

public class LoginButtonController implements EventHandler<ActionEvent> {

    private final TextField username;
    private final TextField password;
    private final Label error;

    public LoginButtonController(TextField username, TextField password, Label error) {
        this.username = username;
        this.password = password;
        this.error = error;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            GoodFoodApplication.MODEL.setDbConnection(username.getText(), password.getText());
            GoodFoodApplication.STAGE.setScene(ScenesEnum.PAGESERVEUR.getScene());

            // Set stage in the middle of the screen with Screen.getPrimary().getVisualBounds().getWidth() and Screen.getPrimary().getVisualBounds().getHeight()
            GoodFoodApplication.STAGE.setX((Screen.getPrimary().getVisualBounds().getWidth() - GoodFoodApplication.STAGE.getWidth()) / 2);
            GoodFoodApplication.STAGE.setY((Screen.getPrimary().getVisualBounds().getHeight() - GoodFoodApplication.STAGE.getHeight()) / 2);
        } catch (SQLException e) {
            error.setVisible(true);
            error.setText(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
