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
        return dailyEarnings;
    }

    public void addDailyEarnings(Double dailyEarnings) {
        this.dailyEarnings += dailyEarnings;
    }
}
