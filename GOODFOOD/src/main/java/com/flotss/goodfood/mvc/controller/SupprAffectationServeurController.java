package com.flotss.goodfood.mvc.controller;

import com.flotss.goodfood.GoodFoodApplication;
import com.flotss.goodfood.mvc.view.AffectationServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.sql.Date;

public class SupprAffectationServeurController implements EventHandler<ActionEvent> {

    private final int numServ;
    private final int numTab;
    private final Date date;
    private final AffectationServeurView affectationServeurView;


    public SupprAffectationServeurController(int numServ, int numtab, Date date, AffectationServeurView affectationServeurView) {
        this.numServ = numServ;
        this.numTab = numtab;
        this.date = date;
        this.affectationServeurView = affectationServeurView;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            GoodFoodApplication.MODEL.supprimerAffectationServeur(numTab, date, numServ);
            affectationServeurView.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
