package com.goodfood.app.mvc.controller;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.view.AffectationServeurView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.sql.Date;

/**
 * The type Suppr affectation serveur controller.
 */
public class SupprAffectationServeurController implements EventHandler<ActionEvent> {

    private final int numServ;
    private final int numTab;
    private final Date date;
    private final AffectationServeurView affectationServeurView;


    /**
     * Instantiates a new Suppr affectation serveur controller.
     *
     * @param numServ                the num serv
     * @param numtab                 the numtab
     * @param date                   the date
     * @param affectationServeurView the affectation serveur view
     */
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
