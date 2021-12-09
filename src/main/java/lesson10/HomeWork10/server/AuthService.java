package lesson10.HomeWork10.server;


import java.sql.SQLException;

/**
 * сервис аутеннтификации
 */
public interface AuthService {

    /**
     * Запустить сервис
     */
    void start() throws SQLException;

    /**
     * Отключить сервис.
     */
    void stop();

    /**
     * Получить никнейм по логину/паролю
     * @param login
     * @param pass
     * @return никнейм если найден или null, если такого нет
     */
    String getNickByLoginAndPass(String login, String pass);
}
