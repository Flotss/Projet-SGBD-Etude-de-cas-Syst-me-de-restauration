package com.goodfood.app.mvc.view;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.database.DBUtils;
import com.goodfood.app.mvc.utils.ComposantUtils;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The type Statistique serveur view.
 */
public class StatistiqueServeurView extends VBox implements Observateur {

    private final DatePicker beginDate;
    private final DatePicker endDate;

    private final Pane resServeurActif;
    private final Pane resServeurInactif;

    /**
     * Instantiates a new Statistique serveur view.
     */
    public StatistiqueServeurView() {
        super();
        this.setSpacing(5);

        Label statsServeurLabel = ComposantUtils.createLabel("Statistique serveur", 15);

        this.beginDate = new DatePicker();
        this.beginDate.setValue(LocalDate.of(2021, 9, 1));
        this.beginDate.setOnAction(event -> update());

        this.endDate = new DatePicker();
        this.endDate.setValue(LocalDate.of(2021, 9, 15));
        this.endDate.setOnAction(event -> update());

        this.resServeurActif = new HBox();

        Label serveurInactifLabel = ComposantUtils.createLabel("Serveur inactif", 15);
        this.resServeurInactif = new HBox();

        this.getChildren().addAll(statsServeurLabel, beginDate, endDate, resServeurActif, serveurInactifLabel, resServeurInactif);

        update();
    }


    @Override
    public void update() {
        ResultSet resultSet = GoodFoodApplication.MODEL.getStatsServeur(beginDate.getValue().toString(), endDate.getValue().toString());
        try {
            resServeurActif.getChildren().clear();
            resServeurActif.getChildren().add(DBUtils.dumpResultSet(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultSet2 = GoodFoodApplication.MODEL.getServeurInactif(beginDate.getValue().toString(), endDate.getValue().toString());
        try {
            resServeurInactif.getChildren().clear();
            resServeurInactif.getChildren().add(DBUtils.dumpResultSet(resultSet2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
