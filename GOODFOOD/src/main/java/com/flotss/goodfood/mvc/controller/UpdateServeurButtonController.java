package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.view.GestionServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class UpdateServeurButtonController implements EventHandler<ActionEvent> {

    private final HBox ligne;
    private final Label errorSuccessLabel;

    private final GestionServeurView gestionServeurView;

    public UpdateServeurButtonController(HBox ligne, Label errorSuccessLabel, GestionServeurView gestionServeurView) {
        this.ligne = ligne;
        this.errorSuccessLabel = errorSuccessLabel;
        this.gestionServeurView = gestionServeurView;
    }

    @Override
    public void handle(ActionEvent event) {
        int numServ = Integer.parseInt(((javafx.scene.control.TextField) ligne.getChildren().get(0)).getText());
        String email = ((javafx.scene.control.TextField) ligne.getChildren().get(1)).getText();
        String nom = ((javafx.scene.control.TextField) ligne.getChildren().get(2)).getText();
        String grade = ((javafx.scene.control.TextField) ligne.getChildren().get(3)).getText();

        if (GoodFoodApplication.MODEL.updateServeur(numServ, email, nom, grade)){
            errorSuccessLabel.setText("Le serveur a bien été modifié");
            errorSuccessLabel.setVisible(true);
            errorSuccessLabel.setTextFill(Color.GREEN);
            gestionServeurView.update();
        }else{
            errorSuccessLabel.setText("Le serveur n'a pas pu être modifié");
            errorSuccessLabel.setVisible(true);
            errorSuccessLabel.setTextFill(Color.RED);
        }
    }
}
