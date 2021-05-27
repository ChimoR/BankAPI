import com.fasterxml.jackson.annotation.JsonProperty;

public class BillNumber {

    @JsonProperty("Bill Number")
    private String billNumber;

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public BillNumber() {
    }
}
