package controller;

import entity.Client;
import entity.Product;
import entity.Purchase;
import model.ClientModel;
import model.ProductModel;
import model.PurchaseModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class PurchaseController {
    public PurchaseModel instanceModel() {
        return new PurchaseModel();
    }

    public void add() {
        try {
            Date purchaseDate = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the Date of the Purchase (YYYY-MM-DD)"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Quantity of the Purchase"));

            Object[] clientOptions = new ClientModel().findAll().toArray();
            Object[] productOptions = new ProductModel().findAll().toArray();

            if (clientOptions.length > 0) {
                Client selectedClient = (Client) JOptionPane.showInputDialog(
                        null,
                        "Select the Client:\n",
                        "Selecting a Client",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        clientOptions,
                        clientOptions[0]);
                if (selectedClient == null) {
                    JOptionPane.showMessageDialog(null, "No Client selected");
                } else {
                    if (productOptions.length > 0) {
                        Product selectedProduct = (Product) JOptionPane.showInputDialog(
                                null,
                                "Select the Product:\n",
                                "Selecting a Product",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                productOptions,
                                productOptions[0]);
                        if (selectedProduct == null) {
                            JOptionPane.showMessageDialog(null, "No Product selected");
                        } else {
                            System.out.println(instanceModel().insert(new Purchase(purchaseDate, quantity, selectedClient.getId(), selectedProduct.getId())));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No Products registered yet");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No Clients registered yet");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Value entered is not valid");
            System.out.println(e.getMessage());
        }
    }

    public void update() {
        try {
            Object[] purchaseOptions = instanceModel().findAll().toArray();
            if (purchaseOptions.length > 0) {
                Purchase selectedPurchase = (Purchase) JOptionPane.showInputDialog(
                        null,
                        "Select the Purchase you want to update:\n",
                        "Updating a Purchase",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        purchaseOptions,
                        purchaseOptions[0]);
                if (selectedPurchase == null) {
                    JOptionPane.showMessageDialog(null, "No Client selected");
                } else {
                    Date purchaseDate = (Date.valueOf(JOptionPane.showInputDialog(null, "Enter the new Date of the Purchase (YYYY-MM-DD)", selectedPurchase.getPurchaseDate())));
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new Quantity of the Purchase", selectedPurchase.getQuantity()));

                    Object[] clientOptions = new ClientModel().findAll().toArray();
                    Object[] productOptions = new ProductModel().findAll().toArray();

                    if (clientOptions.length > 0) {
                        Client selectedClient = (Client) JOptionPane.showInputDialog(
                                null,
                                "Select the new Client:\n",
                                "Updating a Client",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                clientOptions,
                                clientOptions[0]);
                        if (selectedClient == null) {
                            JOptionPane.showMessageDialog(null, "No Client selected");
                        } else {
                            if (productOptions.length > 0) {
                                Product selectedProduct = (Product) JOptionPane.showInputDialog(
                                        null,
                                        "Select the new Product:\n",
                                        "Updating a Product",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        productOptions,
                                        productOptions[0]);
                                if (selectedProduct == null) {
                                    JOptionPane.showMessageDialog(null, "No Product selected");
                                } else {
                                    selectedPurchase.setQuantity(quantity);
                                    selectedPurchase.setPurchaseDate(purchaseDate);
                                    selectedPurchase.setIdClient(selectedClient.getId());
                                    selectedPurchase.setIdProduct(selectedProduct.getId());
                                    if (instanceModel().update(selectedPurchase)) {
                                        JOptionPane.showMessageDialog(null, "Purchase Updated successfully");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Couldn't update the Purchase");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No Products registered yet");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No Clients registered yet");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "No Purchases registered yet");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Value entered is not valid");
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Purchase selectedOption = (Purchase) JOptionPane.showInputDialog(
                    null,
                    "Select the Purchase that you want to delete:\n",
                    "Deleting a Purchase",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No Purchase selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this Purchase?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption.getId())) {
                        JOptionPane.showMessageDialog(null, "Purchase successfully deleted");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the Purchase");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Purchase was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Purchases registered yet");
        }
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll(instanceModel().findAll()));
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("Purchases List:\n");
        if (objectsList.isEmpty()) {
            list.append("No Purchases registered yet");
        } else {
            for (Object obj : objectsList) {
                Purchase objPlane = (Purchase) obj;
                list.append(objPlane.toString()).append("\n");
            }
        }
        return list;
    }
}
