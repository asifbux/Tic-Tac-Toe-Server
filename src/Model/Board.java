package Model;


import java.io.Serializable;

/**
 * Provides data members and methods to create a board game, Tic-Tac-Toe in java application.
 * 
 * The purpose of class Board is to create 2D char array where 2 users place a mark either 'X' or 'O'
 * and check winner or the tie. 
 * 
 * @author Vaibhav V. Jadhav
 * @version 1.0
 * @since October 03, 2019
 */
public class Board implements Constants, Serializable {
	
	/** The board with 2D char array */
	private char theBoard[][];
	
	/** The mark count which holds count of marks*/
	private int markCount;

	static final long serialVersionUID = 1;

	/**
	 * Instantiates a new board. sets markCount to 0 and initializes theBoard with 2D char array with
	 * row length 3 and adds empty space in array.
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = Constants.SPACE_CHAR;
		}
	}

	/**
	 * Gets the mark either 'X' or 'O'.
	 *
	 * @param row the row of theBoard
	 * @param col the column of theBoard
	 * @return the mark of theBoard
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Checks if theBoard is full.
	 *
	 * @return true, if markCount is 9
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * X wins checks if player X is winner by comparing it with 1.
	 *
	 * @return true, if player X is 1
	 */
	public boolean xWins() {
		if (checkWinner(Constants.LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * O wins check if player O is winner by comparing it with 1.
	 *
	 * @return true, if player O is 1
	 */
	public boolean oWins() {
		if (checkWinner(Constants.LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Adds the mark on the Board by taking row, column and mark as arguments. After placing mark on 
	 * given row and column, it increments the markCount by 1.
	 *
	 * @param row the row of the Board
	 * @param col the column of the Board
	 * @param mark the mark either 'X' or 'O'
	 */
	public void addMark(int row, int col, char mark) {
		//TODO: check if user has already entered mark then don't allow to re-write on it
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clear basically adds empty spaces on whole Board and resets markCount to 0.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = Constants.SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Check winner gives either X or O is winner. 
	 *
	 * @param mark the mark either 'X' or 'O'
	 * @return the int value of result as 1 for win and 0 for loss
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

}
