package com.goodfood.app.mvc.controller;

import com.goodfood.app.GoodFoodApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * The type Reserve plat button controller.
 */
public class ReservePlatButtonController implements EventHandler<ActionEvent> {

    private final ChoiceBox<Integer> nbrBox;
    private final ChoiceBox<String> platBox;
    private final TextField quantityTextField;
    private final Label errorLabel;
    private final Label successLabel;


    /**
     * Instantiates a new Reserve plat button controller.
     *
     * @param nbrBox            the nbr box
     * @param platBox           the plat box
     * @param quantityTextField the quantity text field
     * @param successLabel      the success label
     * @param errorLabel        the error label
     */
    public ReservePlatButtonController(ChoiceBox<Integer> nbrBox, ChoiceBox<String> platBox, TextField quantityTextField, Label successLabel, Label errorLabel) {
        this.nbrBox = nbrBox;
        this.platBox = platBox;
        this.quantityTextField = quantityTextField;
        this.errorLabel = errorLabel;
        this.successLabel = successLabel;
    }

    @Override
    public void handle(ActionEvent event) {
        if (!verificationDonnee()) return;

        try {
            GoodFoodApplication.MODEL.commanderPlat(nbrBox.getValue(), platBox.getValue(), Integer.parseInt(quantityTextField.getText()));
            successLabel.setVisible(true);
            successLabel.setText("La commande a bien été effectuée");
            errorLabel.setVisible(false);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            successLabel.setVisible(false);
            errorLabel.setVisible(true);
            errorLabel.setText(e.getMessage());
        }
    }

    /**
        * Verification donnee
        *
        * @return the boolean
        */
    private boolean verificationDonnee() {
        if (nbrBox.getValue() == null) {
            errorLabel.setVisible(true);
            errorLabel.setText("Veuillez choisir un numéro de réservation");
            return false;
        } else {
            errorLabel.setVisible(false);
        }

        if (quantityTextField.getText().isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("Veuillez choisir une quantité");
            return false;
        } else {
            errorLabel.setVisible(false);
        }

        return true;
    }
}
