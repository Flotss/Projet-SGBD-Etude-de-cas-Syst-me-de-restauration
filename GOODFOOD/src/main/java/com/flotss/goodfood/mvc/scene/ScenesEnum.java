package com.flotss.goodfood.mvc.scene;


import javafx.scene.Scene;

public enum ScenesEnum {
    LOGIN("login"),
    LANDINGPAGE("landingpage");

    private Scene scene;

    private ScenesEnum(String name) {
        switch (name) {
            case "login":
                this.scene = new Scene(new LoginPage(), 500, 500);
                break;
            case "landingpage":
                this.scene = new Scene(new LandingPage(), 1000, 1000);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }

    public Scene getScene() {
        return this.scene;
    }

}
