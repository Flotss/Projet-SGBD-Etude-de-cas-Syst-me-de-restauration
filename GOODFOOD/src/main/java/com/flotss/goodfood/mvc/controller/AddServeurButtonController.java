package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.view.GestionServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * The type Add serveur button controller.
 */
public class AddServeurButtonController implements EventHandler<ActionEvent> {

    private final TextField emailTF;
    private final TextField nomServTF;
    private final TextField gradeTF;
    private final TextField mdpTF;
    private final Label errorSuccessLabel;

    private final GestionServeurView gestionServeurView;

    /**
     * Instantiates a new Add serveur button controller.
     *
     * @param emailTF            the email tf
     * @param nomServTF          the nom serv tf
     * @param gradeTF            the grade tf
     * @param mdpTF              the mdp tf
     * @param errorSuccessLabel  the error success label
     * @param gestionServeurView the gestion serveur view
     */
    public AddServeurButtonController(TextField emailTF, TextField nomServTF, TextField gradeTF, TextField mdpTF, Label errorSuccessLabel, GestionServeurView gestionServeurView) {
        this.emailTF = emailTF;
        this.nomServTF = nomServTF;
        this.gradeTF = gradeTF;
        this.mdpTF = mdpTF;
        this.errorSuccessLabel = errorSuccessLabel;
        this.gestionServeurView = gestionServeurView;
    }

    @Override
    public void handle(ActionEvent event) {
        errorSuccessLabel.setVisible(true);

        if (!verificationChamps()) {
            errorSuccessLabel.setText("Veuillez remplir tous les champs");
            errorSuccessLabel.setTextFill(Color.RED);
            return;
        }

        try {
            GoodFoodApplication.MODEL.ajouterServeur(emailTF.getText(), nomServTF.getText(), gradeTF.getText(), mdpTF.getText());
            errorSuccessLabel.setText("Le serveur a bien été ajouté");
            errorSuccessLabel.setTextFill(Color.GREEN);
            gestionServeurView.update();
        } catch (Exception e) {
            errorSuccessLabel.setText(e.getMessage());
            errorSuccessLabel.setTextFill(Color.RED);
        }
    }

    /**
        * Verification champs boolean.
        *
        * @return the boolean
        */
    private boolean verificationChamps() {
        return !emailTF.getText().isEmpty() && !nomServTF.getText().isEmpty() && !gradeTF.getText().isEmpty() && !mdpTF.getText().isEmpty();
    }


}
