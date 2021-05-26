package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.Balance;
import model.transfer.BillNumber;
import service.Logger;

import java.io.IOException;

/**
 * Класс-хэндлер для обработки запроса получения баланса по номеру счёта
 */
public class CheckBalanceHandler extends BillNumberUnmarshaller {

    static final ObjectMapper mapper = new ObjectMapper();
    static final String info = "Checked balance of bill %s - %s";

    /**
     * Метод для получения баланса по номеру счёта
     * @param httpExchange
     * @return объект класса Balance в формате JSON
     * @throws IOException
     */

    public static String checkBillBalanceFromJSON(HttpExchange httpExchange) throws IOException {
        Logger.logRequest(httpExchange);

        BillNumber billNumber = unmarshallBillNumber(httpExchange);
        int balance = MainHandler.cardDAO.getBalance(billNumber.getBillNumber());

        Balance balanceObj = new Balance();
        balanceObj.setBalance(balance);

        Logger.logAction(String.format(info, billNumber.getBillNumber(), balance));
        return mapper.writeValueAsString(balanceObj);
    }
}
