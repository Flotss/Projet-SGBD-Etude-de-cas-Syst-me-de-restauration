package com.goodfood.app.mvc.view;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.controller.ReservePlatButtonController;
import com.goodfood.app.mvc.scene.ScenesEnum;
import com.goodfood.app.mvc.utils.ComposantUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Reservation plat view.
 */
public class ReservationPlatView extends VBox implements Observateur {

    private final ChoiceBox<Integer> numberReservationChBox;
    private final ChoiceBox<String> platChBox;

    /**
     * Instantiates a new Reservation plat view.
     */
    public ReservationPlatView() {
        super();
        // Réserver une table
        this.setSpacing(5);

        Label reserverPlatLabel = ComposantUtils.createLabel("Réserver un plat", 15);
        Label errorLabel = ComposantUtils.createLabel("", 12);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        HBox hBox3 = new HBox();

        // Numéro de reservation
        VBox vBoxNumberReservation = ComposantUtils.createVBox(5);
        Label numberReservationLabel = ComposantUtils.createLabel("Numéro de réservation", 12);
        this.numberReservationChBox = new ChoiceBox<>();
        vBoxNumberReservation.getChildren().addAll(numberReservationLabel, numberReservationChBox);

        // Numéro de plat
        VBox vBoxNumberPlat = ComposantUtils.createVBox(5);
        Label platLabel = ComposantUtils.createLabel("Plat", 12);
        this.platChBox = new ChoiceBox<>();
        platChBox.setValue("Choisir un plat");
        vBoxNumberPlat.getChildren().addAll(platLabel, platChBox);

        // Quantité de plat
        VBox vBoxQuantityPlat = ComposantUtils.createVBox(5);
        Label quantityPlatLabel = ComposantUtils.createLabel("Quantité", 12);
        TextField quantityPlatTextField = ComposantUtils.createNumberTextField("1", 12);
        quantityPlatTextField.setPrefWidth(50);
        vBoxQuantityPlat.getChildren().addAll(quantityPlatLabel, quantityPlatTextField);

        Button reserverPlatButton = new Button("Réserver un plat");

        Label successLabel = ComposantUtils.createLabel("", 12);
        successLabel.setTextFill(Color.GREEN);
        successLabel.setVisible(false);

        reserverPlatButton.setOnAction(new ReservePlatButtonController(numberReservationChBox, platChBox, quantityPlatTextField, successLabel, errorLabel));

        hBox3.getChildren().addAll(vBoxNumberReservation, vBoxNumberPlat, vBoxQuantityPlat);
        this.getChildren().addAll(reserverPlatLabel, errorLabel, hBox3, successLabel, reserverPlatButton);
        update();
    }


    public void update() {
        try {
            List<String> plats = GoodFoodApplication.MODEL.getPlatsToList();
            platChBox.getItems().clear();
            platChBox.getItems().addAll(plats);
            platChBox.setValue(plats.get(0));
        } catch (SQLException e) {
            GoodFoodApplication.STAGE.setScene(ScenesEnum.ERROR_SCENE.getScene());
        }
        try {
            List<Integer> reservations = GoodFoodApplication.MODEL.getReservationToList();
            numberReservationChBox.getItems().clear();
            numberReservationChBox.getItems().addAll(reservations);
            numberReservationChBox.setValue(reservations.get(0));
        } catch (Exception e) {
            numberReservationChBox.setValue(null);
        }
    }
}
