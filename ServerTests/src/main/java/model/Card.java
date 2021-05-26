package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс - сущность банковской карты
 */
public class Card {

    @JsonProperty("Number")
    private String number;

    @JsonProperty("Expiration date")
    private String expirationDate;

    @JsonProperty("Owner")
    private String owner;

    @JsonProperty("Bill Number")
    private String billNumber;

    @JsonProperty("Balance")
    private int balance;

    @JsonProperty("CVV")
    private int cvv;

    @JsonProperty("UserID")
    private int userID;

    public Card() {
    }

    public Card(String number, int cvv, int balance, String expirationDate, String owner, String billNumber, int userID) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.owner = owner;
        this.billNumber = billNumber;
        this.balance = balance;
        this.cvv = cvv;
        this.userID = userID;
    }

    public String getNumber() {
        return number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getOwner() {
        return owner;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getCvv() {
        return cvv;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", owner='" + owner + '\'' +
                ", billNumber='" + billNumber + '\'' +
                ", balance=" + balance +
                ", cvv=" + cvv +
                ", userID=" + userID +
                '}';
    }
}
