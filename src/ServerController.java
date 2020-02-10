import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerController implements Constants {

    private Socket aSocket, oSocket;
    private ObjectOutputStream outputStreamX, outputStreamO;
    private ObjectInputStream inputStreamX, inputStreamO;
    private ServerSocket serverSocket;
    private ExecutorService pool;


    public ServerController() {
        try {
            serverSocket = new ServerSocket(9806);
            pool = Executors.newCachedThreadPool();
            runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer() {

        try {

            aSocket = serverSocket.accept();
            outputStreamX = new ObjectOutputStream(aSocket.getOutputStream());
            inputStreamX = new ObjectInputStream(aSocket.getInputStream());

            oSocket = serverSocket.accept();
            outputStreamO = new ObjectOutputStream(oSocket.getOutputStream());
            inputStreamO = new ObjectInputStream(oSocket.getInputStream());

            Game game = new Game(outputStreamX, outputStreamO, inputStreamX, inputStreamO);
            pool.execute(game); // two players for one thread

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        new ServerController();
    }

}
