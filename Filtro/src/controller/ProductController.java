package controller;

import entity.Product;
import model.ProductModel;

import javax.swing.*;
import java.util.List;

public class ProductController {
    public ProductModel instanceModel() {
        return new ProductModel();
    }

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

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll(instanceModel().findAll()));
    }

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
}
