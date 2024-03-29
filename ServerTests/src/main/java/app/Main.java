package app;

import java.io.IOException;

/**
 * BankAPI - веб-сервис на Http-сервере, формат входящих и исходищих данных - JSON. Имеет 4 энд-поинта:
 * 1) Создание новой карты по номеру счёта и добавление её в БД.
 * 2) Получение списка карт из БД по номеру счёта.
 * 3) Внесение средств по номеру счёта.
 * 4) Проверка баланса по номеру счёта.
 * Используемая БД - H2 in memory со скриптами инициализации схем и предзаполнением данных при старте.
 * @author Rataev Roman
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Initializer.startServer();
    }

}
