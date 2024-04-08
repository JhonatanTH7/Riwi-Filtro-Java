package controller;

import entity.Client;
import model.ClientModel;

import javax.swing.*;
import java.util.List;

public class ClientController {

    public ClientModel instanceModel() {
        return new ClientModel();
    }

    public void add() {
        String name = JOptionPane.showInputDialog(null, "Enter the name of the Client");
        String lastName = JOptionPane.showInputDialog(null, "Enter the lastname of the Client");
        String email = JOptionPane.showInputDialog(null, "Enter the email of the Client");
        System.out.println(instanceModel().insert(new Client(name, lastName, email)));
    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Client selectedOption = (Client) JOptionPane.showInputDialog(
                    null,
                    "Select the Client that you want to update:\n",
                    "Updating a Client",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No Client selected");
            } else {
                selectedOption.setName(JOptionPane.showInputDialog(null, "Enter the new name of the Client", selectedOption.getName()));
                selectedOption.setLastName(JOptionPane.showInputDialog(null, "Enter the new lastname of the Client", selectedOption.getLastName()));
                selectedOption.setEmail(JOptionPane.showInputDialog(null, "Enter the new email of the Client", selectedOption.getEmail()));
                if (instanceModel().update(selectedOption)) {
                    JOptionPane.showMessageDialog(null, "Client Updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Couldn't update the Client");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Clients registered yet");
        }
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Client selectedOption = (Client) JOptionPane.showInputDialog(
                    null,
                    "Select the Client that you want to delete:\n",
                    "Deleting a Client",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No Client selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this Client?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption.getId())) {
                        JOptionPane.showMessageDialog(null, "Client successfully deleted");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the Client");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Client was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Clients registered yet");
        }
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll(instanceModel().findAll()));
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("Clients List:\n");
        if (objectsList.isEmpty()) {
            list.append("No Clients registered yet");
        } else {
            for (Object obj : objectsList) {
                Client objPlane = (Client) obj;
                list.append(objPlane.toString()).append("\n");
            }
        }
        return list;
    }

    public void getByName() {
        String nameSearched = JOptionPane.showInputDialog(null, "Enter the name of the Client you want to search");
        StringBuilder list = new StringBuilder("Filtered by Name: " + nameSearched + "\n");
        list.append(getAll(instanceModel().findByName(nameSearched)));
        JOptionPane.showMessageDialog(null, list);
    }
}
