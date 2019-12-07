package wilsonserver.engine.actions;

import wilsonserver.utils.model.Constants;
import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;
import wilsonserver.utils.model.Weather;

public class ActionEscape extends AbstractAction {

    public ActionEscape(GameData gameData, Player player) {
        super(gameData, player);
    }

    @Override
    public void execute() {
        super.execute();

        if (hasActionsLeft()) {

            if (gameData.getBoatProgress() < Constants.BOAT_TIERS[0]) {
                System.out.println("The boat is not ready to sail.");
                return;
            }

            if (gameData.getWeather() == Weather.GOOD) {
                attemptEscape();
                return;
            }

            rest();
            System.out.println("You can't escape in this weather.");
        }
    }

    private void attemptEscape() {

        int escapeChance = 0;
        int randomChance = (int) Math.random() * 100 + 1;

        if (gameData.getBoatProgress() >= Constants.BOAT_TIERS[0] && gameData.getBoatProgress() < Constants.BOAT_TIERS[1]) {
            escapeChance = 10;
        }
        if (gameData.getBoatProgress() >= Constants.BOAT_TIERS[1] && gameData.getBoatProgress() < Constants.BOAT_TIERS[2]) {
            escapeChance = 20;
        }
        if (gameData.getBoatProgress() >= Constants.BOAT_TIERS[2] && gameData.getBoatProgress() < Constants.BOAT_TIERS[3]) {
            escapeChance = 30;
        }
        if (gameData.getBoatProgress() >= Constants.BOAT_TIERS[3] && gameData.getBoatProgress() < Constants.BOAT_TIERS[4]) {
            escapeChance = 40;
        }
        if (gameData.getBoatProgress() >= Constants.BOAT_TIERS[4] && gameData.getBoatProgress() < Constants.BOAT_TIERS[5]) {
            escapeChance = 50;
        }

        gameData.setTools(0);
        gameData.setWood(0);
        gameData.setFood(0);
        gameData.destroyBoat();

        if (escapeChance < randomChance) {
            player.setHealth(0);
            System.out.println("Somebody died, you lost the boat and all the materials/tools.");
            return;
        }

        player.setEscaped();
        System.out.println(player.getName().toUpperCase() + " ESCAPED!");

    }

}
