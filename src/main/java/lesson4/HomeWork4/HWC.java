package lesson4.HomeWork4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HWC extends JFrame {


    private JTextArea textArea;
    private JTextField textField;
    private JButton button;


    public HWC() throws HeadlessException, IOException {

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
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

        setTitle("HWChat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea.getText() != "") {
                    textArea.append(textField.getText() + "\n");
                    textField.setText("");
                }
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append(textField.getText() + "\n");
                textField.setText("");
            }
        });

    }


    public static void main(String[] args) throws IOException {

        new HWC();

    }

}

