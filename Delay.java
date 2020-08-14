
import java.util.Timer;
import java.util.TimerTask;

public class Delay
{
    public void delay()
    {
        TimerTask timerTask = new Reallocation();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0,6* 10*1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(6*120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(6*30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}