package lesson14.HomeWork14.ComplaintFreeWorld;

import java.util.Timer;
import java.util.TimerTask;

public class TimerReplace {
    Timer timer;
    StopTask stopTask;


    public TimerReplace(int seconds) {
        timer = new Timer();
           timer.schedule(new StopTask(), seconds * 1000L);

    }

    public void TimerStop(){
        stopTask.run();
    }



/*    public static void main(String[] args) {
        start = System.currentTimeMillis();
        new TimerReplace(10);
        System.out.println("StopWatch Started.");
    }*/

/*    public class StopTask extends TimerTask {
        public void run() {
            System.out.println("Time Up!");
            System.out.println("t1 = " + (System.currentTimeMillis() - start));
            timer.cancel();
        }
    }*/

}
