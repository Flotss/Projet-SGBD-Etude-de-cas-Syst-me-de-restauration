package com.goodfood.app.mvc.controller;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.database.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * The type Consult plat button controller.
 */
public class ConsultPlatButtonController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Consultation des plats");

        HBox resultat = null;
        try {
            resultat = DBUtils.dumpResultSet(GoodFoodApplication.MODEL.getPlats());
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(resultat));
        stage.show();
    }
}
