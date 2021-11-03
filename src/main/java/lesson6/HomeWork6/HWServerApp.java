package lesson6.HomeWork6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HWServerApp {
    public static void main(String[] args) {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            System.out.println("Сервер ожидает подключения... ");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился!");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String message = dataInputStream.readUTF();
                if (message.equals("/end")) {
                    dataOutputStream.writeUTF(message);
                    break;
                }
                System.out.println("Client: " + message);
                dataOutputStream.writeUTF("Server: " + sendMessage());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendMessage() {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        return message;
    }
}
