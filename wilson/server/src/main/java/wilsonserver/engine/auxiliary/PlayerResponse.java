package wilsonserver.engine.auxiliary;

import wilsonserver.engine.actions.Action;
import wilsonserver.utils.model.Player;

public class PlayerResponse {

    private Player player;
    private int actionId;

    public PlayerResponse(Player player, int actionId) {
        this.player = player;
        this.actionId = actionId;
    }

    public int getActionId() {
        return actionId;
    }

    public Player getPlayer() {
        return player;
    }
}
