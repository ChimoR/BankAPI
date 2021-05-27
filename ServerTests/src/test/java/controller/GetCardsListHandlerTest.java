package controller;

import app.Initializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Card;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetCardsListHandlerTest {

    @Before
    public void startServer() throws IOException {
        Initializer.startServer();
    }

    @Test
    public void shouldReturnCardsListByBill() throws JsonProcessingException, SQLException {
        PreparedStatement statement = Initializer.getConnection().prepareStatement("SELECT NUMBER FROM CARD WHERE BILL_NUMBER = '12345'");
        ResultSet rs = statement.executeQuery();
        int counter = 0;
        while (rs.next()) {
            rs.getString(1);
            counter++;
        }

        String JSON_CardsList = BankApiClient.sendCardsListRequest();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Card> cards = mapper.readValue(JSON_CardsList,ArrayList.class);

        assertNotNull(cards);
        assertEquals(cards.size(), counter);
    }
}