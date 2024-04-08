import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Menus menus = new Menus();
        String option;
        do {
//          REDIRECCIONAMIENTO A MENUS
            option = JOptionPane.showInputDialog(null, """

                           MAIN MENU

                    1. Manage Clients
                    2. Manage Products
                    3. Manage Purchases
                    4. Exit

                    Choose an option:

                    """);
            switch (option) {
                case "1":
                    menus.clientsMenu();
                    break;
                case "2":
                    menus.productsMenu();
                    break;
                case "3":
                    menus.purchasesMenu();
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "See you next time!!!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
            }
        } while (!option.equals("4"));
    }
}