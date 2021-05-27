package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.CardDAO;
import dao.UserDAO;
import service.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

/**
 * Главный класс хэндлеров
 */
public class MainHandler implements HttpHandler {

    public static final UserDAO userDAO = new UserDAO();
    public static final CardDAO cardDAO = new CardDAO();

    /**
     * Метод, обращающийся к конкретному хэндлеру в зависимости от энд-поинта
     * @param httpExchange объект обмена http-запросом и http-ответом
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String responseData = null;

        if ("POST".equals(httpExchange.getRequestMethod())) {
            if (httpExchange.getRequestURI().toString().equals("/createcard")) {
                try {
                    responseData = CreateCardHandler.createCardFromJson(httpExchange);
                } catch (SQLException throwables) {
                    Logger.logException("SQLException " + throwables.getMessage());
                    throwables.printStackTrace();
                }
            }

            if (httpExchange.getRequestURI().toString().equals("/checkbalance")) {
                responseData = CheckBalanceHandler.checkBillBalanceFromJSON(httpExchange);
            }

            if (httpExchange.getRequestURI().toString().equals("/cardsbybill")) {
                try {
                    responseData = GetCardsListHandler.getCardsListByBill(httpExchange);
                } catch (SQLException throwables) {
                    Logger.logException("SQLException " + throwables.getMessage());
                    throwables.printStackTrace();
                }
            }

            if (httpExchange.getRequestURI().toString().equals("/deposit")) {
                responseData = DepositHandler.deposit(httpExchange);
            }
        }

        if (responseData != null) {
            writeDataToResponse(httpExchange, responseData);
        }
    }

    /**
     * Метод, записывающий данные в тело ответа на запрос
     * @param httpExchange объект обмена http-запросом и http-ответом
     * @param data данные для записи
     */
    public static void writeDataToResponse(HttpExchange httpExchange, String data) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(200, data.length());
        outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        Logger.logResponse(httpExchange);
    }
}
