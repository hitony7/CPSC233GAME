/**
 * GameConfiguration sets up the initial Connect4 board, accessors and mutators.
 *
 * @author T02-1 - Tony Wong
 * @version 1.0
 */
public class GameConfiguration {

	/**
	* Main method for testing the Move class implementation.
	*/

	/**
	 * board declares the Connect4 board.
	 */
	public char[][] board;

	/**
	 * ROW is the amount of rows in the Connect4 board.
	 */
	public final int ROW = 6;

	/**
	 * COL is the amount of columns in the Connect4 board.
	 */
	public final int COL = 7;

	/**
	 * PP1 is the token the first player uses in the Connect4 board.
	 */
	public final char PP1 = 'x';

	/**
	 * PP2 is the token the second player or computer uses in the Connect4 board.
	 */
	public final char PP2 = 'o';

	/**
	 * FS represents a free space in the  Connect4 board.
	 */
	public final char FS = '_';

	/**
	 * Default constructor for the GameConfiguration class.
	 */
	public GameConfiguration() {
		board = new char[this.ROW][this.COL];
		init();
	}

	/**
 	 * init fills the board with free spaces.
 	 */
	private void init() {
		for (int i = 0; i < (this.ROW); i++) {
			for (int j = 0; j < (this.COL); j++) {
				setToken(FS, i, j);
			}
		}
	}

	/**
	 * getToken returns the token at a specific board location.
	 *
	 * @return char, token at the x, y location in the Connect4 board array.
	 */
	public char getToken(int x, int y) {
		return board[x][y];
	}

	/**
	 * setToken changes the token at a specific board location.
	 *
	 * @param tokenType, x, y, the current token, the current row, and the current column.
	 */
	public void setToken(char tokenType, int x, int y) {
		board[x][y] = tokenType;
	}
	

	/**
	 * draw makes the output of the Connect4 board in the text based interface.
	 */
	public void draw() {
		for (int i = 0; i < (this.ROW); i++) {
			for (int j = 0; j < (this.COL); j++) {
				System.out.print("|" + board[i][j] + "|");
			}
			System.out.println("");
		}
	}

}
