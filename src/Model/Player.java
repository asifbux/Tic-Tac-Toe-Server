package Model;

import java.io.Serializable;

/**
 * Provides data members and methods to create Player class of 'X' and 'O'.
 * 
 * @author Vaibhav V. Jadhav
 * @version 1.0
 * @since October 03, 2019
 */
public class Player implements Serializable {
	
	/** The name of the Player. */
	private String name;
	
	/** The board of type Board. */
	private Board board;
	
	/** The opponent player containing Player object. */
	private Player opponent;
	
	/** The mark character either 'X' or 'O'. */
	private char mark;

	static final long serialVersionUID = 1;
	
	/**
	 * Instantiates a new player by taking name of player and their mark.
	 *
	 * @param name the name of the player
	 * @param letter the mark of the player
	 */
	public Player(String name, char letter) {
		this.name = name;
		this.mark = letter;
	}
	
	/**
	 * Play method makes the move on the board of both player and checks for the winner or tie.
	 */
	/**
	 * Gets the name of player.
	 *
	 * @return the name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of player.
	 *
	 * @param name the new name of player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/**
	 * Gets the opponent player.
	 *
	 * @return the opponent player
	 */
	public Player getOpponent() {
		return opponent;
	}

	/**
	 * Sets the opponent player.
	 *
	 * @param opponent the new opponent player
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	/**
	 * Gets the mark either 'X' or 'O'.
	 *
	 * @return the mark
	 */
	public char getMark() {
		return mark;
	}

	/**
	 * Sets the mark either 'X' or 'O'.
	 *
	 * @param mark the new mark
	 */
	public void setMark(char mark) {
		this.mark = mark;
	}

}
