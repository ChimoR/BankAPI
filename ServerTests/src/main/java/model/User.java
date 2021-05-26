package model;

/**
 * Класс - сущность пользователя
 */
public class User {

    private int id;

    private String firstName;

    private String lastName;

    private String billNumber;

    public User() {
    }

    public User(int id, String fistName, String lastName, String billNumber) {
        this.id = id;
        this.firstName = fistName;
        this.lastName = lastName;
        this.billNumber = billNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", billNumber='" + billNumber + '\'' +
                '}';
    }
}
