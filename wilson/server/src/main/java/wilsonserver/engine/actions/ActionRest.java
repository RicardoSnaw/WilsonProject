package wilsonserver.engine.actions;

import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;
import wilsonserver.utils.model.Weather;

public class ActionRest extends AbstractAction {


    public ActionRest(GameData gameData, Player player) {
        super(gameData, player);
    }

    @Override
    public void execute() {
        rest();
    }
}
