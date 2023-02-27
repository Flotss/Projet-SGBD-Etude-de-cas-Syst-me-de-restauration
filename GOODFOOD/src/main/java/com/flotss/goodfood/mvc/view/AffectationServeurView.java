package com.flotss.goodfood.mvc.view;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.controller.SupprAffectationServeurController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class AffectationServeurView extends VBox implements Observateur {

    private final int numServ;

    private final VBox affectations;

    public AffectationServeurView(int numServ, String nomServeur) throws SQLException {
        super();
        this.setPadding(new Insets(10));

        this.numServ = numServ;
        this.affectations = ComposantUtils.createVBox(5);


        Label label = ComposantUtils.createLabel("Affectations du serveur " + nomServeur, 20);
        this.getChildren().addAll(label, affectations);

        // Ajout d'une affectation
        List<Integer> tables = GoodFoodApplication.MODEL.getTableToList();
        VBox ajoutAffectation = ComposantUtils.createVBox(5);
        Label labelAjoutAffectation = ComposantUtils.createLabel("Ajouter une affectation", 15);
        Label errorSuccessLabel = ComposantUtils.createLabel("", 15);
        HBox ligneAjoutAffectation = ComposantUtils.createHBox(5);

        // Numéro de table
        ChoiceBox<Integer> tablesChoiceBox = new ChoiceBox<>();
        tablesChoiceBox.getItems().addAll(tables);
        tablesChoiceBox.setValue(tables.get(0));

        // Date d'affectation
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(2021, 9, 10));

        // Bouton d'ajout
        Button buttonAjoutAffectation = new Button("Ajouter");
        buttonAjoutAffectation.setOnAction(event -> {
            Connection connection = GoodFoodApplication.MODEL.getDbConnection();
            try {
                PreparedStatement queryAjoutAffectation = connection.prepareStatement("INSERT INTO AFFECTER VALUES (?, ?, ?)");
                queryAjoutAffectation.setInt(1, tablesChoiceBox.getValue());
                queryAjoutAffectation.setDate(2, Date.valueOf(datePicker.getValue()));
                queryAjoutAffectation.setInt(3, numServ);
                queryAjoutAffectation.execute();
                update();
                errorSuccessLabel.setText("Affectation ajoutée avec succès");
                errorSuccessLabel.setTextFill(Color.GREEN);
            } catch (SQLException e) {
                e.printStackTrace();
                errorSuccessLabel.setText("Erreur lors de l'ajout de l'affectation");
                errorSuccessLabel.setTextFill(Color.RED);
            }
        });

        ligneAjoutAffectation.getChildren().addAll(tablesChoiceBox, datePicker, buttonAjoutAffectation);
        ajoutAffectation.getChildren().addAll(labelAjoutAffectation, ligneAjoutAffectation, errorSuccessLabel);
        this.getChildren().add(ajoutAffectation);


        update();
    }


    public void update() {
        affectations.getChildren().clear();

        // Les affectations du serveur
        Connection connection = GoodFoodApplication.MODEL.getDbConnection();
        try {
            PreparedStatement queryAffectationList = connection.prepareStatement("SELECT NUMTAB, DATAFF FROM AFFECTER WHERE numServ = ?");
            queryAffectationList.setInt(1, numServ);
            queryAffectationList.execute();

            ResultSet resultSet = queryAffectationList.getResultSet();


            HBox ligneDesColonnes = ComposantUtils.createHBox(5);
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                ligneDesColonnes.getChildren().add(ComposantUtils.createLabel(resultSet.getMetaData().getColumnName(i + 1), 15));
            }
            affectations.getChildren().add(ligneDesColonnes);


            while (resultSet.next()) {
                HBox ligne = ComposantUtils.createHBox(5);
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    ligne.getChildren().add(ComposantUtils.createTextField(resultSet.getString(i + 1), 15));
                }
                Button affecterButton = new Button("Supprimer");
                affecterButton.setOnAction(new SupprAffectationServeurController(numServ, resultSet.getInt(1), resultSet.getDate(2), this));
                ligne.getChildren().add(affecterButton);
                affectations.getChildren().add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
