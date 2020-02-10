import java.io.Serializable;

public class PlayerHelper  implements Serializable{

    private String line;
    private int responseNumber;
    private Player player;
    private int[] position;
    static final long serialVersionUID = 1;

    public PlayerHelper() {

    }

    public PlayerHelper(Player pl, int[] pos, String ln, int resNum) {
        this.player = pl;
        this.position = pos;
        this.line = ln;
        this.responseNumber = resNum;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getResponseNumber() {
        return responseNumber;
    }

    public void setResponseNumber(int responseNumber) {
        this.responseNumber = responseNumber;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
