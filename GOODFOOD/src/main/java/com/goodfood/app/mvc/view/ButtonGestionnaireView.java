package com.goodfood.app.mvc.view;

import com.goodfood.app.GoodFoodApplication;
import com.goodfood.app.mvc.scene.ScenesEnum;
import javafx.scene.control.Button;

public class ButtonGestionnaireView extends Button implements Observateur {

    public ButtonGestionnaireView(String text, int x, int y) {
        super(text);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setOnAction(event -> GoodFoodApplication.STAGE.setScene(ScenesEnum.GESTIONNAIREPAGE.getScene()));
    }

    @Override
    public void update() {
        this.setDisable(! GoodFoodApplication.MODEL.isGestionnaire());
    }
}
