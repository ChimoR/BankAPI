package controller;

import app.Initializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import model.transfer.Balance;
import model.transfer.BillNumber;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class CheckBalanceHandlerTest {

    @Before
    public void startServer() throws IOException {
        Initializer.startServer();
    }

    @Test
    public void shouldReturnBalanceByBill() throws JsonProcessingException, SQLException {
        PreparedStatement statement =  Initializer.getConnection().prepareStatement("SELECT BALANCE FROM CARD WHERE BILL_NUMBER = '12345'");
        ResultSet set = statement.executeQuery();
        int DBbalance = 0;
        while (set.next()) {
            DBbalance = set.getInt(1);
        }

        String JSON_Balance = BankApiClient.sendCheckBalanceRequest();
        ObjectMapper mapper = new ObjectMapper();
        Balance balance = mapper.readValue(JSON_Balance, Balance.class);

        assertEquals(JSON_Balance, String.format("{\"Balance\":%s}", DBbalance));
        assertNotNull(balance);
    }

}