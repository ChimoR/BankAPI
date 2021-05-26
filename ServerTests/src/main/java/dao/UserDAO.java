package dao;

import app.Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для операций с базой данных пользователей
 */
public class UserDAO {

    static final Connection connection = Initializer.getConnection();

    /**
     * Метод для получения имени и владельца карты по UserID
     * @param userID
     * @return
     */
    public ResultSet getOwner(int userID) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement("SELECT FIRST_NAME, LAST_NAME FROM USER " +
                    "WHERE ID = ?");
            prepStmt.setInt(1, userID);
            return prepStmt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
