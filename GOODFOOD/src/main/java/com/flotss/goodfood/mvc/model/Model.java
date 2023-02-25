package com.flotss.goodfood.mvc.model;

import com.flotss.goodfood.database.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private Connection dbConnection;

    private String emailServeur;

    public Model(){
        this.dbConnection = null;
    }

    public void setDbConnection(String user, String password) throws SQLException, ClassNotFoundException, IOException {
        this.dbConnection = DBConnection.makeConnection(user, password);
        this.emailServeur = user;
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void disconnectDB() {
        this.dbConnection = null;
    }

    public void reserveTable(int numTable, String dateTime, int nbrPersonne) throws SQLException {
        // Verification que la table est libre à cette date
        System.out.println("SELECT * FROM RESERVATION WHERE NUMTAB = " + numTable + " AND DATRES = to_date( '" + dateTime + "' ,'yyyy/mm/dd hh24:mi')");
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM RESERVATION WHERE NUMTAB = ? AND DATRES = to_date( ? ,'yyyy-mm-dd hh24:mi')");
        preparedStatement.setInt(1, numTable);
        preparedStatement.setString(2, dateTime);
        preparedStatement.execute();
        if(preparedStatement.getResultSet().next()){
            // La table est déjà réservée
            throw new SQLException("La table est déjà réservée");
        }

        // On cherche le numéro de la réservation max
        preparedStatement = dbConnection.prepareStatement("SELECT MAX(NUMRES) FROM RESERVATION");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        int numRes = resultSet.getInt(1) + 1;

        // Insertion de la réservation
        // TODO : METTRE A JOUR LA STATE SUIVANTE AVEC LES BONNES COLONNES
        preparedStatement = dbConnection.prepareStatement("INSERT INTO RESERVATION (numres, numtab, DATRES, NBPERS) VALUES (?,?, to_date( ? ,'yyyy-mm-dd hh24:mi'),?)");
        preparedStatement.setInt(1, numRes);
        preparedStatement.setInt(2, numTable);
        preparedStatement.setString(3, dateTime);
        preparedStatement.setInt(4, nbrPersonne);
        preparedStatement.executeUpdate();

        dbConnection.commit();
    }

    public List<Integer> getTable() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT NUMTAB FROM TABL");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        List<Integer> tables = new ArrayList<>();
        while (resultSet.next()){
            tables.add(resultSet.getInt("NUMTAB"));
        }
        return tables;
    }
}
