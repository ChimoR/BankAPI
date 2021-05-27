import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class Main {

    public static void main(String[] args) {
        createNewCardRequest();
        sendDepositRequest();
        sendCheckBalanceRequest();
        sendCardsListRequest();
    }

    public static void sendCheckBalanceRequest() {
        BillNumber b = new BillNumber();
        b.setBillNumber("12345");
        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            //Отправка запроса
            HttpPost request = new HttpPost("http://localhost:8080/checkbalance");
            String JSON_STRING = mapper.writeValueAsString(b);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            //Обработка ответа
            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);

        } catch (Exception ex) {

        }
    }

    public static void createNewCardRequest() {
        BillNumber b = new BillNumber();
        b.setBillNumber("12345");
        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            //Отправка запроса
            HttpPost request = new HttpPost("http://localhost:8080/createcard");
            String JSON_STRING = mapper.writeValueAsString(b);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            //Обработка ответа
            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);

        } catch (Exception ex) {

        }
    }

    public static void sendDepositRequest() {
        Transaction transaction = new Transaction();
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
            System.out.println(result);

        } catch (Exception ex) {

        }
    }

    public static void sendCardsListRequest() {
        BillNumber b = new BillNumber();
        b.setBillNumber("12345");
        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            //Отправка запроса
            HttpPost request = new HttpPost("http://localhost:8080/cardsbybill");
            String JSON_STRING = mapper.writeValueAsString(b);
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON));

            //Обработка ответа
            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);

        } catch (Exception ex) {

        }
    }
}
