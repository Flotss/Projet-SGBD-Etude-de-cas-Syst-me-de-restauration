package com.flotss.goodfood.mvc.view;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.controller.AddServeurButtonController;
import com.flotss.goodfood.mvc.controller.AffectationServeurButtonController;
import com.flotss.goodfood.mvc.controller.UpdateServeurButtonController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionServeurView extends VBox {

    private final Label errorSuccessLabel;
    private final VBox resultat;

    public GestionServeurView() {
        super();
        VBox top = ComposantUtils.createVBox(5);
        resultat = ComposantUtils.createVBox(5);

        Label label = ComposantUtils.createLabel("Gestion des serveurs", 20);
        errorSuccessLabel = ComposantUtils.createLabel("", 15);

        top.getChildren().addAll(label, errorSuccessLabel);
        this.getChildren().addAll(top, resultat);
        update();
    }


    public void update() {
        resultat.getChildren().clear();
        Connection connection = GoodFoodApplication.MODEL.getDbConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NUMSERV, EMAIL, NOMSERV, GRADE FROM serveur");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                HBox ligne = ComposantUtils.createHBox(5);
                TextField numServTF = ComposantUtils.createTextField(resultSet.getString(1), 15);
                numServTF.setEditable(false);
                ligne.getChildren().add(numServTF);
                for (int i = 1; i < resultSet.getMetaData().getColumnCount(); i++) {
                    ligne.getChildren().add(ComposantUtils.createTextField(resultSet.getString(i + 1), 15));
                }

                // Mise à jour et suppression
                Button updateButton = new Button("Mettre à jour");
                updateButton.setOnAction(new UpdateServeurButtonController(ligne, errorSuccessLabel, this));
                Button supprButton = new Button("Supprimer");
                supprButton.setOnAction(new UpdateServeurButtonController(ligne, errorSuccessLabel, this));

                // Affectation des tables
                Button affectationButton = new Button("Affecter");
                affectationButton.setOnAction(new AffectationServeurButtonController(resultSet.getString(1), resultSet.getString(3)));




                ligne.getChildren().addAll(updateButton, supprButton, affectationButton);
                resultat.getChildren().add(ligne);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }


        // Ajouter un serveur
        HBox addServeur = ComposantUtils.createHBox(5);
        VBox emailVBox = ComposantUtils.createVBox(2);
        Label emailLabel = ComposantUtils.createLabel("Email", 12);
        TextField emailTF = ComposantUtils.createTextField("", 15);
        emailVBox.getChildren().addAll(emailLabel, emailTF);

        VBox nomServVBox = ComposantUtils.createVBox(2);
        Label nomServLabel = ComposantUtils.createLabel("Nom", 12);
        TextField nomServTF = ComposantUtils.createTextField("", 15);
        nomServVBox.getChildren().addAll(nomServLabel, nomServTF);

        VBox gradeVBox = ComposantUtils.createVBox(2);
        Label gradeLabel = ComposantUtils.createLabel("Grade", 12);
        TextField gradeTF = ComposantUtils.createTextField("", 15);
        gradeVBox.getChildren().addAll(gradeLabel, gradeTF);

        VBox mdpVBox = ComposantUtils.createVBox(2);
        Label mdpLabel = ComposantUtils.createLabel("Mot de passe", 12);
        TextField mdpTF = ComposantUtils.createPasswordField("", 15);
        mdpVBox.getChildren().addAll(mdpLabel, mdpTF);

        Button addButton = new Button("Ajouter");
        addButton.setTranslateY(20);
        addButton.setOnAction(new AddServeurButtonController(emailTF, nomServTF, gradeTF, mdpTF, errorSuccessLabel, this));

        addServeur.getChildren().addAll(emailVBox, nomServVBox, gradeVBox, mdpVBox, addButton);
        resultat.getChildren().add(addServeur);

    }

}
