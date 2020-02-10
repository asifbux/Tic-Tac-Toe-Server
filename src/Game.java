import java.io.*;
import java.util.concurrent.TimeUnit;

public class Game implements Constants, Runnable {

    private PlayerHelper xPlayerHelper, oPlayerHelper;
    private Board theBoard;
    private ObjectOutputStream outputStreamX, outputStreamO;
    private ObjectInputStream inputStreamX, inputStreamO;

    public Game(ObjectOutputStream outputStreamX,  ObjectOutputStream outputStreamO,
                ObjectInputStream inputStreamX, ObjectInputStream inputStreamO) throws IOException, ClassNotFoundException {
        theBoard  = new Board();
        this.outputStreamX = outputStreamX;
        this.outputStreamO = outputStreamO;
        this.inputStreamX = inputStreamX;
        this.inputStreamO = inputStreamO;

        try {
            xPlayerHelper = (PlayerHelper) inputStreamX.readObject();
            oPlayerHelper = (PlayerHelper) inputStreamO.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        setupTheGame();
    }

    public void setupTheGame() throws IOException, ClassNotFoundException {
        xPlayerHelper.getPlayer().setBoard(theBoard);
        oPlayerHelper.getPlayer().setBoard(theBoard);
        xPlayerHelper.getPlayer().setOpponent(oPlayerHelper.getPlayer().getOpponent());
        oPlayerHelper.getPlayer().setOpponent(xPlayerHelper.getPlayer().getOpponent());
        xPlayerHelper.getPlayer().setMark(LETTER_X);
        oPlayerHelper.getPlayer().setMark(LETTER_O);
        startTheGame();
    }

    public void startTheGame() throws IOException, ClassNotFoundException {
        sendObjectX(1, "Two players have joined the game. Game has started");
        sendObjectO(1, "Two players have joined the game. Game has started");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendObjectO(2, "");
        sendObjectX(2, "");
        makeMove();

    }
    public void makeMove() throws IOException, ClassNotFoundException {
        while(true) {
            if(xMove()) {};
            if(checkMove()) {};
            if(oMove()) {};
            if(checkMove()) {};
        }
    }

    public boolean xMove() throws IOException, ClassNotFoundException {
        boolean xTurn = true;
        while(xTurn) {
            sendObjectXO(3, xPlayerHelper.getPlayer().getName() + " your turn",
                    4, "Please wait X is making the move");
            xPlayerHelper = (PlayerHelper) inputStreamX.readObject();

            if (validateMove(xPlayerHelper)) {  // it to will check if the mark can be made other it is invalid
                sendObjectX(6, "Please Wait, O is making the move");
                theBoard.addMark(xPlayerHelper.getPosition()[0], xPlayerHelper.getPosition()[1], xPlayerHelper.getPlayer().getMark());

                oPlayerHelper.setPosition(xPlayerHelper.getPosition());
                sendObjectO(10, "");
                xTurn = false;
            }
            else { // now tell the client your mark is not valid make another move
                sendObjectX(7, "Invalid Move " + xPlayerHelper.getPlayer().getName() + ", your turn again");
            }
        }
        return true;
    }

    public boolean oMove() throws IOException, ClassNotFoundException {
        boolean oTurn = true;
        while (oTurn) {
            sendObjectXO(4, "Please wait O is making the move",
                    3, oPlayerHelper.getPlayer().getName() + " your turn");
            oPlayerHelper = (PlayerHelper) inputStreamO.readObject();

            if (validateMove(oPlayerHelper)) { // it to will check if the mark can be made other it is invalid
                sendObjectO(6, "Please Wait, X is making the move");
                theBoard.addMark(oPlayerHelper.getPosition()[0], oPlayerHelper.getPosition()[1], oPlayerHelper.getPlayer().getMark());

                xPlayerHelper.setPosition(oPlayerHelper.getPosition());
                sendObjectX(11, "");
                oTurn = false;
            }
            else { // now tell the client your mark is not valid make another move
                sendObjectX(7, "Invalid Move " + oPlayerHelper.getPlayer().getName() + ", your turn again");
            }
        }
        return true;
    }


    public boolean checkMove() throws IOException {
        if (theBoard.xWins()) { // Returns True if there is any tie
            sendObjectXO(9, xPlayerHelper.getPlayer().getName() +" you are the Winner!",
                    12,oPlayerHelper.getPlayer().getName() +" you lost the Game!");
        }
        else if(theBoard.oWins()) {
            sendObjectXO(12, xPlayerHelper.getPlayer().getName() +" you lost the Game!",
                    9, oPlayerHelper.getPlayer().getName() +" you are the Winner!");

        }
        else if(theBoard.isFull()) {
            sendObjectXO(8, "The game has ended. It's a tie!",
                    8, "The game has ended. It's a tie!");
        }
        return true;
    }

    public boolean validateMove(PlayerHelper aPlayerHelper) {
        if(theBoard.getMark(aPlayerHelper.getPosition()[0],aPlayerHelper.getPosition()[1]) == SPACE_CHAR)
            return true;
        return false;
    }

    public void sendObject(ObjectOutputStream outputStreamA, PlayerHelper aPlayerHelper, int responseNumber, String message) {
        aPlayerHelper = new PlayerHelper(aPlayerHelper.getPlayer(), aPlayerHelper.getPosition(), null, 0);
        aPlayerHelper.setResponseNumber(responseNumber);
        aPlayerHelper.setLine(message);
        try {
            outputStreamA.writeObject(aPlayerHelper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObjectX(int responseNumber, String message) {
        sendObject(outputStreamX, xPlayerHelper, responseNumber, message);
    }

    public void sendObjectO(int responseNumber, String message) {
        sendObject(outputStreamO, oPlayerHelper, responseNumber, message);
    }

    public void sendObjectXO(int xResponseNumber, String xMessage, int oResponseNumber, String oMessage) {
        sendObjectX(xResponseNumber, xMessage);
        sendObjectO(oResponseNumber, oMessage);
    }

    @Override
    public void run() {

    }
}
