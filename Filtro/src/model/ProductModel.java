package model;

import Utils.DeleteFromTable;
import database.CRUD;
import database.ConfigDB;
import entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Product objProduct = (Product) object;
        String sql = "INSERT INTO products(name,stock,price,idStore) VALUES(?,?,?,?);";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objProduct.getName());
            objPrepare.setInt(2, objProduct.getStock());
            objPrepare.setDouble(3, objProduct.getPrice());
            objPrepare.setInt(4, objProduct.getIdStore());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objProduct.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Product added successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objProduct;
    }

    @Override
    public boolean update(Object object) {
        Product objProduct = (Product) object;
        boolean isUpdated = false;
        String sql = "UPDATE products SET name = ?, stock = ?, price = ?, idStore = ? WHERE id = ?;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, objProduct.getName());
            objPrepare.setInt(2, objProduct.getStock());
            objPrepare.setDouble(3, objProduct.getPrice());
            objPrepare.setInt(4, objProduct.getIdStore());
            objPrepare.setInt(5, objProduct.getId());
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
        return DeleteFromTable.deleteInt("products", "id", id);
    }

    @Override
    public List<Object> findAll() {
        List<Object> productsList = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id ASC;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            ResultSet objResult = objConnection.prepareStatement(sql).executeQuery();
            while (objResult.next()) {
                productsList.add(extractResultSet(objResult));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return productsList;
    }

    public List<Object> findByName(String nameSearched) {
        String sql = "SELECT * FROM products WHERE name LIKE ?;";
        List<Object> productsList = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, "%" + nameSearched + "%");
            ResultSet objResult = objPreparedStatement.executeQuery();
            while (objResult.next()) {
                productsList.add(extractResultSet(objResult));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return productsList;
    }

    private Product extractResultSet(ResultSet objResult) throws SQLException {
        return new Product(objResult.getInt("id"), objResult.getString("name"), objResult.getInt("stock"), objResult.getDouble("price"), objResult.getInt("idStore"));
    }
}
