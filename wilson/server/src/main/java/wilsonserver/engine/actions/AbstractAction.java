package wilsonserver.engine.actions;

import wilsonserver.utils.model.Constants;
import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;

public class AbstractAction implements Action {

    Player player;
    GameData gameData;
    private boolean actionsLeft;

    AbstractAction(GameData gameData, Player player) {
        this.gameData = gameData;
        this.player = player;
    }

    @Override
    public void execute() {
        if (player.getActions() <= 0) {
            actionsLeft = false;

            // debug log
            System.out.println(player.getName() + " has no actions left.");
            return;
        }
        actionsLeft = true;
        player.useAction();
    }

    void rest() {
        player.setActions(Constants.PLAYER_MAX_ACTIONS);
    }

    boolean hasActionsLeft() {
        return actionsLeft;
    }

    int calculateProgress(int range, int min) {
        return (int) (Math.random() * range + min);
    }
}
