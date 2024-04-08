package model;

import Utils.DeleteFromTable;
import database.CRUD;
import database.ConfigDB;
import entity.Purchase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class PurchaseModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Purchase objPurchase = (Purchase) object;
        String sql = "INSERT INTO purchases(purchaseDate,quantity,idClient,idProduct) VALUES(?,?,?,?);";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setDate(1, objPurchase.getPurchaseDate());
            objPrepare.setInt(2, objPurchase.getQuantity());
            objPrepare.setInt(3, objPurchase.getIdClient());
            objPrepare.setInt(4, objPurchase.getIdProduct());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objPurchase.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Purchase added successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPurchase;
    }

    @Override
    public boolean update(Object object) {
        Purchase objPurchase = (Purchase) object;
        boolean isUpdated = false;
        String sql = "UPDATE purchases SET purchaseDate = ?, quantity = ?, idClient = ?, idProduct = ? WHERE id = ?;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setDate(1, objPurchase.getPurchaseDate());
            objPrepare.setInt(2, objPurchase.getQuantity());
            objPrepare.setInt(3, objPurchase.getIdClient());
            objPrepare.setInt(4, objPurchase.getIdProduct());
            objPrepare.setInt(5, objPurchase.getId());
            if (objPrepare.executeUpdate() > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        return DeleteFromTable.deleteInt("purchases", "id", id);
    }

    @Override
    public List<Object> findAll() {
        List<Object> purchasesList = new ArrayList<>();
        String sql = "SELECT * FROM purchases ORDER BY id ASC;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            ResultSet objResult = objConnection.prepareStatement(sql).executeQuery();
            while (objResult.next()) {
                purchasesList.add(extractResultSet(objResult));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return purchasesList;
    }

    private Purchase extractResultSet(ResultSet objResult) throws SQLException {
        return new Purchase(objResult.getInt("id"), objResult.getDate("purchaseDate"), objResult.getInt("quantity"), objResult.getInt("idClient"), objResult.getInt("idProduct"));
    }
}
