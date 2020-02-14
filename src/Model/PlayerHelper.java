package Model;

import java.io.Serializable;

/**
 * The type Player helper.
 */
public class PlayerHelper  implements Serializable{

    private String line;
    private int responseNumber;
    private Player player;
    private int[] position;
    /**
     * The Serial version uid.
     */
    static final long serialVersionUID = 1;

    /**
     * Instantiates a new Player helper.
     */
    public PlayerHelper() {

    }

    /**
     * Instantiates a new Player helper.
     *
     * @param pl     the pl
     * @param pos    the pos
     * @param ln     the ln
     * @param resNum the res num
     */
    public PlayerHelper(Player pl, int[] pos, String ln, int resNum) {
        this.player = pl;
        this.position = pos;
        this.line = ln;
        this.responseNumber = resNum;
    }

    /**
     * Gets line.
     *
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * Sets line.
     *
     * @param line the line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * Gets response number.
     *
     * @return the response number
     */
    public int getResponseNumber() {
        return responseNumber;
    }

    /**
     * Sets response number.
     *
     * @param responseNumber the response number
     */
    public void setResponseNumber(int responseNumber) {
        this.responseNumber = responseNumber;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get position int [ ].
     *
     * @return the int [ ]
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(int[] position) {
        this.position = position;
    }
}
