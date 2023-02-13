package com.flotss.goodfood.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBUtils {

    public static void dumpResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.print(metaData.getColumnName(i) + "\t".repeat(3));
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.print(resultSet.getObject(i) + "\t".repeat(3));
            }
            System.out.println();
        }
    }

//    public static void clearTable() throws SQLException {
//        Connection con = DBConnection.makeConnection();
//        con.prepareStatement("DELETE FROM ETUDIANT").execute();
//        con.commit();
//        con.close();
//    }

}
