package Controller;

import Model.Constants;
import Model.Game;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Server controller.
 */
public class ServerController implements Constants {

    private Socket aSocket, oSocket;
    private ObjectOutputStream outputStreamX, outputStreamO;
    private ObjectInputStream inputStreamX, inputStreamO;
    private ServerSocket serverSocket;
    private ExecutorService pool;

    /**
     * Instantiates a new Server controller.
     */
    public ServerController() {
        try {
            serverSocket = new ServerSocket(9806);
            pool = Executors.newFixedThreadPool(10);
            runServer();
        } catch (IOException e) {
            System.err.println("Could not listen to port");
            System.exit(-1);
        }
    }

    /**
     * Run server.
     */
    public void runServer() {
        try {
            while(true) {
                aSocket = serverSocket.accept();
                outputStreamX = new ObjectOutputStream(aSocket.getOutputStream());
                inputStreamX = new ObjectInputStream(aSocket.getInputStream());

                oSocket = serverSocket.accept();
                outputStreamO = new ObjectOutputStream(oSocket.getOutputStream());
                inputStreamO = new ObjectInputStream(oSocket.getInputStream());

                Game game = new Game(outputStreamX, outputStreamO, inputStreamX, inputStreamO);
                pool.execute(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {

        new ServerController();
    }

}
