package com.flotss.goodfood.mvc.scene;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.controller.GestionServeurController;
import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


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

        Label consultServeurLabel = ComposantUtils.createLabel("Création et mise à jour des serveurs", 15);
        Button consultServeurButton = new Button("Consulter les serveurs");
        consultServeurButton.setOnAction(new GestionServeurController());

        vBoxServeur.getChildren().addAll(consultServeurLabel, consultServeurButton);
        this.getChildren().add(vBoxServeur);

    }
}
