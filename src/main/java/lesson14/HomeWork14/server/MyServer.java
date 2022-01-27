package lesson14.HomeWork14.server;

import lesson14.HomeWork14.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Логика сервера.
 */
public class MyServer {

    /**
     * Сервис аутентификации.
     */
    private AuthService authService;


    /**
     * Активные клиента.
     */
    private List<ClientHandler> clients;

    private static final Logger logger = LogManager.getLogger(MyServer.class);


    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            //authService = new BaseAuthService();
            authService = new DBAuthService();
            authService.start();
            //DBAuthService auth = new DBAuthService();
            //auth.PAuthService();

            clients = new ArrayList<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                logger.info("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                logger.info("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException | SQLException ex) {
            System.out.println("Ошибка в работе сервера.");
            logger.error("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {

        clients.forEach(client -> client.sendMessage(message));
    }

    public synchronized void sendMessageToClient(ClientHandler from, String nickTo, String msg) {

        clients.stream()
                .filter(client -> client.getName().equals(nickTo))
                .forEach(client -> client.sendMessage("от " + from.getName() + ": " + msg));

        Optional<String> optional = Optional.of("str");
        clients.stream()
                .filter(client -> client.getName().equals(nickTo));
        optional.ifPresentOrElse(client -> {
                    from.sendMessage("клиенту " + nickTo + ": " + msg);
                }
                , () -> from.sendMessage("Участника с ником " + nickTo + " нет в чат-комнате"));
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized String getActiveClients() {
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_ACTIVE_COMMAND).append(" ");
        for (ClientHandler clientHandler : clients) {
            sb.append(clientHandler.getName()).append(" " + "\n");
        }
        return sb.toString();
    }

}
