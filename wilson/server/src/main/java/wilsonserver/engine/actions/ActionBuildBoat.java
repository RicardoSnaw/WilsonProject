package wilsonserver.engine.actions;

import wilsonserver.utils.model.Constants;
import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;
import wilsonserver.utils.model.Weather;

public class ActionBuildBoat extends AbstractAction {

    public ActionBuildBoat(GameData gameData, Player player) {
        super(gameData, player);
    }

    @Override
    public void execute() {
        super.execute();

        if (hasActionsLeft()) {

            if (gameData.getWood() < Constants.BASE_BOAT_WOOD_COST && gameData.getTools() < Constants.BASE_BOAT_TOOLS_COST) {
                System.out.println("You dont have enough materials/tools.");
                return;
            }

            if (gameData.getWeather() == Weather.GOOD) {
                calculateBoatProgress(1, 10);
                return;
            }

            rest();
            System.out.println("You can't build with this weather.");
        }
    }

    private void calculateBoatProgress(int range, int min) {
        int currentBoatProgress = gameData.getBoatProgress();
        int randomizer = calculateProgress(range, min);

        gameData.setBoatProgress(currentBoatProgress + randomizer);
    }
}
