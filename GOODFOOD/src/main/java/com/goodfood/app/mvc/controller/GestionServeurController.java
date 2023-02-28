package com.goodfood.app.mvc.controller;

import com.goodfood.app.mvc.view.GestionServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type Gestion serveur controller.
 */
public class GestionServeurController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Gestion des serveurs");


        GestionServeurView gestionServeurView = new GestionServeurView();


        stage.setScene(new Scene(gestionServeurView));
        stage.show();
    }
}
