package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.BillNumber;
import service.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Утилитный класс для хэндлеров
 */
public abstract class BillNumberUnmarshaller {

    /**
     * Метод для получения номера счёта из JSON, полученного из запроса
     * @param httpExchange объект обмена http-запросом и http-ответом
     * @return объект класса BillNumber
     */
    public static BillNumber unmarshallBillNumber(HttpExchange httpExchange) throws IOException {
        InputStream input = httpExchange.getRequestBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.joining("\n"));

        try {
            return objectMapper.readValue(result, BillNumber.class);
        }
        catch (JsonProcessingException e) {
            Logger.logException(e.getMessage());
            return null;
        }
    }
}
