package dao;

import app.Initializer;
import model.Card;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CardDAOTest {

    private CardDAO cardDAO = new CardDAO();

    @Test
    public void shouldReturnValidBalanceAndUserIDForBill() throws SQLException {
        PreparedStatement statement =  Initializer.getConnection().prepareStatement("SELECT BALANCE, USERID FROM CARD WHERE BILL_NUMBER = '12345'");
        ResultSet set = statement.executeQuery();
        int DBbalance = 0;
        int userID = 0;
        while (set.next()) {
            DBbalance = set.getInt(1);
            userID = set.getInt(2);
        }
        ResultSet rs = cardDAO.getBalanceAndUserID("12345");
        rs.next();

        assertEquals(rs.getInt(1), DBbalance);
        assertEquals(rs.getInt(2), userID);
    }

    @Test
    public void shouldReturnValidBalanceForBill() throws SQLException {
        PreparedStatement statement =  Initializer.getConnection().prepareStatement("SELECT BALANCE FROM CARD WHERE BILL_NUMBER = '12345'");
        ResultSet set = statement.executeQuery();
        int balance1 = 0;
        while (set.next()) {
            balance1 = set.getInt(1);
        }

        assertEquals(balance1, cardDAO.getBalance("12345"));
    }

    @Test
    public void shouldUpdateCorrectAmountCardBalancesForBill() throws SQLException {
        PreparedStatement statement = Initializer.getConnection().prepareStatement("SELECT NUMBER FROM CARD WHERE BILL_NUMBER = '12345'");
        ResultSet rs = statement.executeQuery();
        int counter = 0;
        while (rs.next()) {
            rs.getString(1);
            counter++;
        }

        assertEquals(cardDAO.updateBalance("12345", 100), counter);
    }

    @Test
    public void shouldReturnValidCardNumbersForBill() throws SQLException {
        ResultSet rs = cardDAO.getBillCards("12345");
        rs.next();

        assertEquals(rs.getString(1), "1234567890");
        rs.next();

        assertEquals(rs.getString(1), "1234567891");
    }

    @Test
    public void shouldInsertOneCard() {
        Card card = new Card("2345678901", 123, 777, "11.11.2011", "Third Anonim", "34567", 3);
        assertEquals(cardDAO.addNewCard(card), 1);
    }
}