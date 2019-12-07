package wilsonserver.engine;


import wilsonserver.engine.actions.*;
import wilsonserver.engine.auxiliary.PlayerResponse;
import wilsonserver.utils.model.Constants;
import wilsonserver.utils.model.GameData;
import wilsonserver.utils.model.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Engine implements Runnable {

    private HashMap<Integer, Action> actionMap;
    private GameData gameData;
    private BlockingQueue<PlayerResponse> responseQueue;
    private volatile HashSet<String> threadsResponded;

    public Engine() {
        this.threadsResponded = new HashSet<>();
        this.actionMap = new HashMap<>();
        this.responseQueue = new LinkedBlockingDeque<>();
        this.gameData = new GameData();
    }

    @Override
    public void run() {
        if (responseQueue.size() == Constants.NUMBER_OF_PLAYERS) {
            System.out.println("All players sent an action.");

            executeResponses();
            gameData.nextPhase();
        }

        sleep(500);

        //Loop
        run();
    }

    public synchronized void addPlayerResponse(PlayerResponse playerResponse) {
        responseQueue.add(playerResponse);
        threadsResponded.add(Thread.currentThread().getName());
    }

    /**
     * @param playerResponse in order to be executed:
     *                       - Uses gamedata to execute actions from players
     */
    private void executePlayerResponse(PlayerResponse playerResponse) {
        populateActionMap(playerResponse.getPlayer());
        actionMap.get(playerResponse.getActionId()).execute();
    }

    private void populateActionMap(Player player) {
        actionMap.put(1, new ActionGatherFood(gameData, player));
        actionMap.put(2, new ActionGatherWood(gameData, player));
        actionMap.put(3, new ActionBuildTools(gameData, player));
        actionMap.put(4, new ActionBuildBoat(gameData, player));
        actionMap.put(5, new ActionRest(gameData, player));
        actionMap.put(6, new ActionEscape(gameData, player));
    }

    public synchronized boolean haveIResponded() {
        return threadsResponded.contains(Thread.currentThread().getName());
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeResponses() {
        while (!responseQueue.isEmpty()) {
            executePlayerResponse(responseQueue.poll());
        }
        threadsResponded.clear();
    }

    public synchronized int incrementPlayers() {
        return GameData.CURRENT_NUMBER_OF_PLAYERS.incrementAndGet();
    }

    public synchronized int getPlayerNumber() {
        return GameData.CURRENT_NUMBER_OF_PLAYERS.get();
    }

}
