package com.flotss.goodfood.mvc.scene;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.controller.GestionServeurController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import com.flotss.goodfood.mvc.view.GestionPlatView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;


class GestionnairePage extends Pane {

    public GestionnairePage() {
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

        Button serviceButton = new Button("Service");
        serviceButton.setLayoutX(1125);
        serviceButton.setLayoutY(10);
        serviceButton.setOnAction(event -> GoodFoodApplication.STAGE.setScene(ScenesEnum.PAGESERVEUR.getScene()));
        this.getChildren().add(serviceButton);


        // Consulter les serveurs pour les modifiers
        VBox vBoxServeur = ComposantUtils.createVBox(5);
        vBoxServeur.setLayoutX(50);
        vBoxServeur.setLayoutY(50);

        Label consultServeurLabel = ComposantUtils.createLabel("Gestion des serveurs", 15);
        Button consultServeurButton = new Button("Consulter les serveurs");
        consultServeurButton.setOnAction(new GestionServeurController());

        vBoxServeur.getChildren().addAll(consultServeurLabel, consultServeurButton);
        this.getChildren().add(vBoxServeur);


        // Consulter les plats pour les modifiers
        VBox vBoxPlat = ComposantUtils.createVBox(5);
        vBoxPlat.setLayoutX(200);
        vBoxPlat.setLayoutY(50);

        Label consultPlatLabel = ComposantUtils.createLabel("Gestion des plats", 15);
        Button consultPlatButton = new Button("Consulter les plats");
        consultPlatButton.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Gestion des plats");


            GestionPlatView gestionPlatView = null;
            try {
                gestionPlatView = new GestionPlatView();
            } catch (SQLException e) {
                e.getStackTrace();
            }


            stage.setScene(new Scene(gestionPlatView));
            stage.show();
        });

        vBoxPlat.getChildren().addAll(consultPlatLabel, consultPlatButton);
        this.getChildren().add(vBoxPlat);


    }
}
