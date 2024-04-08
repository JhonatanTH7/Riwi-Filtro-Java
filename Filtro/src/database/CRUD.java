package database;

import java.util.List;

public interface CRUD {

    Object insert(Object object);

    boolean update(Object object);

    boolean delete(int id);

    List<Object> findAll();
}

