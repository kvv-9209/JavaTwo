package lesson14.HomeWork14.ComplaintFreeWorld;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerFreeWorld {


    @FXML
    private Label tickCount;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonRight;
    @FXML
    private Button buttonLeft;

    private TimerReplace timer;


/*    public ControllerFreeWorld() {
*//*        try {
           // OpenConnection();o
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
    }*/

    private void start() {
        for (int i = 0; i < 21; i++) {
            tickCount.setText(String.valueOf(i));
            timer = new TimerReplace(10);
        }
    }

    @FXML
    public void onClickStart() {
        start();
    }

    @FXML
    public void onClickRight() {
        timer.TimerStop();
        start();
    }

    @FXML
    public void onClickLeft() {
        timer.TimerStop();
        start();
    }
}
