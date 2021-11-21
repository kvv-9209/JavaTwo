package lesson8.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import lesson8.constants.Constants;

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


    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService();
            authService.start();

            clients = new ArrayList<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {

        clients.forEach(client -> client.sendMessage(message));

        /*for (ClientHandler client : clients) {
            client.sendMessage(message);
        }*/
    }

    public synchronized void sendMessageToClient(ClientHandler from, String nickTo, String msg) {

        for (ClientHandler client : clients) {
            if (client.getName().equals(nickTo)) {
                client.sendMessage("от " + from.getName() + ": " + msg);
                from.sendMessage("клиенту " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMessage("Участника с ником " + nickTo + " нет в чат-комнате");
        /**
         * Не понимаю почему не работает с лямбда-выражением
         * в случае как ниже, from.sendMessage пишется в любом случае
         */
/*        clients.forEach(client -> {
            if (client.getName().equals(nickTo)) {
                client.sendMessage("от " + from.getName() + ": " + msg);
                from.sendMessage("клиенту " + nickTo + ": " + msg);
                return;
            }
        });
        from.sendMessage("Участника с ником " + nickTo + " нет в чат-комнате");*/
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
