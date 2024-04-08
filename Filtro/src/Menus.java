import controller.ClientController;
import controller.ProductController;
import controller.PurchaseController;

import javax.swing.*;

public class Menus {
    public void clientsMenu() {
        ClientController clientController = new ClientController();
        String option2;
//        REDIRECCIONAMIENTO A CONTROLLER DE CLIENTE
        do {
            option2 = JOptionPane.showInputDialog(null, """

                        CLIENTS MENU

                    1. Show all Clients
                    2. Add Client
                    3. Update Client
                    4. Delete Client
                    5. Filter by Name
                    6. Exit

                    Choose an option:

                    """);
            switch (option2) {
                case "1":
//                    LISTAR
                    clientController.getAll();
                    break;
                case "2":
//                    AGREGAR
                    clientController.add();
                    break;
                case "3":
//                    ACTUALIZAR
                    clientController.update();
                    break;
                case "4":
//                    BORRAR
                    clientController.delete();
                    break;
                case "5":
//                    BUSCAR POR NOMBRE
                    clientController.getByName();
                    break;
                case "6":
//                    MUESTRA UN MENSAJE ANTES DE VOLVER AL MENU PRINCIPAL
                    JOptionPane.showMessageDialog(null, "Going back to main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option2.equals("6"));
    }

    public void productsMenu() {
        ProductController productController = new ProductController();
        String option2;
        //        REDIRECCIONAMIENTO A CONTROLLER DE PRODUCTO
        do {
            option2 = JOptionPane.showInputDialog(null, """

                        PRODUCTS MENU

                    1. Show all Products
                    2. Add Product
                    3. Update Product
                    4. Delete Product
                    5. Filter by Name
                    6. Filter by Store
                    7. Exit

                    Choose an option:

                    """);
            switch (option2) {
                case "1":
                    //                    LISTAR
                    productController.getAll();
                    break;
                case "2":
                    //                    AGREGAR
                    productController.add();
                    break;
                case "3":
                    //                    ACTUALIZAR
                    productController.update();
                    break;
                case "4":
                    //                    BORRAR
                    productController.delete();
                    break;
                case "5":
                    //                    BUSCAR POR NOMBRE
                    productController.getByName();
                    break;
                case "6":
//                    BUSCAR POR TIENDA
                    productController.getByStore();
                    break;
                case "7":
//                    MUESTRA UN MENSAJE ANTES DE VOLVER AL MENU PRINCIPAL
                    JOptionPane.showMessageDialog(null, "Going back to main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option2.equals("7"));
    }

    public void purchasesMenu() {
        PurchaseController purchaseController = new PurchaseController();
        String option2;
        //        REDIRECCIONAMIENTO A CONTROLLER DE COMPRA
        do {
            option2 = JOptionPane.showInputDialog(null, """

                        PURCHASES MENU

                    1. Show all Purchases
                    2. Add Purchase
                    3. Update Purchase
                    4. Delete Purchase
                    5. Filter by Product
                    6. Exit

                    Choose an option:

                    """);
            switch (option2) {
                case "1":
                    //                    LISTAR
                    purchaseController.getAll();
                    break;
                case "2":
                    //                    AGREGAR
                    purchaseController.add();
                    break;
                case "3":
                    //                    ACTUALIZAR
                    purchaseController.update();
                    break;
                case "4":
                    //                    BORRAR
                    purchaseController.delete();
                    break;
                case "5":
//                    BUSCAR POR PRODUCTO
                    purchaseController.getByProduct();
                    break;
                case "6":
//                    MUESTRA UN MENSAJE ANTES DE VOLVER AL MENU PRINCIPAL
                    JOptionPane.showMessageDialog(null, "Going back to main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option2.equals("6"));
    }
}
