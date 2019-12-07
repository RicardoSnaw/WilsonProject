package wilsonserver.engine.actions;

import wilsonserver.utils.model.Constants;
import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;
import wilsonserver.utils.model.Weather;

public class ActionBuildTools extends AbstractAction {

    public ActionBuildTools(GameData gameData, Player player) {
        super(gameData, player);
    }

    @Override
    public void execute() {
        super.execute();

        if (hasActionsLeft()) {

            if (gameData.getWood() < Constants.BASE_TOOLS_WOOD_COST) {
                System.out.println("You dont have enough wood.");
                return;
            }

            if (gameData.getWeather() == Weather.GOOD) {
                calculateTools(10, 10);
                return;
            }

            rest();
            System.out.println("You can't build with this weather.");
        }
    }

    private void calculateTools(int range, int min) {
        int currentTools = gameData.getTools();
        int randomizer = calculateProgress(range, min);

        gameData.setTools(currentTools + randomizer);
    }
}
