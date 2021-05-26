package dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDAOTest {

    private UserDAO userDAO = new UserDAO();

    @Test
    public void getOwner() throws SQLException {
        ResultSet rs = userDAO.getOwner(1);
        rs.next();

        assertEquals(rs.getString(1), "FIRST");
        assertEquals(rs.getString(2), "ANONIM");
    }
}