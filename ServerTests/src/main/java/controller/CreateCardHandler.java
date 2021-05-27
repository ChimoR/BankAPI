package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.BillNumber;
import model.Card;
import model.transfer.Exception;
import service.Logger;
import service.PropertyGenerator;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс - хэндлер для обработки запроса на создание новой карты по номеру счёта
 */
public class CreateCardHandler extends BillNumberUnmarshaller {

    static final ObjectMapper mapper = new ObjectMapper();
    static final String info = "Generated and added new card %s";

    /**
     * Метод для создания новой карты по номеру счёта
     * @param httpExchange объект обмена http-запросом и http-ответом
     * @return объект класса Card в JSON формате
     */
    public static String createCardFromJson(HttpExchange httpExchange) throws IOException, SQLException {
        Logger.logRequest(httpExchange);

        BillNumber bn = unmarshallBillNumber(httpExchange);

        String cardNumber = PropertyGenerator.generateCardProperties("number");
        String expDate = PropertyGenerator.generateCardProperties("date");
        int cvv = Integer.parseInt(PropertyGenerator.generateCardProperties("CVV"));
        StringBuilder owner = new StringBuilder();
        int balance = 0;
        int userID = 0;

        if (bn != null) {
            if (MainHandler.cardDAO.checkBillNumber(bn.getBillNumber())) {
                ResultSet rs = MainHandler.cardDAO.getBalanceAndUserID(bn.getBillNumber());
                while (rs.next()) {
                    balance = rs.getInt(1);
                    userID = rs.getInt(2);
                }

                rs = MainHandler.userDAO.getOwner(userID);
                while (rs.next()) {
                    owner.append(rs.getString(1));
                    owner.append(" ");
                    owner.append(rs.getString(2));
                }

                Card newCard = new Card(cardNumber, cvv, balance, expDate, owner.toString(), bn.getBillNumber(), userID);

                MainHandler.cardDAO.addNewCard(newCard);

                Logger.logAction(String.format(info, newCard));
                return mapper.writeValueAsString(newCard);
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
