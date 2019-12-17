package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBController {

    private static Statement statement;



    public static void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:clinique.db");
            statement = connection.createStatement();
        } catch (Exception e) {
        }
    }

    public static String getUsername() {
        try {

            ResultSet resultSet = statement.executeQuery("select username from user");
            return resultSet.getString("username");
        } catch (Exception e) {
        }
        return null;
    }

    public static String getPassword() {
        try {

            ResultSet resultSet = statement.executeQuery("select password from user");
            return resultSet.getString("password");
        } catch (Exception e) {
        }
        return null;
    }

    public static ResultSet getPatients() {
        try {
            return statement.executeQuery("select Nom,Prenom, DateNaissance, Faculte from patient order by id DESC");
        } catch (Exception e) {
        }
        return null;
    }
    public static ResultSet getMedicines() {
        try {
            return statement.executeQuery("select Medicament, Dose, Forme from medicament order by id DESC");
        } catch (Exception e) {
        }
        return null;
    }

    public static void insertMed(String nom, String dose, String form) {

        try {
            statement.executeQuery("INSERT INTO medicament(id, Medicament,Dose,Forme) " +
                    "VALUES (null, '" + nom + "', '" + dose + "','" + form + "');");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertPatient(String nom, String prenom, String datn, String spt) {

        try {
            statement.executeQuery("INSERT INTO patient(id, Nom, Prenom, DateNaissance, Faculte) " +
                    "VALUES (null, '" + nom + "', '" + prenom + "','" + datn + "','" + spt + "');");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}




