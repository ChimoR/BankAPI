import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        String createNewCard = "1 - create new card.";
        String deposit = "2 - deposit.";
        String checkBalance = "3 - check balance.";
        String cardsList = "4 - cards list.";
        String testAll = "5 - send all requests. Order - create card, check balance, cards list, deposit";
        String exit = "6 - exit.";
        boolean flag = true;

        while (flag) {
            System.out.printf("Choose option:\n%s\n%s\n%s\n%s\n%s\n%s\n", createNewCard, deposit, checkBalance, cardsList, testAll, exit);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            int operation = Integer.parseInt(console.readLine());
            String billNumber;
            switch (operation) {
                case 1 :
                    System.out.println("Enter bill number");
                    billNumber = console.readLine();
                    createNewCardRequest(billNumber);
                    break;

                case 2 :
                    System.out.println("Enter bill number");
                    billNumber = console.readLine();
                    System.out.println("Enter amount to deposit");
                    int amount = Integer.parseInt(console.readLine());
                    sendDepositRequest(billNumber, amount);
                    break;

                case 3 :
                    System.out.println("Enter bill number");
                    billNumber = console.readLine();
                    sendCheckBalanceRequest(billNumber);
                    break;
                case 4 :
                    System.out.println("Enter bill number");
                    billNumber = console.readLine();
                    sendCardsListRequest(billNumber);
                    break;
                case 5:
                    System.out.println("Enter bill number");
                    billNumber = console.readLine();
                    System.out.println("Enter amount to deposit");
                    amount = Integer.parseInt(console.readLine());
                    createNewCardRequest(billNumber);
                    sendCheckBalanceRequest(billNumber);
                    sendCardsListRequest(billNumber);
                    sendDepositRequest(billNumber, amount);
                    break;
                case 6 :
                    flag = false;
            }
        }
    }

    public static void sendCheckBalanceRequest(String billNumber) {
        BillNumber b = new BillNumber();
        b.setBillNumber(billNumber);
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
            ex.printStackTrace();
        }
    }

    public static void createNewCardRequest(String billNumber) {
        BillNumber b = new BillNumber();
        b.setBillNumber(billNumber);
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
            ex.printStackTrace();
        }
    }

    public static void sendDepositRequest(String billNumber, int amount) {
        Transaction transaction = new Transaction();
        transaction.setBillNumber(billNumber);
        transaction.setAmount(amount);

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
            ex.printStackTrace();
        }
    }

    public static void sendCardsListRequest(String billNumber) {
        BillNumber b = new BillNumber();
        b.setBillNumber(billNumber);
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
            ex.printStackTrace();
        }
    }
}
