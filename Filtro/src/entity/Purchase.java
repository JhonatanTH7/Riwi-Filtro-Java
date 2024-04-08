package entity;

import java.sql.Date;

public class Purchase {

    private int id;
    private Date purchaseDate;
    private int quantity;
    private int idClient;
    private int idProduct;

    public Purchase() {
    }

    public Purchase(Date purchaseDate, int quantity, int idClient, int idProduct) {
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.idClient = idClient;
        this.idProduct = idProduct;
    }

    public Purchase(int id, Date purchaseDate, int quantity, int idClient, int idProduct) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.idClient = idClient;
        this.idProduct = idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return
                "  - ID: " + id +
                        "  PurchaseDate: " + purchaseDate +
                        "  Quantity: " + quantity +
                        "  ClientID: " + idClient +
                        "  ProductID: " + idProduct;
    }
}
