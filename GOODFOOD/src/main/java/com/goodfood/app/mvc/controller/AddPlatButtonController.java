package com.goodfood.app.mvc.controller;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.view.GestionPlatView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.SQLException;

/**
 * The type Add plat button controller.
 */
public class AddPlatButtonController implements EventHandler<ActionEvent> {

    private final TextField libelle;
    private final ChoiceBox<String> type;
    private final TextField prix;
    private final TextField quantite;
    private final Label errorSuccess;

    private final GestionPlatView gestionPlatView;


    /**
     * Instantiates a new Add plat button controller.
     *
     * @param libelle         the libelle
     * @param type            the type
     * @param prix            the prix
     * @param quantite        the quantite
     * @param errorSuccess    the error success
     * @param gestionPlatView the gestion plat view
     */
    public AddPlatButtonController(TextField libelle, ChoiceBox<String> type, TextField prix, TextField quantite, Label errorSuccess, GestionPlatView gestionPlatView) {
        super();
        this.libelle = libelle;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.errorSuccess = errorSuccess;
        this.gestionPlatView = gestionPlatView;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            verificationDonnees();
            GoodFoodApplication.MODEL.addPlat(libelle.getText(), type.getValue(), prix.getText(), quantite.getText());
            errorSuccess.setText("Plat ajouté avec succès");
            errorSuccess.setTextFill(Color.GREEN);
            gestionPlatView.update();
            clearFields();
        } catch (SQLException e) {
            e.getStackTrace();
            errorSuccess.setText("Erreur lors de l'ajout du plat");
            errorSuccess.setTextFill(Color.RED);
        } catch (IllegalStateException e) {
            errorSuccess.setText(e.getMessage());
            errorSuccess.setTextFill(Color.RED);
        }
    }

    /**
        * Clear fields.
        */
    private void clearFields() {
        libelle.setText("");
        type.setValue("");
        prix.setText("");
        quantite.setText("");
    }

    /**
        * Verification donnees.
        */
    private void verificationDonnees() {
        if (libelle.getText().isEmpty() || type.getValue().isEmpty() || prix.getText().isEmpty() || quantite.getText().isEmpty()
                || !prix.getText().matches("[0-9]+") || !quantite.getText().matches("[0-9]+")) {
            errorSuccess.setText("Veuillez remplir tous les champs");
            errorSuccess.setTextFill(Color.RED);
            throw new IllegalStateException("Veuillez remplir tous les champs");
        }

    }

}
