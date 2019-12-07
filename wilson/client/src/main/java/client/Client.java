package client;

import menus.MainMenu;
import menus.Menu;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class Client {

    private int port;
    private HashMap<Integer, Menu> mapOfMenus;
    private Prompt prompt;
    private InputStream is;
    private OutputStream out;

    public Client(int port) {
        this.prompt = new Prompt(System.in, System.out);
        this.mapOfMenus = new HashMap<>();
        this.port = port;
        initMenus();
    }

    public void start() {
        Socket socket = null;

        try {
            //Instance socket and streams
            socket = new Socket("localhost", port);
            is = socket.getInputStream();
            out = socket.getOutputStream();

            //Accept server player name request
            communicateName();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            DataOutputStream dataOut = new DataOutputStream(out);

            String message;
            while ((message = bufferedReader.readLine()) != null) {
                if(!message.equals("menu")) {
                    System.out.println(message);
                }

                if(message.equals("menu")) {
                    Menu currentMenu = mapOfMenus.get(1);

                    int actionId = currentMenu.present();
                    dataOut.writeInt(actionId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            close(is);
            close(out);
            close(socket);
        }
    }

    private void communicateName() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            printWriter = new PrintWriter(out, true);

            String serverComm = bufferedReader.readLine();

            StringInputScanner scanner = new StringInputScanner();
            scanner.setMessage(serverComm);
            scanner.setError("Error scanning name.");

            String playerName = prompt.getUserInput(scanner);

            printWriter.println(playerName);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            System.out.println("Name sent.");
        }
    }


    private void initMenus() {
        //add menus here
        MainMenu mainMenu = new MainMenu(prompt);

        mapOfMenus.put(mainMenu.getId(), mainMenu);
    }

    private void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            System.out.println("Fuck this shit I'm out...");
            e.printStackTrace();
        }
    }
}
