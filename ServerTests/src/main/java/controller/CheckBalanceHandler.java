package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.Balance;
import model.transfer.BillNumber;
import model.transfer.Exception;
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
     * @param httpExchange httpExchange объект обмена http-запросом и http-ответом
     * @return объект класса Balance в формате JSON
     */

    public static String checkBillBalanceFromJSON(HttpExchange httpExchange) throws IOException {
        Logger.logRequest(httpExchange);

        BillNumber billNumber = unmarshallBillNumber(httpExchange);

        if (billNumber != null) {
            if (MainHandler.cardDAO.checkBillNumber(billNumber.getBillNumber())) {
                int balance = MainHandler.cardDAO.getBalance(billNumber.getBillNumber());

                Balance balanceObj = new Balance();
                balanceObj.setBalance(balance);

                Logger.logAction(String.format(info, billNumber.getBillNumber(), balance));
                return mapper.writeValueAsString(balanceObj);
            }
            else {
                Exception exception = new Exception();
                exception.setMessage("Bill number not found");

                Logger.logException(exception.getMessage());
                return mapper.writeValueAsString(exception);
            }
        }
        else {
            Exception exception = new Exception();
            exception.setMessage("Bill number not found");

            Logger.logException(exception.getMessage());
            return mapper.writeValueAsString(exception);
        }
    }
}
