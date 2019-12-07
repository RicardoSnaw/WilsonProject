package wilsonserver;

import wilsonserver.engine.Engine;
import wilsonserver.utils.model.Constants;
import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ExecutorService threadPool;
    private LinkedList<PlayerHandler> playersList;

    public Server() {
        this.threadPool = Executors.newCachedThreadPool();
        this.playersList = new LinkedList<>();
    }

    public void listen() {
        ServerSocket serverSocket = null;

        threadPool.execute(new BroadcastMessage(playersList));
        Engine gameEngine = new Engine();
        threadPool.execute(gameEngine);

        try {
            serverSocket = new ServerSocket(Constants.PORT_NUMBER);

            while (gameEngine.getPlayerNumber() < Constants.NUMBER_OF_PLAYERS) {

                Socket playerConnection = acceptPlayerConnection(serverSocket);

                PlayerHandler playerHandler = new PlayerHandler(playerConnection, gameEngine);
                playersList.add(playerHandler);
                threadPool.execute(playerHandler);
                Thread.sleep(5000);
            }

            runGame();

            gameEngine.run();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            close(serverSocket);
        }
    }

    //Accepts new connections to the server.
    private Socket acceptPlayerConnection(ServerSocket serverSocket) {
        Socket playerConnection = null;
        try {
            playerConnection = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            acceptPlayerConnection(serverSocket);
        }
        return playerConnection;
    }

    //Closes a socket.
    private void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runGame() throws InterruptedException {

        for (PlayerHandler player : playersList) {

            while (player.getPlayer() == null) {
                Thread.sleep(500);
            }
        }
    }

}
