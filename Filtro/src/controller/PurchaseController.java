package controller;

import entity.Client;
import entity.Product;
import entity.Purchase;
import entity.Store;
import model.ClientModel;
import model.ProductModel;
import model.PurchaseModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class PurchaseController {
    //    METODO QUE RETORNA INSTANCIA DEL MODELO DE LA ENTIDAD RESPECTIVA
    public PurchaseModel instanceModel() {
        return new PurchaseModel();
    }

    //    METODO QUE RECOLECTA LOS ATRIBUTOS DE LA ENTIDAD PARA LUEGO ENVIARLA AL MODELO Y AGREGAR EL REGISTRO A LA BASE DE DATOS, SE REALIZAN VALIDACIONES Y SE IMPRIME UNA TARJETA TIPO FACTURA CON ALGUNOS DETALLES DE LA TIENDA, CLIENTE Y PRODUCTO
    public void add() {
        try {
            Date purchaseDate = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the Date of the Purchase (YYYY-MM-DD)"));

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
                                "Select the Product to Purchase:\n",
                                "Selecting a Product",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                productOptions,
                                productOptions[0]);
                        if (selectedProduct == null) {
                            JOptionPane.showMessageDialog(null, "No Product selected");
                        } else {
                            int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Quantity of the Purchase >> Actual Stock: " + selectedProduct.getStock()));
                            if (selectedProduct.getStock() >= quantity) {
                                System.out.println(instanceModel().insert(new Purchase(purchaseDate, quantity, selectedClient.getId(), selectedProduct.getId())));
                                if (new ProductModel().updateStock(selectedProduct.getId(), (selectedProduct.getStock() - quantity))) {
                                    JOptionPane.showMessageDialog(null, "Stock updated successfully");
                                    double normalPrice = selectedProduct.getPrice() * quantity;
                                    Store objStore = new ProductModel().findStore(selectedProduct.getId());
                                    StringBuilder bill = new StringBuilder("\n\n====================================== Bill ======================================\n\n");
                                    bill.append("PRODUCT ==>     Name: ").append(selectedProduct.getName()).append("     Price: ").append(selectedProduct.getPrice()).append("     Quantity: ").append(quantity).append("\n");
                                    bill.append("STORE ==>     Name: ").append(objStore.getName()).append("     Location: ").append(objStore.getLocation()).append("\n");
                                    bill.append("CLIENT ==>     Name: ").append(selectedClient.getName()).append("     LastName: ").append(selectedClient.getLastName()).append("     Email: ").append(selectedClient.getEmail()).append("\n");
                                    bill.append("\n\n\n\n").append("PRICE ==> ").append(normalPrice).append("          IVA ==> ").append(0.19 * normalPrice).append("\n").append("TOTAL PRICE: ").append(1.19 * normalPrice);
                                    JOptionPane.showMessageDialog(null, bill);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Couldn't update the Stock");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "There is not enough Stock of the Product you want to Purchase >> Actual Stock: " + selectedProduct.getStock());
                            }
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

    //RECOLECTA NUEVAMENTE LOS ATRIBUTOS DE LA ENTIDAD EN CASO DE QUE EL USUARIO DESEE ACTUALIZARLO, POSTERIORMENTE LO ENVIA AL MODELO PARA REALIZAR LA ACTUALIZACIÓN EN LA BASE DE DATOS
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

//    LE PERMITE AL USUARIO SELECCIONAR ENTRE LOS REGISTROS EXISTENTES PARA ELIMINAR EL DESEADO, AL ESCOGER SE ENVIA EL ID AL MODELO QUE REALIZARA EL BORRADO EN LA BASE DE DATOS
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

//    ENVIA LA LISTA GENERAL AL METODO GET-ALL QUE RECIBE LISTA COMO PARAMETRO Y LA CONCATENA MEDIANTE UN STRING BUILDER
    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll(instanceModel().findAll()));
    }

//    RECIBE UNA LISTA COMO PARAMETRO Y LA CONCATENA EN UN STRING BUILDER PARA LUEGO RETORNARLA
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

//    DA AL USUARIO LA OPORTUNIDAD DE ESCOGER ENTRE LOS PRODUCTOS EXISTENTES PARA LUEGO MEDIANTE EL SELECCIONADO FILTRAR LA LISTA DE COMPRAS, SE ENVIA EL ID AL MODELO PARA REALIZAR LA RESPECTIVA SENTENCIA SQL
    public void getByProduct() {
        Object[] flightOptions = new ProductModel().findAll().toArray();
        if (flightOptions.length > 0) {
            Product selectedProduct = (Product) JOptionPane.showInputDialog(
                    null,
                    "Select the Product to filter by:\n",
                    "Selecting the Product",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    flightOptions,
                    flightOptions[0]);
            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(null, "No Flight selected");
            } else {
                StringBuilder list = new StringBuilder("Filtered by Product: " + selectedProduct.getName() + "\n");
                list.append(getAll(instanceModel().findByProduct(selectedProduct.getId())));
                JOptionPane.showMessageDialog(null, list);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Flights found");
        }
    }
}
