package wilsonserver.utils.model;

import java.util.concurrent.atomic.AtomicInteger;

public class GameData {

    public static volatile AtomicInteger CURRENT_NUMBER_OF_PLAYERS = new AtomicInteger(0);
    private int round = 1;
    private boolean daytime = true;
    private Weather weather = Weather.GOOD;
    private int wood;
    private int food;
    private int tools;
    private int boatProgress;


    public void nextPhase() {

        if (daytime) {
            daytime = false;
            System.out.println("It's nighttime.");
            return;
        }

        randomiseWeather();
        daytime = true;
        System.out.println("It's daytime.");
        this.round++;
        System.out.println(round + " days spent in the island.");
    }

    private void randomiseWeather() {
        int outcome = (int) (Math.random() * 100 + 1);

        if (outcome > 50) {
            System.out.println("Set weather to GOOD, die roll: " + outcome);
            weather = Weather.GOOD;
            return;
        }

        if (outcome > 25) {
            System.out.println("Set weather to RAINY, die roll: " + outcome);
            weather = Weather.RAINY;
            return;
        }

        System.out.println("Set weather to STORM, die roll: " + outcome);
        weather = Weather.STORM;
    }

    public boolean isDaytime() {
        return daytime;
    }

    public int getRound() {
        return round;
    }

    public int getFood() {
        return food;
    }

    public int getWood() {
        return wood;
    }

    public int getTools() {
        return tools;
    }

    public int getBoatProgress() {
        return boatProgress;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setTools(int tools) {
        this.tools = tools;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setBoatProgress(int progress) {
        this.boatProgress = progress;
    }

    public Weather getWeather() {
        return weather;
    }

    public void destroyBoat() {
        this.boatProgress = 0;
    }
}
