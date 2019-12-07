package wilsonserver.engine.actions;

import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;
import wilsonserver.utils.model.Weather;

public class ActionGatherFood extends AbstractAction {


    public ActionGatherFood(GameData gameData, Player player) {
        super(gameData, player);
    }

    @Override
    public void execute() {
        super.execute();

        if (hasActionsLeft()) {

            if (gameData.getWeather() == Weather.GOOD) {
                calculateFood(2, 1);
                return;
            }

            if (gameData.getWeather() == Weather.RAINY) {
                calculateFood(1, 0);
                return;
            }

            rest();
            System.out.println("You must rest.");
        }
    }

    private void calculateFood(int range, int min) {
        int currentFood = gameData.getFood();
        int randomizer = calculateProgress(range, min);

        gameData.setFood(currentFood + randomizer);
    }
}
