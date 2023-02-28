package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.database.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The type Consult tables button controller.
 */
public class ConsultTablesButtonController implements EventHandler<ActionEvent> {

    private final DatePicker datePickerTable;
    private final ChoiceBox<String> choiceBox;
    private final Label error;

    /**
     * Instantiates a new Consult tables button controller.
     *
     * @param datePickerTable the date picker table
     * @param choiceBox       the choice box
     * @param error           the error
     */
    public ConsultTablesButtonController(DatePicker datePickerTable, ChoiceBox<String> choiceBox, Label error) {
        this.datePickerTable = datePickerTable;
        this.choiceBox = choiceBox;
        this.error = error;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Consultation des tables");

        String date = datePickerTable.getValue().toString();
        String time = choiceBox.getValue();
        String dateTime = date + " " + time;

        if (dateTime.contains("Choisir une heure")) {
            error.setVisible(true);
            error.setText("Veuillez choisir une heure");
            return;
        } else {
            error.setVisible(false);
        }

        HBox resultat = null;
        try {
            PreparedStatement preparedStatement = GoodFoodApplication.MODEL.getDbConnection().
                    prepareStatement("""
                            select numtab from tabl
                            minus
                            select numtab from reservation where datres = to_date( ? ,'yyyy/mm/dd hh24:mi')
                            """);
            preparedStatement.setString(1, dateTime);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultat = DBUtils.dumpResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(resultat));

        stage.show();
    }
}
