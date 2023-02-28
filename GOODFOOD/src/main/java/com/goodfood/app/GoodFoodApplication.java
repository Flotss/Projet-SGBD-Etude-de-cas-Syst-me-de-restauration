package com.goodfood.app;

import com.goodfood.app.mvc.model.Model;
import com.goodfood.app.mvc.scene.ScenesEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type Good food application.
 */
public class GoodFoodApplication extends Application {

    /**
     * The constant STAGE.
     */
    public static Stage STAGE;
    /**
     * The constant MODEL.
     */
    public static Model MODEL;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        GoodFoodApplication.STAGE = stage;
        GoodFoodApplication.MODEL = new Model();

        Scene scene = ScenesEnum.LOGIN.getScene();
        stage.setTitle("GoodFood");
        stage.setScene(scene);
        stage.show();
    }
}