package model;

import Utils.DeleteFromTable;
import database.CRUD;
import database.ConfigDB;
import entity.Product;
import entity.Store;

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
        //        METODO CON CONSULTA SQL PARA AGREGAR UN REGISTRO MEDIANTE UN OBJETO RECIBIDO COMO PARAMETRO
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
        //        DEVUELVE EL OBJETO GENERADO PARA VERIFICAR SI SE EJECUTO EXITOSAMENTE
        return objProduct;
    }

    @Override
    public boolean update(Object object) {
        //        METODO CON CONSULTA SQL PARA ACTUALIZAR UN REGISTRO MEDIANTE UN OBJETO RECIBIDO COMO PARAMETRO
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
        //        DEVUELVE BOOLEANO PARA VERIFICAR SI SE EJECUTO EXITOSAMENTE
        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        //        LLAMADO DE METODO DELETEFROMTABLE ENVIANDO COMO PARAMETROS NOMBRE DE TABLA NOMBRE DE COLUMNA O CAMPO Y EL VALOR A COMPARAR PARA BORRAR
//        RETORNA BOOLEANO PARA VERIFICAR SI SE EJECUTO EXITOSAMENTE
        return DeleteFromTable.deleteInt("products", "id", id);
    }

    @Override
    public List<Object> findAll() {
        //        METODO QUE LISTA TODOS LOS REGISTROS EXISTENTES EN LA TABLA PERTENECIENTE A LA ENTIDAD
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
        //        DESPUÉS DE ALMACENAR LOS OBJETOS EN UNA LISTA LA RETORNA HACIA EL CONTROLLER
        return productsList;
    }

    public List<Object> findByName(String nameSearched) {
        //        METODO PARA FILTRAR LOS PRODUCTOS POR NOMBRE
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
        //        DESPUÉS DE ALMACENAR LOS OBJETOS EN UNA LISTA LA RETORNA HACIA EL CONTROLLER
        return productsList;
    }

    public Store findStore(int idProduct) {
//        INNER JOIN UTILIZADO PARA ACCEDER A LOS REGISTROS DE TIENDA
        Store objStore = null;
        String sql = "SELECT  products.id AS idProduct,stores.id AS idStore, stores.name AS storeName, stores.location AS storeLocation FROM products INNER JOIN stores ON products.idStore = stores.id WHERE products.id = ?;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, idProduct);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objStore = new Store();
                objStore.setId(objResult.getInt("idStore"));
                objStore.setName(objResult.getString("storeName"));
                objStore.setLocation(objResult.getString("storeLocation"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
//        DESPUÉS DE EXTRAER LOS VALORES A UN OBJETO STORE LO RETORNA
        return objStore;
    }

    public List<Object> findAllStores() {
        //        METODO QUE LISTA TODOS LOS REGISTROS EXISTENTES EN LA TABLA PERTENECIENTE A LA ENTIDAD
        List<Object> storesList = new ArrayList<>();
        String sql = "SELECT * FROM stores ORDER BY id ASC;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            ResultSet objResult = objConnection.prepareStatement(sql).executeQuery();
            while (objResult.next()) {
                storesList.add(extractResultSetStore(objResult));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        //        DESPUÉS DE ALMACENAR LOS OBJETOS EN UNA LISTA LA RETORNA HACIA EL CONTROLLER
        return storesList;
    }

    public boolean updateStock(int idProduct, int newStock) {
//        ACTUALIZA EL STOCK DEL PRODUCTO
        boolean isUpdated = false;
        String sql = "UPDATE products SET stock = ? WHERE id = ?;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, newStock);
            objPrepare.setInt(2, idProduct);
            if (objPrepare.executeUpdate() > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        //        DEVUELVE BOOLEANO PARA VERIFICAR SI SE EJECUTO EXITOSAMENTE
        return isUpdated;
    }

    private Product extractResultSet(ResultSet objResult) throws SQLException {
        //        RETORNA LA INSTANCIA DE LA ENTIDAD HABIENDO EXTRAÍDO LOS RESULTADOS DEL RESULTSET
        return new Product(objResult.getInt("id"), objResult.getString("name"), objResult.getInt("stock"), objResult.getDouble("price"), objResult.getInt("idStore"));
    }

    private Store extractResultSetStore(ResultSet objResult) throws SQLException {
        return new Store(objResult.getInt("id"), objResult.getString("name"), objResult.getString("location"));
    }
}
