package lesson12.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lesson12.constants.Constants;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Controller {


    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private File parentDir;
    private File file;


    @FXML
    private TextArea textChat;
    @FXML
    private TextField textSend;
    @FXML
    private Button buttonSend;
    @FXML
    private TextField login;
    @FXML
    private TextField pass;
    @FXML
    private Button buttonAuth;
    @FXML
    private TextArea activeUsers;
    @FXML
    private Button buttonActiveUsers;
    @FXML
    private TextField loginReg;
    @FXML
    private TextField nickReg;
    @FXML
    private TextField passReg;
    @FXML
    private Button buttonReg;
    @FXML
    private TextField nickRename;
    @FXML
    private Button buttonRename;


    public Controller() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (textSend.getText().trim().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textSend.getText());
            textSend.setText("");
            //   textField.grabFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = dataInputStream.readUTF();
                    if (messageFromServer.equals(Constants.END_COMMAND)) {
                        break;
                    } else if (messageFromServer.startsWith(Constants.CLIENTS_ACTIVE_COMMAND)) {
                        activeUsers.setText("");
                        activeUsers.appendText(messageFromServer.substring(5));
                    } else if (messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {
                        createFileHistory();
                        login.setVisible(false);
                        pass.setVisible(false);
                        buttonAuth.setVisible(false);
                        readHistory();
                    } else {
                        writeHistory(messageFromServer);
                        textChat.appendText(messageFromServer);
                        textChat.appendText("\n");
                    }
                }
                textChat.appendText("Соединение разорвано");
                textSend.setEditable(false);
                closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {
        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {
        }
        try {
            socket.close();
        } catch (Exception ex) {

        }
    }

    private void createFileHistory() throws IOException {
        parentDir = new File("History");
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        file = new File(parentDir, "history_" + login.getText() + ".txt");

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private void writeHistory(String text) throws IOException {
        if (file != null) {
            try (Writer writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(text + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readHistory() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("History/history_" +
                    login.getText() + ".txt"), StandardCharsets.UTF_8);
            int buffer = lines.size();
            if (buffer < 100) {
                for (String readBylines : lines) {
                    textChat.appendText(readBylines + "\n");
                }
            } else {
                lines.stream()
                        .skip(buffer - 100)
                        .forEach(nextLines -> textChat.appendText(nextLines + "\n"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onClickAuth() {
        try {
            dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " "
                    + login.getText() + " " + pass.getText());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void onClickSend() {
        sendMessage();
    }

    @FXML
    public void onClickActiveUsers() {
        try {
            dataOutputStream.writeUTF(Constants.CLIENTS_ACTIVE_COMMAND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void onClickReg() {
        try {
            dataOutputStream.writeUTF(Constants.REG_COMMAND + " "
                    + loginReg.getText() + " " + passReg.getText() + " " + nickReg.getText());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void onClickRename() {
        try {
            dataOutputStream.writeUTF(Constants.RENAME_NICK_COMMAND + " "
                    + nickRename.getText());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
