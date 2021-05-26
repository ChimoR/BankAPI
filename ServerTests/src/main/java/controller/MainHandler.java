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
     * @param httpExchange
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String responseData = null;

        if ("POST".equals(httpExchange.getRequestMethod())) {
            if (httpExchange.getRequestURI().toString().contains("/createcard")) {
                try {
                    responseData = CreateCardHandler.createCardFromJson(httpExchange);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (httpExchange.getRequestURI().toString().contains("/checkbalance")) {
                responseData = CheckBalanceHandler.checkBillBalanceFromJSON(httpExchange);
            }

            if (httpExchange.getRequestURI().toString().contains("/cardsbybill")) {
                try {
                    responseData = GetCardsListHandler.getCardsListByBill(httpExchange);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (httpExchange.getRequestURI().toString().contains("/deposit")) {
                responseData = DepositHandler.deposit(httpExchange);
            }
        }

        writeDataToResponse(httpExchange, responseData);
    }

    /**
     * Метод, записывающий данные в тело ответа на запрос
     * @param httpExchange
     * @param data данные для записи
     * @throws IOException
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
