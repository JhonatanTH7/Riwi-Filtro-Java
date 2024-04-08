package entity;

public class Store {
    private int id;
    private String name;
    private String location;

    public Store() {
    }

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Store(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return
                "  - ID: " + id +
                        "  Name: " + name + '\'' +
                        "  Location: " + location;
    }
}
