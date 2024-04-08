package controller;

import entity.Product;
import model.ProductModel;

import javax.swing.*;
import java.util.List;

public class ProductController {
    //    METODO QUE RETORNA INSTANCIA DEL MODELO DE LA ENTIDAD RESPECTIVA
    public ProductModel instanceModel() {
        return new ProductModel();
    }

    //    METODO QUE RECOLECTA LOS ATRIBUTOS DE LA ENTIDAD PARA LUEGO ENVIARLA AL MODELO Y AGREGAR EL REGISTRO A LA BASE DE DATOS
    public void add() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter the name of the Product");
            int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Stock of the Product"));
            double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Price of the Product"));
            int idStore = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the ID of the store"));
            System.out.println(instanceModel().insert(new Product(name, stock, price, idStore)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Value entered is not valid");
            System.out.println(e.getMessage());
        }
    }

    //RECOLECTA NUEVAMENTE LOS ATRIBUTOS DE LA ENTIDAD EN CASO DE QUE EL USUARIO DESEE ACTUALIZARLO, POSTERIORMENTE LO ENVIA AL MODELO PARA REALIZAR LA ACTUALIZACIÓN EN LA BASE DE DATOS
    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Product selectedOption = (Product) JOptionPane.showInputDialog(
                    null,
                    "Select the Product that you want to update:\n",
                    "Updating a Product",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No Product selected");
            } else {
                try {
                    selectedOption.setName(JOptionPane.showInputDialog(null, "Enter the new name of the Product", selectedOption.getName()));
                    selectedOption.setStock(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new Stock of the Product", selectedOption.getStock())));
                    selectedOption.setPrice(Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new Price of the Product", selectedOption.getPrice())));
                    selectedOption.setIdStore(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new ID of the store where the Product is", selectedOption.getIdStore())));
                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Product Updated successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't update the Product");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Value entered is not valid");
                    System.out.println(e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Products registered yet");
        }
    }

    //    LE PERMITE AL USUARIO SELECCIONAR ENTRE LOS REGISTROS EXISTENTES PARA ELIMINAR EL DESEADO, AL ESCOGER SE ENVIA EL ID AL MODELO QUE REALIZARA EL BORRADO EN LA BASE DE DATOS
    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Product selectedOption = (Product) JOptionPane.showInputDialog(
                    null,
                    "Select the Product that you want to delete:\n",
                    "Deleting a Product",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No Product selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this Product?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption.getId())) {
                        JOptionPane.showMessageDialog(null, "Product successfully deleted");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the Product");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Product was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Products registered yet");
        }
    }

    //    ENVIA LA LISTA GENERAL AL METODO GET-ALL QUE RECIBE LISTA COMO PARAMETRO Y LA CONCATENA MEDIANTE UN STRING BUILDER
    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll(instanceModel().findAll()));
    }

    //    RECIBE UNA LISTA COMO PARAMETRO Y LA CONCATENA EN UN STRING BUILDER PARA LUEGO RETORNARLA
    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("Products List:\n");
        if (objectsList.isEmpty()) {
            list.append("No Products registered yet");
        } else {
            for (Object obj : objectsList) {
                Product objPlane = (Product) obj;
                list.append(objPlane.toString()).append("\n");
            }
        }
        return list;
    }

//    METODO QUE SOLICITA AL USUARIO UN NOMBRE DE PRODUCTO PARA LUEGO ENVIARLO A EL MODELO Y FILTRAR POR ESTE STRING
    public void getByName() {
        String nameSearched = JOptionPane.showInputDialog(null, "Enter the name of the product you want to search");
        StringBuilder list = new StringBuilder("Filtered by Name: " + nameSearched + "\n");
        list.append(getAll(instanceModel().findByName(nameSearched)));
        JOptionPane.showMessageDialog(null, list);
    }
}
