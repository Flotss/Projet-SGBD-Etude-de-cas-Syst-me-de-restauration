package com.flotss.goodfood.mvc.view;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.List;

public class ReglementView extends VBox implements Observateur {

    private final ChoiceBox<Integer> cbReservation;
    private final Label montantApayer;
    private final Label errorSuccessLabel;

    public ReglementView() {
        super();
        this.setSpacing(5);

        // HBOX pour le label et la choicebox
        HBox hBoxReservationLabel = ComposantUtils.createHBox(5);
        Label reservationLabel = ComposantUtils.createLabel("Choisisser une réservation a encaisser", 15);
        // ChoiceBox des réservations
        this.cbReservation = new ChoiceBox<>();


        hBoxReservationLabel.getChildren().addAll(reservationLabel, cbReservation);
        this.getChildren().add(hBoxReservationLabel);

        // HBox pour le paiement
        HBox hBoxReservation = ComposantUtils.createHBox(5);
        this.montantApayer = ComposantUtils.createLabel("", 15);
        cbReservation.setOnAction(event -> montantApayer.setText("Montant à payer : " + GoodFoodApplication.MODEL.getMontantCommande(cbReservation.getValue()) + "€"));

        // Type de paiement
        ChoiceBox<String> cbTypePaiement = new ChoiceBox<>();
        cbTypePaiement.getItems().addAll("Espece", "Carte", "Cheque");
        cbTypePaiement.setValue("Carte");

        // Boutton de paiement
        Button bPayer = new Button("Payer");

        // Label d'erreur ou de succès
        this.errorSuccessLabel = ComposantUtils.createLabel("", 15);
        bPayer.setOnAction(event -> {
            try {
                GoodFoodApplication.MODEL.payerReservation(cbReservation.getValue(), cbTypePaiement.getValue(), montantApayer.getText());
                errorSuccessLabel.setText("Réservation payée");
                errorSuccessLabel.setTextFill(Color.GREEN);
            } catch (SQLException e) {
                e.getStackTrace();
                errorSuccessLabel.setText("Erreur lors du paiement");
                errorSuccessLabel.setTextFill(Color.RED);
            } catch (Exception e) {
                e.getStackTrace();
                errorSuccessLabel.setText("Veuillez choisir une réservation");
                errorSuccessLabel.setTextFill(Color.RED);
            }
        });
        hBoxReservation.getChildren().addAll(montantApayer, cbTypePaiement, bPayer);
        this.getChildren().addAll(hBoxReservation, errorSuccessLabel);
    }


    public void update() {
        this.errorSuccessLabel.setText("");
        try {
            List<Integer> listReservation = GoodFoodApplication.MODEL.getReservationNotPayedToList();
            if (listReservation.isEmpty()) {
                cbReservation.setDisable(true);
                this.montantApayer.setText("Montant à payer : 0€");
            } else {
                cbReservation.setDisable(false);
                cbReservation.getItems().clear();
                cbReservation.getItems().addAll(listReservation);
                cbReservation.setValue(listReservation.get(0));
                this.montantApayer.setText("Montant à payer : " + GoodFoodApplication.MODEL.getMontantCommande(cbReservation.getValue()) + "€");
            }
        } catch (SQLException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }

    }
}
