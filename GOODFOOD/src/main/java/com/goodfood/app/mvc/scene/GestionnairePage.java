package com.goodfood.app.mvc.scene;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.controller.DisconnectButtonController;
import com.goodfood.app.mvc.controller.GestionServeurController;
import com.goodfood.app.mvc.utils.ComposantUtils;
import com.goodfood.app.mvc.view.GestionPlatView;
import com.goodfood.app.mvc.view.ReglementView;
import com.goodfood.app.mvc.view.StatistiqueServeurView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;


/**
 * The type Gestionnaire page.
 */
class GestionnairePage extends Pane {

    /**
     * Instantiates a new Gestionnaire page.
     *
     * @throws SQLException the sql exception
     */
    public GestionnairePage() throws SQLException {
        super();

        // Boutton de déconnexion
        Button logoutButton = new Button("Déconnexion");
        logoutButton.setLayoutX(10);
        logoutButton.setLayoutY(10);
        logoutButton.setOnAction(new DisconnectButtonController());
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


        // View pour regler les reservations
        ReglementView reglementView = new ReglementView();
        reglementView.setLayoutX(350);
        reglementView.setLayoutY(50);
        GoodFoodApplication.MODEL.addObserver(reglementView);
        this.getChildren().add(reglementView);


        // Affichage (dans un ordre décroissant) du chiffre d’affaire et le nombre
        // de commandes réalisés par chaque serveur (nom, chiffre d’affaire,
        // nombre de commandes) en une période donnée (date début, date fin).
        VBox vBoxStatsServeur = ComposantUtils.createVBox(5);
        vBoxStatsServeur.setLayoutX(50);
        vBoxStatsServeur.setLayoutY(200);

        StatistiqueServeurView statistiqueServeurView = new StatistiqueServeurView();
        GoodFoodApplication.MODEL.addObserver(statistiqueServeurView);
        vBoxStatsServeur.getChildren().addAll(statistiqueServeurView);

        this.getChildren().add(vBoxStatsServeur);

    }
}
