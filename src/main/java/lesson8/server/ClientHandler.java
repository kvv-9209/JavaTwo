package lesson8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import lesson8.constants.Constants;

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
                    authentification();
                    readMessage();
                } catch (IOException ex) {
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

    private void authentification() throws IOException {
        nonAuthClient(name);
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");    //3
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick != null) {
                    //Дописать проверку что такого ника нет в чате(*)
                    //Авторизовались
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

    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            //hint: можем получать команду
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            } else if (messageFromClient.startsWith(Constants.PRIVAT_MESSAGE_COMMAND)) {
                String[] tokens = messageFromClient.split("\\s+");
                String nick = tokens[1];
                String msg = messageFromClient.substring(4 + nick.length());
                server.sendMessageToClient(this, nick, msg);
            } else if (messageFromClient.startsWith(Constants.CLIENTS_ACTIVE_COMMAND)) {
                sendMessage(server.getActiveClients());
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

    /**
     * Метод отвечает за отключение неавторизованных пользователей
     * по тайм-ауту (120 сек. ждём после подключения клиента и,
     * если он не авторизовался за это время, закрываем соединение)
     * @param nickName имя пользователя
     */
    private void nonAuthClient(String nickName) {
        new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (nickName == null) {
                closeConnection();
            }
            System.out.println("Нет ответа от пользователя");
        }).start();

    }
}
