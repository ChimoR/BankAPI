package model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Утилитный класс, используемый для обработки транзакции внесения средств по номеру счёта
 */
public class DepositTransaction {

    @JsonProperty("Bill Number")
    String billNumber;

    @JsonProperty("Amount")
    int amount;

    public DepositTransaction() {
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
