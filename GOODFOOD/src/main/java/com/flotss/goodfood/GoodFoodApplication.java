package com.flotss.goodfood;

import com.flotss.goodfood.mvc.model.Model;
import com.flotss.goodfood.mvc.scene.ScenesEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GoodFoodApplication extends Application {

    public static Stage STAGE;
    public static Model MODEL;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        GoodFoodApplication.STAGE = stage;
        GoodFoodApplication.MODEL = new Model();
        MODEL.setDbConnection("test", "test");

        Scene scene = ScenesEnum.PAGESERVEUR.getScene();
        stage.setTitle("GoodFood");
        stage.setScene(scene);
        stage.show();
    }
}