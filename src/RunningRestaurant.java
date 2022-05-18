import Menu.Dish;

import java.util.Timer;
import java.util.TimerTask;


public class RunningRestaurant extends Thread{
    private Double dailyEarnings;

    public RunningRestaurant() {
        this.dailyEarnings = 0d;
    }

    @Override
    public void run() {

    }
    public Double getDailyEarnings() {
        return Math.round(dailyEarnings*100)/100D;
    }

    public void addDailyEarnings(Double dailyEarnings) {
        this.dailyEarnings += Math.round(dailyEarnings*100)/100D;
    }
}
