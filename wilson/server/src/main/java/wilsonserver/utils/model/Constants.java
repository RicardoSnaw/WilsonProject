package wilsonserver.utils.model;

public class Constants {

    public static final double SUNNY_WEATHER_MULTIPLIER = 1.5;
    public static final double GOOD_WEATHER_MULTIPLIER = 1.2;
    public static final double RAINY_WEATHER_MULTIPLIER = 1;
    public static final double WINDY_WEATHER_MULTIPLIER = 0.8;
    public static final double STORM_WEATHER_MULTIPLIER = 0.4;
    public static final int BASE_PLAYER_HEALTH = 100;
    public static final int PORT_NUMBER = 64000;
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final String DIALOGUE = "You and your friends were traveling to Italy. Somehow the pilot lost\n" +
            "control of the plane and it crashed on the ocean. You and your friends were able to swim to a nearby\n" +
            "island and now you must escape. Don't forget that teamwork is very important!\n" +
            "Your objective is to escape the island! GOOD LUCK!";
    public static final int BASE_BOAT_WOOD_COST = 50;
    public static final int BASE_BOAT_TOOLS_COST = 50;
    public static final int BASE_TOOLS_WOOD_COST = 50;
    public static final int[] BOAT_TIERS = {50, 100, 150, 200, 250};
    public static final int PLAYER_MAX_ACTIONS = 2;
}
