package com.flotss.goodfood.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection makeConnection(String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager
                .getConnection("jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb",
                                   user,
                                   password);
        connection.setAutoCommit(false);
        return connection;
    }

}