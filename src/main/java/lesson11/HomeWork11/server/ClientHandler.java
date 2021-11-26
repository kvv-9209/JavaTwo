package lesson11.HomeWork11.server;

import lesson11.HomeWork11.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Обработчик для конкретного клиента.
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    /**
                     * по тайм-ауту (20 мин. ждём после подключения клиента и,
                     * если он не авторизовался за это время, закрываем соединение)
                     */
                    new Thread(() -> {
                        try {
                            Thread.sleep(120_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (name == null) {
                            System.out.println("Клиент не авторизовался");
                            closeConnection();
                        }
                    }).start();

                    authentification();
                    readMessage();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    public String getName() {
        return name;
    }
    // /auth login pass

    private void authentification() throws IOException, SQLException {

        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.REG_COMMAND)) {
                String[] token = str.split("\\s+");
                DBAuthService registration = new DBAuthService();
                if (token[1] != null && token[2] != null && token[3] != null) {
                    registration.insertUser(token[1], token[2], token[3]);
                }
            }
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");    //3
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick != null) {
                    name = nick;
                    sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " вошел в чат");
                    server.subscribe(this);
                    return;
                } else {
                    sendMessage("Неверные логин/пароль");
                }
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException, SQLException {
        while (true) {
            String messageFromClient = in.readUTF();
            //hint: можем получать команду
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            String[] token = messageFromClient.split("\\s+");

            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            } else if (messageFromClient.startsWith(Constants.PRIVAT_MESSAGE_COMMAND)) {
                String nick = token[1];
                String msg = messageFromClient.substring(4 + nick.length());
                server.sendMessageToClient(this, nick, msg);
            } else if (messageFromClient.startsWith(Constants.CLIENTS_ACTIVE_COMMAND)) {
                sendMessage(server.getActiveClients());
            } else if (messageFromClient.startsWith(Constants.RENAME_NICK_COMMAND)) {
                String newNick = token[1];
                DBAuthService newNickName = new DBAuthService();
                newNickName.renameToNick(newNick, name);
            } else {
                server.broadcastMessage(name + ": " + messageFromClient);
            }
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            out.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            socket.close();
        } catch (IOException ex) {
            //ignore
        }
    }


}
