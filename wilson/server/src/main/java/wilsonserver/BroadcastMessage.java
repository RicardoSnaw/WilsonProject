package wilsonserver;

import java.util.LinkedList;

public class BroadcastMessage implements Runnable {

    private LinkedList<PlayerHandler> playersList;

    public BroadcastMessage(LinkedList playersList) {
        this.playersList = playersList;
    }

    @Override
    public void run() {

        for(PlayerHandler player : playersList) {
            player.message("BroadcastServer functional.");
        }

    }
}
