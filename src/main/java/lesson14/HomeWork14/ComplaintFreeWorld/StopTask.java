package lesson14.HomeWork14.ComplaintFreeWorld;

import java.util.Timer;
import java.util.TimerTask;

public class StopTask extends TimerTask {
    Timer timer;

    public void run() {
        System.out.println("Time Up!");
        timer.cancel();
    }
}
