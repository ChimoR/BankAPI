package service;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс - логгер
 */
public class Logger {

    public static final String LOG_FILE = "/Users/rataevroman/IdeaProjects/ServerTests/src/main/resources/log";

    public static final String REQUEST_TEMPLATE = "%s   Received %s request on URI: %s \n";
    public static final String RESPONSE_TEMPLATE = "%s   Sent response on %s request on URI: %s \n \n";
    public static final String EXCEPTION_TEMPLATE = "%s   Exception occurred, message : %s\n";
    public static final String ACTION_TEMPLATE = "%s   Action: %s\n";

    /** Метод логгирующий принятый поступивший запрос */
    public static void logRequest(HttpExchange httpExchange) throws IOException {
        String date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
        writer.write(String.format(REQUEST_TEMPLATE, date, httpExchange.getRequestMethod(), httpExchange.getRequestURI()));
        writer.close();
    }

    /** Метод логгирующий отправку ответа на запрос */
    public static void logResponse(HttpExchange httpExchange) throws IOException {
        String date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
        writer.write(String.format(RESPONSE_TEMPLATE, date, httpExchange.getRequestMethod(), httpExchange.getRequestURI()));
        writer.close();
    }

    /** Метод логгирующий возникшее исключение */
    public static void logException(String message) throws IOException {
        String date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
        writer.write(String.format(EXCEPTION_TEMPLATE, date, message));
        writer.close();
    }

    /** Метод логгирующий действие */
    public static void logAction(String message) throws IOException {
        String date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
        writer.write(String.format(ACTION_TEMPLATE, date, message));
        writer.close();

    }

}
