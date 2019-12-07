package wilsonserver.engine.actions;

import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;
import wilsonserver.utils.model.Weather;

public class ActionGatherWood extends AbstractAction {


    public ActionGatherWood(GameData gameData, Player player) {
        super(gameData, player);
    }

    @Override
    public void execute() {
        super.execute();

        if (hasActionsLeft()) {

            if (gameData.getWeather() == Weather.GOOD) {
                calculateWood(10, 10);
                return;
            }

            if (gameData.getWeather() == Weather.RAINY) {
                calculateWood(5, 5);
                return;
            }

            rest();
            System.out.println("You must rest.");
        }
    }

    private void calculateWood(int range, int min) {
        int currentWood = gameData.getWood();
        int randomizer = calculateProgress(range, min);

        gameData.setWood(currentWood + randomizer);
    }
}
