package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.BillNumber;
import model.Card;
import model.transfer.Exception;
import service.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс - хэндлер для обработки запроса на получение списка карт по номеру счёта
 */
public class GetCardsListHandler extends BillNumberUnmarshaller {

    static final ObjectMapper mapper = new ObjectMapper();
    static final String info = "Received list of cards for bill %s : %s";

    /**
     * Метод для получения списка карт по номеру счёта
     * @param httpExchange объект обмена http-запросом и http-ответом
     * @return Лист объектов класса Card в формате JSON
     */
    public static String getCardsListByBill(HttpExchange httpExchange) throws IOException, SQLException {
        Logger.logRequest(httpExchange);

        BillNumber billNumber = unmarshallBillNumber(httpExchange);
        ArrayList<Card> validCards = new ArrayList<>();

        if (billNumber != null) {
            if (MainHandler.cardDAO.checkBillNumber(billNumber.getBillNumber())) {
                ResultSet rs = MainHandler.cardDAO.getBillCards(billNumber.getBillNumber());

                while (rs.next()) {
                    String number = rs.getString(1);
                    int cvv = rs.getInt(2);
                    int balance = rs.getInt(3);
                    String expDate = rs.getString(4);
                    String owner = rs.getString(5);
                    String bNumber = rs.getString(6);
                    int userID = rs.getInt(7);
                    validCards.add(new Card(number, cvv, balance, expDate, owner, bNumber, userID));
                }

                Logger.logAction(String.format(info, billNumber.getBillNumber(), validCards));
                return mapper.writeValueAsString(validCards);
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
