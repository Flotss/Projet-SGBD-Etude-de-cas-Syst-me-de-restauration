package com.flotss.goodfood.database;

import com.flotss.goodfood.mvc.utils.ComposantUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * The type Db utils.
 */
public class DBUtils {

    /**
     * Dump result set h box.
     *
     * @param resultSet the result set
     * @return the h box
     * @throws SQLException the sql exception
     */
    public static HBox dumpResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        HBox hBoxMain = new HBox();
        VBox[] vBoxs = new VBox[metaData.getColumnCount()];
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
//            System.out.print(metaData.getColumnName(i) + "\t".repeat(3));
            vBoxs[i - 1] = new VBox();
            vBoxs[i - 1].getChildren().add(ComposantUtils.createLabel(metaData.getColumnName(i), 11));
            vBoxs[i - 1].setBorder(Border.stroke(Color.BLACK));
            vBoxs[i - 1].setPadding(new Insets(3));
            vBoxs[i - 1].setAlignment(Pos.CENTER);
        }
        hBoxMain.getChildren().addAll(vBoxs);

        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.print(resultSet.getObject(i) + "\t".repeat(3));

                // Verify if the object is null
                if (resultSet.getObject(i) == null) {
                    vBoxs[i - 1].getChildren().add(ComposantUtils.createLabel("NULL", 11));
                } else {
                    vBoxs[i - 1].getChildren().add(ComposantUtils.createLabel(resultSet.getObject(i).toString(), 11));
                }
            }
        }
        return hBoxMain;
    }

//    public static void clearTable() throws SQLException {
//        Connection con = DBConnection.makeConnection();
//        con.prepareStatement("DELETE FROM ETUDIANT").execute();
//        con.commit();
//        con.close();
//    }

}
