package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.Balance;
import model.transfer.DepositTransaction;
import service.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Класс - хэндлер для обработки запроса на внесение средств по номеру счёта
 */
public class DepositHandler {

    static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод для обновления баланса по номеру счёта
     * @param httpExchange
     * @return объект класса Balance в формате JSON
     * @throws IOException
     */
    public static String deposit(HttpExchange httpExchange) throws IOException {
        Logger.logRequest(httpExchange);
        String info = "Deposited %s to bill %s, new balance = %d";

        InputStream input = httpExchange.getRequestBody();
        String result = new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining("\n"));
        DepositTransaction depositTransaction = mapper.readValue(result, DepositTransaction.class);

        int balance = MainHandler.cardDAO.getBalance(depositTransaction.getBillNumber());
        int newBalance = depositTransaction.getAmount() + balance;

        MainHandler.cardDAO.updateBalance(depositTransaction.getBillNumber(), newBalance);

        Balance balanceObj = new Balance();
        balanceObj.setBalance(newBalance);

        Logger.logAction(String.format(info, depositTransaction.getAmount(),depositTransaction.getBillNumber(), newBalance));
        return mapper.writeValueAsString(balanceObj);
    }
}
