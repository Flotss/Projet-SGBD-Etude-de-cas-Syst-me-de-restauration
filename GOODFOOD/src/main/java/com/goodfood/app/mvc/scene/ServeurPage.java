package com.goodfood.app.mvc.scene;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.controller.ConsultPlatButtonController;
import com.goodfood.app.mvc.controller.ConsultTablesButtonController;
import com.goodfood.app.mvc.controller.DisconnectButtonController;
import com.goodfood.app.mvc.controller.ReserveTableButtonController;
import com.goodfood.app.mvc.utils.ComposantUtils;
import com.goodfood.app.mvc.view.ReservationPlatView;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.time.LocalDate;


/**
 * The type Page serveur.
 */
class ServeurPage extends Pane {

    /**
     * Instantiates a new Page serveur.
     */
    public ServeurPage() {
        super();

        // Boutton de déconnexion
        Button logoutButton = new Button("Déconnexion");
        logoutButton.setLayoutX(10);
        logoutButton.setLayoutY(10);
        logoutButton.setOnAction(new DisconnectButtonController());

        this.getChildren().add(logoutButton);

        if (GoodFoodApplication.MODEL.isGestionnaire()) {
            Button gestionnaireButton = new Button("Gestion");
            gestionnaireButton.setLayoutX(1125);
            gestionnaireButton.setLayoutY(10);
            gestionnaireButton.setOnAction(event -> GoodFoodApplication.STAGE.setScene(ScenesEnum.GESTIONNAIREPAGE.getScene()));
            this.getChildren().add(gestionnaireButton);
        }


        // Consulter les tables disponibles
        VBox vBoxTableDispo = ComposantUtils.createVBox(5);
        vBoxTableDispo.setLayoutX(50);
        vBoxTableDispo.setLayoutY(50);

        Label consultTablesLabel = ComposantUtils.createLabel("Consulter les tables disponibles", 15);
        Label errorLabel = ComposantUtils.createLabel("", 12);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        HBox hBox = ComposantUtils.createHBox(5);
        DatePicker datePickerTable = new DatePicker();
        datePickerTable.setValue(LocalDate.of(2021, 9, 10));

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("10:00", "11:00", "12:00", "13:00", "14:00", "19:00", "20:00", "21:00", "22:00");
        choiceBox.setValue("Choisir une heure");

        Button consultTablesButton = new Button("Voir les tables disponibles");
        consultTablesButton.setOnAction(new ConsultTablesButtonController(datePickerTable, choiceBox, errorLabel));

        hBox.getChildren().addAll(datePickerTable, choiceBox);
        vBoxTableDispo.getChildren().addAll(consultTablesLabel, errorLabel, hBox, consultTablesButton);

        this.getChildren().add(vBoxTableDispo);


        // Réserver une table
        VBox vBoxReserverTable = ComposantUtils.createVBox(5);
        vBoxReserverTable.setLayoutX(500);
        vBoxReserverTable.setLayoutY(50);

        Label reserverTableLabel = ComposantUtils.createLabel("Réserver une table", 15);
        Label errorLabel2 = ComposantUtils.createLabel("", 12);
        errorLabel2.setTextFill(Color.RED);
        errorLabel2.setVisible(false);

        HBox hBox2 = new HBox();

        // Date
        VBox vBoxDate = ComposantUtils.createVBox(5);
        Label dateLabel = ComposantUtils.createLabel("Date", 12);
        DatePicker datePickerTable2 = new DatePicker();
        datePickerTable2.setValue(LocalDate.of(2021, 9, 10));
        vBoxDate.getChildren().addAll(dateLabel, datePickerTable2);


        // Heure
        VBox vBoxHour = ComposantUtils.createVBox(5);
        Label hourLabel = ComposantUtils.createLabel("Heure", 12);
        ChoiceBox<String> choiceBoxHourTable = new ChoiceBox<>();
        choiceBoxHourTable.getItems().addAll("10:00", "11:00", "12:00", "13:00", "14:00", "19:00", "20:00", "21:00", "22:00");
        choiceBoxHourTable.setValue("Choisir une heure");
        vBoxHour.getChildren().addAll(hourLabel, choiceBoxHourTable);

        // Numéro de table
        VBox vBoxNumberTable = ComposantUtils.createVBox(5);
        Label numberTableLabel = ComposantUtils.createLabel("Numéro de table", 12);
        ChoiceBox<Integer> numberTableChBox = new ChoiceBox<>();
        try {
            numberTableChBox.getItems().addAll(GoodFoodApplication.MODEL.getTableToList());
        } catch (SQLException e) {
            GoodFoodApplication.STAGE.setScene(ScenesEnum.ERROR_SCENE.getScene());
        }
        vBoxNumberTable.getChildren().addAll(numberTableLabel, numberTableChBox);

        // Nombre de personne à la table
        VBox vBoxNumberPerson = ComposantUtils.createVBox(5);
        Label numberPersonLabel = ComposantUtils.createLabel("Nombre de personne", 12);
        TextField numberPersonTextField = ComposantUtils.createNumberTextField("1", 12);
        vBoxNumberPerson.getChildren().addAll(numberPersonLabel, numberPersonTextField);

        Button reserverTableButton = new Button("Réserver une table");

        Label successLabel = ComposantUtils.createLabel("", 12);
        successLabel.setTextFill(Color.GREEN);
        successLabel.setVisible(false);

        reserverTableButton.setOnAction(new ReserveTableButtonController(numberTableChBox, datePickerTable2, choiceBoxHourTable, numberPersonTextField, errorLabel2, successLabel));

        hBox2.getChildren().addAll(vBoxDate, vBoxHour, vBoxNumberTable, vBoxNumberPerson);
        vBoxReserverTable.getChildren().addAll(reserverTableLabel, errorLabel2, hBox2, reserverTableButton, successLabel);

        this.getChildren().add(vBoxReserverTable);


        // Consulter les plats pour une eventuelle commande
        VBox vBoxPlats = ComposantUtils.createVBox(5);
        vBoxPlats.setLayoutX(50);
        vBoxPlats.setLayoutY(200);

        Label platsLabel = ComposantUtils.createLabel("Consulter les plats", 15);
        Button platsButton = new Button("Voir les plats");
        platsButton.setOnAction(new ConsultPlatButtonController());

        vBoxPlats.getChildren().addAll(platsLabel, platsButton);

        this.getChildren().add(vBoxPlats);


        // Reservation d'un plat pour une réservation de table
        ReservationPlatView reservationPlatView = new ReservationPlatView();
        reservationPlatView.setLayoutX(500);
        reservationPlatView.setLayoutY(200);
        GoodFoodApplication.MODEL.addObserver(reservationPlatView);

        this.getChildren().add(reservationPlatView);
    }
}
