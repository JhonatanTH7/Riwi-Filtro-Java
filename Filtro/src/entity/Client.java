package entity;

public class Client {
//    ENTIDAD CON SUS ATRIBUTOS, CONSTRUCTORES, GETTER, SETTER Y TO-STRING
    private int id;
    private String name;
    private String lastName;
    private String email;

    public Client() {
    }

    public Client(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Client(int id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                "  - ID: " + id +
                        "  Name: " + name +
                        "  LastName: " + lastName +
                        "  Email: " + email;
    }
}
