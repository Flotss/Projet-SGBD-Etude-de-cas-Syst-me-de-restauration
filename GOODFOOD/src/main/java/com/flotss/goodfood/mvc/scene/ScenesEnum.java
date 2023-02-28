package com.flotss.goodfood.mvc.scene;


import javafx.scene.Scene;

import java.sql.SQLException;

/**
 * The enum Scenes enum.
 */
public enum ScenesEnum {
    /**
     * Login scenes enum.
     */
    LOGIN("login"),
    /**
     * Pageserveur scene enum.
     */
    PAGESERVEUR("pageserveur"),
    /**
     * Error scene enum.
     */
    ERROR_SCENE("error_scene"),
    /**
     * Gestionnairepage scene enum.
     */
    GESTIONNAIREPAGE("gestionnairepage");

    private final Scene scene;

    ScenesEnum(String name) {
        switch (name) {
            case "login":
                this.scene = new Scene(new LoginPage(), 400, 250);
                break;
            case "pageserveur":
                this.scene = new Scene(new ServeurPage(), 1200, 400);
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

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return this.scene;
    }

}
