package com.flotss.goodfood.mvc.scene;


import javafx.scene.Scene;

public enum ScenesEnum {
    LOGIN("login"),
    LANDINGPAGE("landingpage"), ERROR_SCENE("error_scene");

    private Scene scene;

    private ScenesEnum(String name) {
        switch (name) {
            case "login":
                this.scene = new Scene(new LoginPage(), 400, 250);
                break;
            case "landingpage":
                this.scene = new Scene(new LandingPage(), 1200, 800);
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
