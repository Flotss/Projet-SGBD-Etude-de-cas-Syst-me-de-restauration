package com.goodfood.app.mvc.controller;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.view.GestionServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * The type Update serveur button controller.
 */
public class UpdateServeurButtonController implements EventHandler<ActionEvent> {

    private final HBox ligne;
    private final Label errorSuccessLabel;

    private final GestionServeurView gestionServeurView;

    /**
     * Instantiates a new Update serveur button controller.
     *
     * @param ligne              the ligne
     * @param errorSuccessLabel  the error success label
     * @param gestionServeurView the gestion serveur view
     */
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

        if (((Button) event.getSource()).getText().equalsIgnoreCase("Supprimer")) {
            if (GoodFoodApplication.MODEL.deleteServeur(numServ)) {
                errorSuccessLabel.setText("Le serveur a bien été supprimé");
                errorSuccessLabel.setVisible(true);
                errorSuccessLabel.setTextFill(Color.GREEN);
                gestionServeurView.update();
            } else {
                errorSuccessLabel.setText("Le serveur n'a pas pu être supprimé");
                errorSuccessLabel.setVisible(true);
                errorSuccessLabel.setTextFill(Color.RED);
            }
            return;
        }

        if (GoodFoodApplication.MODEL.updateServeur(numServ, email, nom, grade)) {
            errorSuccessLabel.setText("Le serveur a bien été modifié");
            errorSuccessLabel.setVisible(true);
            errorSuccessLabel.setTextFill(Color.GREEN);
            gestionServeurView.update();
        } else {
            errorSuccessLabel.setText("Le serveur n'a pas pu être modifié");
            errorSuccessLabel.setVisible(true);
            errorSuccessLabel.setTextFill(Color.RED);
        }
    }
}
