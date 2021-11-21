package lesson7.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import lesson7.constants.Constants;

public class HWClientApp extends JFrame {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private JTextArea textArea;
    private JTextField textField;
    private JButton button;

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
                    if (messageFromServer.equals("/end")) {
                        break;
                    }
                    textArea.append(messageFromServer);
                    textArea.append("\n");
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
        setSize(400, 500);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        //textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(textArea, BorderLayout.CENTER);
        final JPanel panel = new JPanel();
        add(new JScrollPane(textArea));
        panel.setLayout(new BorderLayout(0, 0));
        add(panel, BorderLayout.SOUTH);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        button = new JButton();
        button.setHorizontalAlignment(0);
        button.setMaximumSize(new Dimension(78, 100));
        button.setVerticalAlignment(0);
        button.putClientProperty("hideActionText", Boolean.FALSE);
        button.setText("Отправить");
        panel.add(button, BorderLayout.EAST);


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
        JTextField loginField = new JTextField();
        loginPanel.add(loginField);
        JTextField passField = new JTextField();
        loginPanel.add(passField);
        JButton authButton = new JButton("Авторизоваться");
        loginPanel.add(authButton);
        add(loginPanel, BorderLayout.NORTH);

        setVisible(true);

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(Constants.AUTH_COMMAND +" " + loginField.getText() + " " + passField.getText());
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
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(HWClientApp::new);
    }
}

