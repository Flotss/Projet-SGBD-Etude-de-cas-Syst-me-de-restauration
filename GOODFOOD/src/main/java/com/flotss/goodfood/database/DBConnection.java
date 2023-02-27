package com.flotss.goodfood.database;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DBConnection {

    private static Connection connection = null;

    public static Connection makeConnection(String user, String password) throws SQLException, ClassNotFoundException, IOException {
        if (connection == null) {
            loadConnection();
        }

        PreparedStatement loginServeur = connection.prepareStatement("SELECT * FROM SERVEUR WHERE EMAIL = ? AND PASSWD = ?");
        loginServeur.setString(1, user);
        loginServeur.setString(2, password);
        ResultSet result = loginServeur.executeQuery();
        if (!result.next()) {
            throw new SQLException("Email ou mot de passe incorrect");
        }

        connection.setAutoCommit(false);
        return connection;
    }


    private static void loadConnection() throws SQLException, IOException, ClassNotFoundException {
        loadDriver();
        Ini ini = new Ini(new File("GOODFOOD/src/main/resources/db_config.ini"));
        String userDB = ini.get("db", "username");
        String passwordDB = ini.get("db", "password");

        connection = DriverManager
                .getConnection("jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb",
                        userDB,
                        passwordDB);
        ini.clear();
    }

    private static void loadDriver() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

}