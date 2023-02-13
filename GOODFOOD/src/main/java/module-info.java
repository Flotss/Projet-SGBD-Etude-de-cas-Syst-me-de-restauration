module com.flotss.goodfood.goodfood {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.flotss.goodfood to javafx.fxml;
    exports com.flotss.goodfood;

}