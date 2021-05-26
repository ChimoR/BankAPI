package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.transfer.BillNumber;
import model.transfer.DepositTransaction;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


/**
 * Класс - имитатор клиента веб-сервиса
 */
public class BankApiClient {

    /** Отправка запроса на получение баланса по номеру счёта */
    public static String sendCheckBalanceRequest() {
        BillNumber b = new BillNumber();
        b.setBillNumber("12345");
        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("http://localhost:8080/checkbalance");
            String JSON_STRING = mapper.writeValueAsString(b);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /** Отправка запроса на создание новой карты по номеру счёта */
    public static String createNewCardRequest() {
        BillNumber b = new BillNumber();
        b.setBillNumber("12345");
        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("http://localhost:8080/createcard");
            String JSON_STRING = mapper.writeValueAsString(b);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /** Отправка запроса на внесение средств по номеру счёта */
    public static String sendDepositRequest() {
        DepositTransaction transaction = new DepositTransaction();
        transaction.setBillNumber("12345");
        transaction.setAmount(200);

        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://localhost:8080/deposit");
            String JSON_STRING = mapper.writeValueAsString(transaction);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /** Отправка запроса на получение списка карт по номеру счёта */
    public static String sendCardsListRequest() {
        BillNumber b = new BillNumber();
        b.setBillNumber("12345");
        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("http://localhost:8080/cardsbybill");
            String JSON_STRING = mapper.writeValueAsString(b);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
