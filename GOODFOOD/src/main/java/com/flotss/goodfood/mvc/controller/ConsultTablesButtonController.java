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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.HandlerBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultTablesButtonController implements EventHandler<ActionEvent> {

    private DatePicker datePickerTable;
    private ChoiceBox<String> choiceBox;
    private Label error;

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
        System.out.println(dateTime);

        if (dateTime.contains("Choisir une heure")){
            error.setVisible(true);
            error.setText("Veuillez choisir une heure");
            return;
        }else{
            error.setVisible(false);
        }

        HBox resultat = null;
        try{
            PreparedStatement preparedStatement = GoodFoodApplication.MODEL.getDbConnection().
                    prepareStatement("select numtab from tabl\n" +
                                        "minus\n" +
                                        "select numtab from reservation where datres = to_date( ? ,'yyyy/mm/dd hh24:mi')");
            preparedStatement.setString(1, dateTime);
            preparedStatement.executeQuery();
            ResultSet  resultSet = preparedStatement.getResultSet();
            resultat = DBUtils.dumpResultSet(resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }

        stage.setScene(new Scene(resultat));

        stage.show();
    }
}
