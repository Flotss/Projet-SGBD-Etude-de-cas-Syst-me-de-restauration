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

    private String gradePersonne;

    public Model() {
        this.dbConnection = null;
    }

    public void setDbConnection(String user, String password) throws SQLException, ClassNotFoundException, IOException {
        this.dbConnection = DBConnection.makeConnection(user, password);

        // Réccupération du grade de la personne connectée
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT GRADE FROM SERVEUR WHERE EMAIL = ?");
        preparedStatement.setString(1, user);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        gradePersonne = resultSet.getString(1);
        System.out.println("Type de personne : " + gradePersonne);
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
        if (preparedStatement.getResultSet().next()) {
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

    public List<Integer> getTableToList() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT NUMTAB FROM TABL");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        List<Integer> tables = new ArrayList<>();
        while (resultSet.next()) {
            tables.add(resultSet.getInt(1));
        }
        return tables;
    }

    public List<Integer> getReservationToList() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT NUMRES FROM RESERVATION ORDER BY DATRES");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        List<Integer> reservations = new ArrayList<>();
        while (resultSet.next()) {
            reservations.add(resultSet.getInt(1));
        }
        return reservations;
    }


    public ResultSet getPlats() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT LIBELLE, TYPE, PRIXUNIT FROM PLAT ORDER BY CASE WHEN TYPE = 'Entree' THEN 1 WHEN TYPE = 'Plat' THEN 2 WHEN TYPE = 'Viande' THEN 3 WHEN TYPE = 'Poisson' THEN 4 WHEN TYPE = 'Fromage' THEN 5 WHEN TYPE = 'Dessert' THEN 6 END");
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }

    public List<String> getPlatsToList() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT LIBELLE FROM PLAT ORDER BY CASE WHEN TYPE = 'Entree' THEN 1 WHEN TYPE = 'Plat' THEN 2 WHEN TYPE = 'Viande' THEN 3 WHEN TYPE = 'Poisson' THEN 4 WHEN TYPE = 'Fromage' THEN 5 WHEN TYPE = 'Dessert' THEN 6 END");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        List<String> plats = new ArrayList<>();
        while (resultSet.next()) {
            plats.add(resultSet.getString(1));
        }
        return plats;
    }

    public void commanderPlat(Integer numres, String platLibelle, int quantite) throws SQLException {
        // On cherche le numéro du plat
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT NUMPLAT FROM PLAT WHERE LIBELLE = ?");
        preparedStatement.setString(1, platLibelle);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        int numPlat = resultSet.getInt(1);

        // Verification que le plat est disponible
        preparedStatement = dbConnection.prepareStatement("SELECT QTESERVIE FROM PLAT WHERE NUMPLAT = ?");
        preparedStatement.setInt(1, numPlat);
        preparedStatement.execute();
        resultSet = preparedStatement.getResultSet();
        resultSet.next();
        int qteServie = resultSet.getInt(1);
        if (qteServie < quantite) {
            throw new SQLException("Le plat n'est pas disponible");
        }

        // Insertion de la commande
        preparedStatement = dbConnection.prepareStatement("INSERT INTO COMMANDE (NUMRES, NUMPLAT, QUANTITE) VALUES (?,?,?)");
        preparedStatement.setInt(1, numres);
        preparedStatement.setInt(2, numPlat);
        preparedStatement.setInt(3, quantite);
        preparedStatement.executeUpdate();

        // Mise à jour de la quantité servie
        preparedStatement = dbConnection.prepareStatement("UPDATE PLAT SET QTESERVIE = QTESERVIE - ? WHERE NUMPLAT = ?");
        preparedStatement.setInt(1, quantite);
        preparedStatement.setInt(2, numPlat);
        preparedStatement.executeUpdate();

        dbConnection.commit();
    }

    public boolean isGestionnaire() {
        return gradePersonne.equalsIgnoreCase("Gestionnaire");
    }

    public boolean updateServeur(int numServ, String email, String nom, String grade) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("UPDATE SERVEUR SET EMAIL = ?, NOMSERV = ?, GRADE = ? WHERE NUMSERV = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, grade);
            preparedStatement.setInt(4, numServ);
            preparedStatement.executeUpdate();
            dbConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void ajouterServeur(String email, String password, String nomserv, String grade) throws SQLException {
        // On cherche le numéro du serveur max
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT MAX(NUMSERV) FROM SERVEUR");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        int numServ = resultSet.getInt(1) + 1;

        // Verification que le serveur n'existe pas déjà
        preparedStatement = dbConnection.prepareStatement("SELECT NUMSERV FROM SERVEUR WHERE EMAIL = ?");
        preparedStatement.setString(1, email);
        preparedStatement.execute();
        resultSet = preparedStatement.getResultSet();
        if (resultSet.next()) {
            throw new SQLException("Le serveur existe déjà");
        }

        // Ajout du serveur
        preparedStatement = dbConnection.prepareStatement("INSERT INTO SERVEUR (NUMSERV, EMAIL, PASSWD, NOMSERV, GRADE) VALUES (?,?,?,?,?)");
        preparedStatement.setInt   (1, numServ);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, nomserv);
        preparedStatement.setString(5, grade);
        preparedStatement.executeUpdate();
        dbConnection.commit();

    }
}
