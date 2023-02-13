package com.flotss.goodfood.mvc.model;

import com.flotss.goodfood.database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Model {

    private Connection dbConnection;

    public Model(String user, String password) throws SQLException {
        this.dbConnection = DBConnection.makeConnection(user, password);
    }

    public Model(){
        this.dbConnection = null;
    }

    public void setDbConnection(String user, String password) throws SQLException {
        this.dbConnection = DBConnection.makeConnection(user, password);
    }

    public Connection getDbConnection() {
        return dbConnection;
    }
}
