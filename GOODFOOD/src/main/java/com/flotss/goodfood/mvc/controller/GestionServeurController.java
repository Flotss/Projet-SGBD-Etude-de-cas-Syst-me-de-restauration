package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.mvc.view.GestionServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
