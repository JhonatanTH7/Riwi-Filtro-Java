import controller.ClientController;
import controller.ProductController;
import controller.PurchaseController;

import javax.swing.*;

public class Menus {
    public void clientsMenu() {
        ClientController clientController = new ClientController();
        String option2;
        do {
            option2 = JOptionPane.showInputDialog(null, """

                        CLIENTS MENU

                    1. Show all Clients
                    2. Add Client
                    3. Update Client
                    4. Delete Client
                    5. Exit

                    Choose an option:

                    """);
            switch (option2) {
                case "1":
                    clientController.getAll();
                    break;
                case "2":
                    clientController.add();
                    break;
                case "3":
                    clientController.update();
                    break;
                case "4":
                    clientController.delete();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Going back to main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option2.equals("5"));
    }

    public void productsMenu() {
        ProductController productController = new ProductController();
        String option2;
        do {
            option2 = JOptionPane.showInputDialog(null, """

                        PRODUCTS MENU

                    1. Show all Products
                    2. Add Product
                    3. Update Product
                    4. Delete Product
                    5. Exit

                    Choose an option:

                    """);
            switch (option2) {
                case "1":
                    productController.getAll();
                    break;
                case "2":
                    productController.add();
                    break;
                case "3":
                    productController.update();
                    break;
                case "4":
                    productController.delete();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Going back to main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option2.equals("5"));
    }

    public void purchasesMenu() {
        PurchaseController purchaseController = new PurchaseController();
        String option2;
        do {
            option2 = JOptionPane.showInputDialog(null, """

                        PURCHASES MENU

                    1. Show all Purchases
                    2. Add Purchase
                    3. Update Purchase
                    4. Delete Purchase
                    5. Exit

                    Choose an option:

                    """);
            switch (option2) {
                case "1":
                    purchaseController.getAll();
                    break;
                case "2":
                    purchaseController.add();
                    break;
                case "3":
                    purchaseController.update();
                    break;
                case "4":
                    purchaseController.delete();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Going back to main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option2.equals("5"));
    }

}
