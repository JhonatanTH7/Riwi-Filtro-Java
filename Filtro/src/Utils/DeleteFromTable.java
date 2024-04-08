package Utils;

import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteFromTable {
//    METODO PARA BORRAR UN REGISTRO DE UNA TABLA MEDIANTE UN ID Y NOMBRE DE TABLA RECIBIDO
    public static boolean deleteInt(String tableName, String columnName, int valueCompare) {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnName + " = " + valueCompare + ";";
        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();
        try {
            if (objConnection.prepareStatement(sql).executeUpdate() > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
//        DEVUELVE UN BOOLEANO CONFIRMANDO SI SE BORRO O NO
        return isDeleted;
    }
}
