package dao;

import app.Initializer;
import model.Card;
import service.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для операций с базой данных карт
 */
public class CardDAO {

    static final Connection connection = Initializer.getConnection();

    /**
     * Метод для получения баланса и UserID по номеру счёта
     * @param billNumber номер счёта
     * @return ResultSet с балансом и UserID
     */
    public ResultSet getBalanceAndUserID(String billNumber) throws IOException {
        try {
            PreparedStatement prepStmt = connection.prepareStatement("SELECT BALANCE, USERID FROM CARD " +
                    "WHERE BILL_NUMBER = ?");
            prepStmt.setString(1, billNumber);
            return prepStmt.executeQuery();
        }
        catch (SQLException throwables) {
            Logger.logException(throwables.getMessage());
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Метод для получения баланса из БД по номеру счёта
     * @param billNumber номер счёта
     * @return баланс
     */
    public int getBalance(String billNumber) throws IOException {
        int balance = -1;
        try {
            PreparedStatement prepStmt = connection.prepareStatement("SELECT BALANCE FROM CARD " +
                    "WHERE BILL_NUMBER = ?");
            prepStmt.setString(1, billNumber);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                 balance = Integer.parseInt(rs.getString(1));
            }
        }
        catch (SQLException e) {
            Logger.logException(e.getMessage());
            e.printStackTrace();
        }

        return balance;
    }

    /**
     * Метод для обновления баланса на картах по номеру счёта в БД
     * @param billNumber номер счёта
     * @param newBalance значение, которое буде присвоено балансу
     */
    public int updateBalance(String billNumber, int newBalance) throws IOException {
        try {
            PreparedStatement prepStmt = connection.prepareStatement("UPDATE CARD SET BALANCE = ? " +
                    "WHERE BILL_NUMBER = ?");
            prepStmt.setInt(1, newBalance);
            prepStmt.setString(2, billNumber);
            return prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            Logger.logException(e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Метод для получения списка карт из БД по номеру счёта
     * @param billNumber номер счёта
     * @return ResultSet из всех столбцов таблицы Card
     */
    public ResultSet getBillCards(String billNumber) throws IOException {
        try {
            PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM CARD " +
                    "WHERE BILL_NUMBER = ?");
            prepStmt.setString(1,billNumber);
            return prepStmt.executeQuery();
        }
        catch (SQLException e) {
            Logger.logException(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Метод для добавления новой карты в БД
     * @param card объект класса Card
     */
    public int addNewCard(Card card) throws IOException {
        try {
            PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO CARD " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");

            prepStmt.setString(1, card.getNumber());
            prepStmt.setInt(2, card.getCvv());
            prepStmt.setInt(3, card.getBalance());
            prepStmt.setString(4, card.getExpirationDate());
            prepStmt.setString(5, card.getOwner());
            prepStmt.setString(6, card.getBillNumber());
            prepStmt.setInt(7, card.getUserID());

            return prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            Logger.logException(e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Метод для проверки существования номера счёта в БД
     * @param billNumber объект номера счёта
     * @return boolean
     */
    public boolean checkBillNumber(String billNumber) throws IOException {
        try {
            PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM CARD " +
                    "WHERE BILL_NUMBER = ?");

            prepStmt.setString(1, billNumber);

            ResultSet rs =  prepStmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            Logger.logException(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
