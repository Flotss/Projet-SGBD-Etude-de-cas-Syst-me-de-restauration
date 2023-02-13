package com.flotss.goodfood;

import com.flotss.goodfood.mvc.model.Model;
import com.flotss.goodfood.mvc.scene.ScenesEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoodFoodApplication extends Application {

    public static Stage STAGE;
    public static Model MODEL;


    @Override
    public void start(Stage stage) throws IOException {
        GoodFoodApplication.STAGE = stage;
        GoodFoodApplication.MODEL = new Model();

        Scene scene = ScenesEnum.LOGIN.getScene();
        stage.setTitle("GoodFood");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}