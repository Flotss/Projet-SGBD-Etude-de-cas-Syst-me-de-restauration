module com.flotss.goodfood.goodfood {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc8;
    requires ini4j;

    opens com.goodfood.app to javafx.fxml;
    exports com.goodfood.app;

}