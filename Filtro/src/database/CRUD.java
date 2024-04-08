package database;

import java.util.List;

//INTERFAZ CRUD PARA IMPLEMENTAR EN LOS MODELOS
public interface CRUD {

    Object insert(Object object);

    boolean update(Object object);

    boolean delete(int id);

    List<Object> findAll();
}

