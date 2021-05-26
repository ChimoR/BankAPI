package model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Утилитный класс, используемый для огенерации баланса на счету в JSON формате
 */
public class Balance {

    @JsonProperty("Balance")
    int balance;

    public Balance() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
