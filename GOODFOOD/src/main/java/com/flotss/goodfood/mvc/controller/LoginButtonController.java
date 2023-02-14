package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.model.Model;
import com.flotss.goodfood.mvc.scene.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginButtonController implements EventHandler<ActionEvent> {

    private TextField username;
    private TextField password;
    private Label error;

    public LoginButtonController(TextField username, TextField password, Label error) {
        this.username = username;
        this.password = password;
        this.error = error;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            GoodFoodApplication.MODEL.setDbConnection(username.getText(), password.getText());
            GoodFoodApplication.STAGE.setScene(ScenesEnum.LANDINGPAGE.getScene());
        } catch (Exception e) {
            error.setVisible(true);
            error.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
