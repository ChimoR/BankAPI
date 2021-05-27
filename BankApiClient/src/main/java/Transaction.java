import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    @JsonProperty("Bill Number")
    String billNumber;

    @JsonProperty("Amount")
    int amount;

    public Transaction() {
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
