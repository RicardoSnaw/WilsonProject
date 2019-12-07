package wilsonserver.utils.model;

import wilsonserver.utils.Messages;

public enum Weather {
    SUNNY(Constants.SUNNY_WEATHER_MULTIPLIER, Messages.SUNNY_MESSAGE),
    GOOD(Constants.GOOD_WEATHER_MULTIPLIER, Messages.GOOD_MESSAGE),
    RAINY(Constants.RAINY_WEATHER_MULTIPLIER, Messages.RAINY_MESSAGE),
    WINDY(Constants.WINDY_WEATHER_MULTIPLIER, Messages.WINDY_MESSAGE),
    STORM(Constants.STORM_WEATHER_MULTIPLIER, Messages.STORM_MESSAGE);

    private String name;
    private double multiplier;

    Weather(double multiplier, String name) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public String getName() {
        return name;
    }
}

