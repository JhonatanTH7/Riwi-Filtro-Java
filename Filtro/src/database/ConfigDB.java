package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection objConnection = null;
//APERTURA DE CONEXION A LA BASE DE DATOS
    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://bkf14fqjqii1ib1i9cgp-mysql.services.clever-cloud.com:3306/bkf14fqjqii1ib1i9cgp";
            String user = "ufptlorvcqnqwm1n";
            String password = "IYA5IhBGqzPlwpltLtKG";
            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Properly connected to DB");
        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver not found");
        } catch (SQLException e) {
            System.out.println("Couldn't establish a connection with DB");
        }
        return objConnection;
    }
//CERRAR LA CONEXION A LA BASE DE DATOS
    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("DB connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error >> " + e.getMessage());
        }
    }

}
