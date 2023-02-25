package com.flotss.goodfood.mvc.scene;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.component.NumberTextField;
import com.flotss.goodfood.mvc.controller.ConsultTablesButtonController;
import com.flotss.goodfood.mvc.controller.ReserveTableButtonController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.SQLException;
import java.time.LocalDate;


class LandingPage extends Pane {

    public LandingPage()  {
        super();

        // Consulter les tables disponibles
        VBox vBoxTableDispo = new VBox();
        vBoxTableDispo.setSpacing(5);
        vBoxTableDispo.setLayoutX(50);
        vBoxTableDispo.setLayoutY(50);

        Label consultTablesLabel = ComposantUtils.createLabel("Consulter les tables disponibles", 15);
        Label errorLabel = ComposantUtils.createLabel("", 12);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        HBox hBox = new HBox();
        DatePicker datePickerTable = new DatePicker();
        datePickerTable.setValue(LocalDate.of(2021, 9, 10));

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll( "10:00", "11:00", "12:00", "13:00", "14:00", "19:00", "20:00", "21:00", "22:00");
        choiceBox.setValue("Choisir une heure");

        Button consultTablesButton = new Button("Voir les tables disponibles");
        consultTablesButton.setOnAction(new ConsultTablesButtonController(datePickerTable, choiceBox, errorLabel));

        hBox.getChildren().addAll(datePickerTable, choiceBox);
        vBoxTableDispo.getChildren().addAll(consultTablesLabel, errorLabel, hBox, consultTablesButton);

        this.getChildren().add(vBoxTableDispo);



        // Réserver une table
        VBox vBoxReserverTable = ComposantUtils.createVBox(5);
        vBoxReserverTable.setLayoutX(50);
        vBoxReserverTable.setLayoutY(180);

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
        choiceBoxHourTable.getItems().addAll( "10:00", "11:00", "12:00", "13:00", "14:00", "19:00", "20:00", "21:00", "22:00");
        choiceBoxHourTable.setValue("Choisir une heure");
        vBoxHour.getChildren().addAll(hourLabel, choiceBoxHourTable);

        // Numéro de table
        VBox vBoxNumberTable = ComposantUtils.createVBox(5);
        vBoxNumberTable.setSpacing(5);
        Label numberTableLabel = ComposantUtils.createLabel("Numéro de table", 12);
        ChoiceBox<Integer> numberTableChBox = new ChoiceBox<>();
        try {
            numberTableChBox.getItems().addAll(GoodFoodApplication.MODEL.getTable());
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

        reserverTableButton.setOnAction(new ReserveTableButtonController(numberTableChBox ,datePickerTable2, choiceBoxHourTable, numberPersonTextField, errorLabel2, successLabel));

        hBox2.getChildren().addAll(vBoxDate, vBoxHour, vBoxNumberTable, vBoxNumberPerson);
        vBoxReserverTable.getChildren().addAll(reserverTableLabel, errorLabel2, hBox2, reserverTableButton, successLabel);

        this.getChildren().add(vBoxReserverTable);
    }
}
