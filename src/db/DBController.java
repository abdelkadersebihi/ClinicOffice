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
            return statement.executeQuery("select * from patient order by id_pat DESC");
        } catch (Exception e) {
        }
        return null;
    }
    public static ResultSet getMedicines() {
        try {
            return statement.executeQuery("select * from medicament order by id_med DESC");
        } catch (Exception e) {
        }
        return null;
    }

    public static ResultSet getPrescriptions() {
        try {
            return statement.executeQuery("select id_ord,nom_pat,prenom_pat,crdat_ord from patient p,ordonnance o where o.id_pat=p.id_pat");
        } catch (Exception e) {
        }
        return null;

    }

    public static void insertMed(String nom, String dose, String form) {

        try {
            statement.executeQuery("INSERT INTO medicament(id_med, nom_med,dose_med,forme_med) " +
                    "VALUES (null, '" + nom + "', '" + dose + "','" + form + "');");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertPatient(String nom, String prenom, String datn, String spt) {

        try {
            //statement.executeQuery("INSERT INTO patient(id_pat, nom_pat,prenom_pat,datn_pat,spt_pat)" +
            //       " VALUES (null,"+nom+","+prenom+","+datn+","+spt+ ");");

            statement.executeQuery("INSERT INTO patient(id_pat, nom_pat,prenom_pat,datn_pat,spt_pat) " +
                    "VALUES (null, '" + nom + "', '" + prenom + "','" + datn + "','" + spt + "');");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}




