package entity;

public class Product {
    private int id;
    private String name;
    private double price;
    private int idStore;

    public Product() {
    }

    public Product(String name, double price, int idStore) {
        this.name = name;
        this.price = price;
        this.idStore = idStore;
    }

    public Product(int id, String name, double price, int idStore) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idStore = idStore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    @Override
    public String toString() {
        return
                "  - ID: " + id +
                        "  Name: " + name + '\'' +
                        "  Price: " + price +
                        "  StoreID: " + idStore;
    }
}
