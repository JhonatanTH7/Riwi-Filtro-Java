package model;

import Utils.DeleteFromTable;
import database.CRUD;
import database.ConfigDB;
import entity.Client;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //        METODO CON CONSULTA SQL PARA AGREGAR UN REGISTRO MEDIANTE UN OBJETO RECIBIDO COMO PARAMETRO
        Client objClient = (Client) object;
        String sql = "INSERT INTO clients(name,lastName,email) VALUES(?,?,?);";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objClient.getName());
            objPrepare.setString(2, objClient.getLastName());
            objPrepare.setString(3, objClient.getEmail());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objClient.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Client added successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        //        DEVUELVE EL OBJETO GENERADO PARA VERIFICAR SI SE EJECUTO EXITOSAMENTE
        return objClient;
    }

    @Override
    public boolean update(Object object) {
        //        METODO CON CONSULTA SQL PARA ACTUALIZAR UN REGISTRO MEDIANTE UN OBJETO RECIBIDO COMO PARAMETRO
        Client objClient = (Client) object;
        boolean isUpdated = false;
        String sql = "UPDATE clients SET name = ?, lastName = ?, email = ? WHERE id = ?;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, objClient.getName());
            objPrepare.setString(2, objClient.getLastName());
            objPrepare.setString(3, objClient.getEmail());
            objPrepare.setInt(4, objClient.getId());
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
        return DeleteFromTable.deleteInt("clients", "id", id);
    }

    @Override
    public List<Object> findAll() {
        //        METODO QUE LISTA TODOS LOS REGISTROS EXISTENTES EN LA TABLA PERTENECIENTE A LA ENTIDAD
        List<Object> clientsList = new ArrayList<>();
        String sql = "SELECT * FROM clients ORDER BY id ASC;";
        Connection objConnection = ConfigDB.openConnection();
        try {
            ResultSet objResult = objConnection.prepareStatement(sql).executeQuery();
            while (objResult.next()) {
                clientsList.add(extractResultSet(objResult));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        //        DESPUÉS DE ALMACENAR LOS OBJETOS EN UNA LISTA LA RETORNA HACIA EL CONTROLLER
        return clientsList;
    }

    public List<Object> findByName(String nameSearched) {
        //        METODO PARA FILTRAR LOS CLIENTES POR NOMBRE
        String sql = "SELECT * FROM clients WHERE name LIKE ?;";
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

    private Client extractResultSet(ResultSet objResult) throws SQLException {
        //        RETORNA LA INSTANCIA DE LA ENTIDAD HABIENDO EXTRAÍDO LOS RESULTADOS DEL RESULTSET
        return new Client(objResult.getInt("id"), objResult.getString("name"), objResult.getString("lastName"), objResult.getString("email"));
    }
}
