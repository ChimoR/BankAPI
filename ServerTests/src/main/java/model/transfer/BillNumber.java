package model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Утилитный класс, используемый для генерации номера счёта в JSON формате
 */
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
