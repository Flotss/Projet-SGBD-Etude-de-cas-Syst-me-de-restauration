package com.flotss.goodfood.mvc.scene;


import javafx.scene.Scene;

import java.sql.SQLException;

public enum ScenesEnum {
    LOGIN("login"),
    PAGESERVEUR("pageserveur"), ERROR_SCENE("error_scene"), GESTIONNAIREPAGE("gestionnairepage");

    private final Scene scene;

    ScenesEnum(String name) {
        switch (name) {
            case "login":
                this.scene = new Scene(new LoginPage(), 400, 250);
                break;
            case "pageserveur":
                this.scene = new Scene(new PageServeur(), 1200, 400);
                break;
            case "gestionnairepage":
                try {
                    this.scene = new Scene(new GestionnairePage(), 1200, 800);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "error_scene":
                this.scene = new Scene(new ErrorScene("Erreur lors du chargement de la page"), 400, 250);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }

    public Scene getScene() {
        return this.scene;
    }

}
