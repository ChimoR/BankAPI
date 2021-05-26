package app;

import com.sun.net.httpserver.HttpServer;
import controller.MainHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.ArrayList;

/**
 * Класс - начальный инициализатор
 */
public class Initializer {

    /** Лист с номерами созданных карт */
    public static final ArrayList<String> existingCardNumbers = new ArrayList<>();

    static HttpServer server = null;
    static Connection connection = null;
    static final String DB_URL = "jdbc:h2:mem:default;INIT=runscript from 'src/main/resources/initUsers'\\;" +
            "runscript from 'src/main/resources/initCards'";

    /** Метод для вызова всех инициализирующих методов */
    public static void startServer() {
        preloadCardNumbers();
        initServer();
    }

    /** Предзаплнение листа номерами карт из БД */
    public static boolean preloadCardNumbers() {
        connection = getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT NUMBER FROM CARD");
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                existingCardNumbers.add(rs.getString(1));
            }
            return true;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** Запуск http сервера */
    public static boolean initServer() {
        if (server == null) {
            try {
                server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            if (server != null) {
                server.createContext("/", new MainHandler());
                server.start();
            }
        }
        return true;
    }

    /** Синглтон реализация для соединения с БД */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
