package wilsonserver;

import wilsonserver.engine.Engine;
import wilsonserver.engine.auxiliary.PlayerResponse;
import wilsonserver.utils.Messages;
import wilsonserver.utils.model.Constants;
import wilsonserver.utils.model.Player;

import java.io.*;
import java.net.Socket;

public class PlayerHandler implements Runnable {

    private Player player;
    private Engine engine;
    private InputStream inputStream;
    private OutputStream outputStream;
    private volatile boolean waiting = true;

    public PlayerHandler(Socket playerConnection, Engine engine) {
        this.engine = engine;
        initStreams(playerConnection);
    }

    @Override
    public void run() {
        askPlayerName();
        DataInputStream dataIn = new DataInputStream(inputStream);

        try {
            message(Constants.DIALOGUE);

            while (true) {

                if (!canAct()) {
                    sleep(100);
                    continue;
                }


                getWriter().println("menu");

                int actionId = dataIn.readInt();

                //Instance player response as make engine run it
                PlayerResponse playerResponse = new PlayerResponse(player, actionId);
                engine.addPlayerResponse(playerResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Player finished");
        }
    }

    private boolean canAct() {
        return !engine.haveIResponded();
    }

    public void askPlayerName() {
        try {
            //Players chooses a name
            getWriter().println("SERVER: Choose a name: ");
            player = new Player(getReader().readLine());
            engine.incrementPlayers();

            //After choosing name
            System.out.println(player.getName() + " entered lobby.");

            //Enters lobby
            lobby();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This "menu" will be the player waiting room and lobby status feedback.
    private void lobby() {
        // TODO : ADD INFO OF NUMBER OF PLAYERS IN LOBBY.
        //int numberOfPlayers = playersList.size();

        getWriter().println("SERVER: " + Messages.WAITING_FOR_PLAYERS);

        while (waiting) {
            sleep(1000);

            if (engine.getPlayerNumber() == Constants.NUMBER_OF_PLAYERS) {
                waiting = false;
            }
        }
        getWriter().println("SERVER: Exiting lobby");
    }

    public Player getPlayer() {
        return player;
    }

    private void initStreams(Socket playerConnection) {
        try {
            inputStream = playerConnection.getInputStream();
            outputStream = playerConnection.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader getReader() {
        BufferedReader playerInput = new BufferedReader(new InputStreamReader(inputStream));
        return playerInput;
    }

    private PrintWriter getWriter() {
        PrintWriter playerOutput = new PrintWriter(outputStream, true);
        return playerOutput;
    }

    public void message(String message) {
        getWriter().println(message);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
