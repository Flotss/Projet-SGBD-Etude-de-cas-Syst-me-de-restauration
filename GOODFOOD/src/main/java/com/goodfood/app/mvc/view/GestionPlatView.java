package com.goodfood.app.mvc.view;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.controller.AddPlatButtonController;
import com.goodfood.app.mvc.utils.ComposantUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * The type Gestion plat view.
 */
public class GestionPlatView extends VBox implements Observateur {


    private final VBox vPlats;
    private final Label errorSuccess;

    /**
     * Instantiates a new Gestion plat view.
     *
     * @throws SQLException the sql exception
     */
    public GestionPlatView() throws SQLException {
        super();
        Label label = new Label("Gestion des plats");
        this.errorSuccess = ComposantUtils.createLabel("", 15);

        vPlats = new VBox();
        this.getChildren().addAll(label, vPlats);

        // Ajouter un plat
        addPlat();


        update();
    }

    public void update() {
        vPlats.getChildren().clear();

        // Les affectations du serveur
        Connection connection = GoodFoodApplication.MODEL.getDbConnection();
        try {
            PreparedStatement queryPlatsList = connection.prepareStatement("SELECT NUMPLAT, LIBELLE, TYPE, PRIXUNIT, QTESERVIE FROM PLAT ORDER BY CASE WHEN TYPE = 'ENTREE' THEN 1 WHEN TYPE = 'PLAT' THEN 2 WHEN TYPE = 'VIANDE' THEN 3 WHEN TYPE = 'POISSON' THEN 4 WHEN TYPE = 'FROMAGE' THEN 5 WHEN TYPE = 'DESSERT' THEN 6 ELSE 7 END");
            queryPlatsList.execute();

            ResultSet resultSet = queryPlatsList.getResultSet();

            while (resultSet.next()) {
                TextField[] textFields = new TextField[resultSet.getMetaData().getColumnCount() - 1];
                HBox ligne = ComposantUtils.createHBox(5);
                for (int i = 1; i < resultSet.getMetaData().getColumnCount(); i++) {
                    TextField textField = ComposantUtils.createTextField(resultSet.getString(i + 1), 15);
                    textFields[i - 1] = textField;
                    ligne.getChildren().add(textField);
                }
                String libelle = resultSet.getString(2);
                Button supprimerButton = new Button("Supprimer");
                supprimerButton.setOnAction(event -> {
                    try {
                        GoodFoodApplication.MODEL.supprPlat(libelle);
                        errorSuccess.setText("Suppression réussie");
                        errorSuccess.setTextFill(Color.GREEN);
                        update();
                    } catch (SQLException e) {
                        errorSuccess.setText(Arrays.toString(e.getStackTrace()));
                        errorSuccess.setTextFill(Color.RED);
                    }
                });
                int numPlat = resultSet.getInt(1);
                Button modifierButton = new Button("Modifier");
                modifierButton.setOnAction(event -> {
                    try {
                        GoodFoodApplication.MODEL.modifPlat(numPlat, textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText());
                        errorSuccess.setText("Modification réussie");
                        errorSuccess.setTextFill(Color.GREEN);
                        update();
                    } catch (SQLException e) {
                        errorSuccess.setText(e.getMessage());
                        errorSuccess.setTextFill(Color.RED);
                    }
                });

                supprimerButton.setDisable(true);
                ligne.getChildren().addAll(modifierButton, supprimerButton);
                vPlats.getChildren().add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void addPlat() throws SQLException {
        // Ajout d'un plat
        VBox vAjoutPlat = ComposantUtils.createVBox(5);
        Label lAjoutPlat = ComposantUtils.createLabel("Ajouter un plat", 20);

        HBox hAjoutPlat = ComposantUtils.createHBox(5);
        // Nom du plat
        VBox vNomPlat = ComposantUtils.createVBox(5);
        Label lNomPlat = ComposantUtils.createLabel("Nom du plat", 15);
        TextField tfNomPlat = ComposantUtils.createTextField("", 15);
        vNomPlat.getChildren().addAll(lNomPlat, tfNomPlat);

        // Type du plat
        VBox vTypePlat = ComposantUtils.createVBox(5);
        Label lTypePlat = ComposantUtils.createLabel("Type du plat", 15);
        ChoiceBox<String> cbTypePlat = new ChoiceBox<>();
        cbTypePlat.getItems().addAll(GoodFoodApplication.MODEL.getTypePlatsToList());
        vTypePlat.getChildren().addAll(lTypePlat, cbTypePlat);

        // Prix du plat
        VBox vPrixPlat = ComposantUtils.createVBox(5);
        Label lPrixPlat = ComposantUtils.createLabel("Prix du plat", 15);
        TextField tfPrixPlat = ComposantUtils.createNumberTextField("", 15);
        vPrixPlat.getChildren().addAll(lPrixPlat, tfPrixPlat);

        // Quantité servie
        VBox vQuantitePlat = ComposantUtils.createVBox(5);
        Label lQuantitePlat = ComposantUtils.createLabel("Quantité servie", 15);
        TextField tfQuantitePlat = ComposantUtils.createNumberTextField("", 15);
        vQuantitePlat.getChildren().addAll(lQuantitePlat, tfQuantitePlat);

        // Ajout du plat
        Button bAjoutPlat = new Button("Ajouter");
        bAjoutPlat.setOnAction(new AddPlatButtonController(tfNomPlat, cbTypePlat, tfPrixPlat, tfQuantitePlat, errorSuccess, this));

        hAjoutPlat.getChildren().addAll(vNomPlat, vTypePlat, vPrixPlat, vQuantitePlat, bAjoutPlat);
        vAjoutPlat.getChildren().addAll(lAjoutPlat, errorSuccess, hAjoutPlat);

        this.getChildren().add(vAjoutPlat);
    }
}
