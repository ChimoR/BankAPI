package controller;

import app.Initializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Card;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CreateCardHandlerTest {

    @Before
    public void startServer() throws IOException {
        Initializer.startServer();
    }

    @Test
    public void shouldReturnJSONCardEntity() throws JsonProcessingException {
        String JSON_Card = BankApiClient.createNewCardRequest();
        ObjectMapper mapper = new ObjectMapper();
        Card card = mapper.readValue(JSON_Card, Card.class);

        assertNotNull(card);
    }
}