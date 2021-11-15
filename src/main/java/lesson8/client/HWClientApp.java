package lesson8.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import lesson8.constants.Constants;

public class HWClientApp extends JFrame {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private JPanel allPanel;
    private JPanel messagePan;
    private JPanel clientsConnectPan;
    private JTextArea textArea;
    private JTextField textField;
    private JButton button;
    private JTextArea usersList;

    public HWClientApp() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HWCApp();
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
                        usersList.setText("");
                        usersList.append(messageFromServer.substring(5));

                    } else {
                        textArea.append(messageFromServer);
                        textArea.append("\n");
                    }
                }
                textArea.append("Соединение разорвано");
                textField.setEnabled(false);
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

    private void sendMessage() {
        if (textField.getText().trim().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");
            textField.grabFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
        }
    }

    private void HWCApp() {

        setTitle("HWChat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);


        /**
         *Общая панель с элементами
         */
        allPanel = new JPanel(new BorderLayout());
        add(allPanel);

        /**
         *панель для поля текста, кнопки отправки
         * поля ввода сообщения
         * поля авторизации
         */

        JPanel panelTextMessageAuth = new JPanel(new BorderLayout());
        allPanel.add(panelTextMessageAuth, BorderLayout.CENTER);

        /**
         * панель приконекченных клиентов
         */

        JPanel usersActive = new JPanel(new BorderLayout());
        usersList = new JTextArea();
        usersList.setEditable(false);
        JButton buttonActiveUsers = new JButton("Список пользователей");
        buttonActiveUsers.setMaximumSize(new Dimension(78, 150));
        allPanel.add(usersActive, BorderLayout.EAST);
        usersActive.add(usersList, BorderLayout.CENTER);
        usersActive.add(buttonActiveUsers, BorderLayout.SOUTH);


        /**
         * панель для текстового поля
         */
        messagePan = new JPanel(new BorderLayout());
        panelTextMessageAuth.add(messagePan, BorderLayout.CENTER);
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        messagePan.add(textArea, BorderLayout.CENTER);
        messagePan.add(new JScrollPane(textArea));

        /**
         * панель ввода и отправки сообщения
         */
        JPanel panelSend = new JPanel(new BorderLayout());
        panelSend.setLayout(new BorderLayout(0, 0));
        panelTextMessageAuth.add(panelSend, BorderLayout.SOUTH);
        textField = new JTextField();
        panelSend.add(textField, BorderLayout.CENTER);
        button = new JButton();
        button.setHorizontalAlignment(0);
        button.setMaximumSize(new Dimension(78, 100));
        button.setVerticalAlignment(0);
        button.putClientProperty("hideActionText", Boolean.FALSE);
        button.setText("Отправить");
        panelSend.add(button, BorderLayout.EAST);


        /**
         * панель авторизации
         */
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
        JTextField loginField = new JTextField();
        JTextField passField = new JTextField();
        JButton authButton = new JButton("Авторизоваться");
        authButton.setMaximumSize(new Dimension(78, 100));
        loginPanel.add(loginField);
        loginPanel.add(passField);
        loginPanel.add(authButton);
        panelTextMessageAuth.add(loginPanel, BorderLayout.NORTH);


        setVisible(true);

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();

            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        buttonActiveUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(Constants.CLIENTS_ACTIVE_COMMAND);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(HWClientApp::new);
    }
}

