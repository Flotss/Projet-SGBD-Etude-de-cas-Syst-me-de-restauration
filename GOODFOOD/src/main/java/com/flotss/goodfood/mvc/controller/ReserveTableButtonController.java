package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReserveTableButtonController implements EventHandler<ActionEvent> {

    private final ChoiceBox<Integer> tableNumber;
    private final DatePicker datePickerTable;
    private final ChoiceBox<String> hourChoiceBox;
    private final TextField numberPersonTextField;
    private final Label errorLabel;
    private final Label successLabel;

    public ReserveTableButtonController(ChoiceBox<Integer> tableNumber, DatePicker datePickerTable, ChoiceBox<String> hourChoiceBox, TextField numberPersonTextField, Label errorLabel, Label successLabel) {
        this.tableNumber = tableNumber;
        this.datePickerTable = datePickerTable;
        this.hourChoiceBox = hourChoiceBox;
        this.numberPersonTextField = numberPersonTextField;
        this.errorLabel = errorLabel;
        this.successLabel = successLabel;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Model model = GoodFoodApplication.MODEL;

        if (! verificationDonnee()) return;

        try {
            model.reserveTable(tableNumber.getValue(), datePickerTable.getValue()+" "+hourChoiceBox.getValue(), Integer.parseInt(numberPersonTextField.getText()));
            successLabel.setVisible(true);
            errorLabel.setVisible(false);
            successLabel.setText("La table n°" + hourChoiceBox.getValue() + " a bien été réservée pour le " + datePickerTable.getValue());
        } catch (Exception e) {
            successLabel.setVisible(false);
            errorLabel.setVisible(true);
            errorLabel.setText("La table n°" + tableNumber.getValue() + " n'a pas pu être réservée pour le " + datePickerTable.getValue() + " à " + hourChoiceBox.getValue() + ". Veuillez réessayer.");
            e.printStackTrace();
        }

    }


    private boolean verificationDonnee(){
        if (hourChoiceBox.getValue().contains("Choisir une heure")){
            errorLabel.setVisible(true);
            errorLabel.setText("Veuillez choisir une heure");
            return false;
        }else{
            errorLabel.setVisible(false);
        }

        if (tableNumber.getValue() == null){
            errorLabel.setVisible(true);
            errorLabel.setText("Veuillez choisir une table");
            return false;
        }else{
            errorLabel.setVisible(false);
        }

        if (numberPersonTextField.getText().isEmpty()){
            errorLabel.setVisible(true);
            errorLabel.setText("Veuillez indiquer le nombre de personnes");
            return false;
        }else{
            errorLabel.setVisible(false);
        }

        return true;
    }
}
