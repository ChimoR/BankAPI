package controller;

import app.Initializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.transfer.Balance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepositHandlerTest {

    @Before
    public void startServer() {
        Initializer.startServer();
    }

    @Test
    public void deposit() throws JsonProcessingException {
        String JSON_Balance = BankApiClient.sendDepositRequest();
        ObjectMapper mapper = new ObjectMapper();
        Balance balance = mapper.readValue(JSON_Balance, Balance.class);

        assertEquals(JSON_Balance, "{\"Balance\":300}");
        assertNotNull(balance);
    }
}