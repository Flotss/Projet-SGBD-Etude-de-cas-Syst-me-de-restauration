package com.goodfood.app.database;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * The type Db connection.
 */
public class DBConnection {

    private static Connection connection = null;

    /**
     * Make connection connection.
     *
     * @param user     the user
     * @param password the password
     * @return the connection
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException            the io exception
     */
    public static Connection makeConnection(String user, String password) throws SQLException, ClassNotFoundException, IOException {
        if (connection == null) {
            loadConnection();
        }
        System.out.println("user : " + user + " password : " + password);
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


    /**
     * Load connection.
     * @throws SQLException           the sql exception
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    private static void loadConnection() throws SQLException, IOException, ClassNotFoundException {
        loadDriver();
        Ini ini = new Ini(new File("GOODFOOD/src/main/resources/db_config.ini"));
        String userDB = ini.get("db", "username");
        String passwordDB = ini.get("db", "password");
        String urlDB = ini.get("db", "url");

        connection = DriverManager
                .getConnection(urlDB,
                        userDB,
                        passwordDB);
        ini.clear();
    }

    /**
     * Load driver.
     * @throws ClassNotFoundException the class not found exception
     */
    private static void loadDriver() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

}