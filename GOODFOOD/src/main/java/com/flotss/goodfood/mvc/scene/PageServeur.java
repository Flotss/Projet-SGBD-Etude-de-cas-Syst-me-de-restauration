package com.flotss.goodfood.mvc.scene;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.controller.ConsultPlatButtonController;
import com.flotss.goodfood.mvc.controller.ConsultTablesButtonController;
import com.flotss.goodfood.mvc.controller.ReservePlatButtonController;
import com.flotss.goodfood.mvc.controller.ReserveTableButtonController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


class PageServeur extends Pane {

    public PageServeur() {
        super();

        // Boutton de déconnexion
        Button logoutButton = new Button("Déconnexion");
        logoutButton.setLayoutX(10);
        logoutButton.setLayoutY(10);
        logoutButton.setOnAction(event -> {
            GoodFoodApplication.STAGE.setScene(ScenesEnum.LOGIN.getScene());
            GoodFoodApplication.MODEL.disconnectDB();
        });

        this.getChildren().add(logoutButton);

        if (GoodFoodApplication.MODEL.isGestionnaire()){
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
        VBox vBoxReserverPlat = ComposantUtils.createVBox(0);
        vBoxReserverPlat.setLayoutX(500);
        vBoxReserverPlat.setLayoutY(200);

        Label reserverPlatLabel = ComposantUtils.createLabel("Réserver un plat", 15);
        Label errorLabel3 = ComposantUtils.createLabel("", 12);
        errorLabel3.setTextFill(Color.RED);
        errorLabel3.setVisible(false);

        HBox hBox3 = new HBox();

        // Numéro de reservation
        VBox vBoxNumberReservation = ComposantUtils.createVBox(5);
        Label numberReservationLabel = ComposantUtils.createLabel("Numéro de réservation", 12);
        ChoiceBox<Integer> numberReservationChBox = new ChoiceBox<>();
        try {
            numberReservationChBox.getItems().addAll(GoodFoodApplication.MODEL.getReservationToList());
        } catch (SQLException e) {
            GoodFoodApplication.STAGE.setScene(ScenesEnum.ERROR_SCENE.getScene());
        }
        vBoxNumberReservation.getChildren().addAll(numberReservationLabel, numberReservationChBox);

        // Numéro de plat
        VBox vBoxNumberPlat = ComposantUtils.createVBox(5);
        Label platLabel = ComposantUtils.createLabel("Plat", 12);
        ChoiceBox<String> platChBox = new ChoiceBox<>();
        platChBox.setValue("Choisir un plat");
        try {
            List<String> plats = GoodFoodApplication.MODEL.getPlatsToList();
            platChBox.getItems().addAll(plats);
            platChBox.setValue(plats.get(0));
        } catch (SQLException e) {
            GoodFoodApplication.STAGE.setScene(ScenesEnum.ERROR_SCENE.getScene());
        }
        vBoxNumberPlat.getChildren().addAll(platLabel, platChBox);

        // Quantité de plat
        VBox vBoxQuantityPlat = ComposantUtils.createVBox(5);
        Label quantityPlatLabel = ComposantUtils.createLabel("Quantité", 12);
        TextField quantityPlatTextField = ComposantUtils.createNumberTextField("1", 12);
        quantityPlatTextField.setPrefWidth(50);
        vBoxQuantityPlat.getChildren().addAll(quantityPlatLabel, quantityPlatTextField);

        Button reserverPlatButton = new Button("Réserver un plat");

        Label successLabel2 = ComposantUtils.createLabel("", 12);
        successLabel2.setTextFill(Color.GREEN);
        successLabel2.setVisible(false);

        reserverPlatButton.setOnAction(new ReservePlatButtonController(numberReservationChBox, platChBox, quantityPlatTextField, successLabel2, errorLabel3));

        hBox3.getChildren().addAll(vBoxNumberReservation, vBoxNumberPlat, vBoxQuantityPlat);
        vBoxReserverPlat.getChildren().addAll(reserverPlatLabel, errorLabel3, hBox3, successLabel2, reserverPlatButton);

        this.getChildren().add(vBoxReserverPlat);
    }
}
