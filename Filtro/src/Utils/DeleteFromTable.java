package Utils;

import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteFromTable {
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
        return isDeleted;
    }
}
