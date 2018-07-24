/**
 * Move takes the player's or computer's desired column for a token and places it correctly in the Connect4 game board.
 *
 * @author T02-1 - Matthew Cox
 * @version 1.0
 */
public class Move {

	/**
	* Main method for testing the Move class implementation.
	*/
	public static void main(String[] args) {

		GameConfiguration c4 = new GameConfiguration(); //Easy setup of a blank board.
		Move m4 = new Move(); //New Move object for testing.

		/**
		 * Empty column test.
		 */
		System.out.println("Testing a move in an empty column.\nBefore,");
		c4.draw(); //Draws the empty board.
		System.out.println(m4.getMove(c4.board, 6, 'o')); //Tests if a move can be made in a completely free column, should print true.
		System.out.println("After,");
		c4.draw(); //Draws the board after placing 'o' in the far right column.

		/**
		 * Half-full column test.
		 */
		System.out.println("Testing a move in a column that already has tokens in it.\nBefore,");
		for (int row = 5; row > 2; row --) {	//Fills the far left column with three x's in the bottom two rows.
			c4.board[row][0] = 'x';
		}
		c4.draw(); //Draws the board before placing 'o' in the far left column above the three x's.
		System.out.println(m4.getMove(c4.board, 0, 'o')); //Tets if a move can be made in half full column, should print true.
		System.out.println("After,");
		c4.draw(); //Draws the board after placing 'o' in the far left column, should be in the fourth row from the bottom.

		/**
		 * Full column test.
		 */
		System.out.println("Testing a move in a completely full column.\nBefore,");
		for (int row = 0; row < 6; row ++) { //Fills the middle column full of x's.
			c4.board[row][3] = 'x';
		}
		c4.draw(); //Draws the board after filling the middle column.
		System.out.println(m4.getMove(c4.board, 3, 'o')); //Tests if a move can be made on the full middle column, should print false.
		System.out.println("After,");
		c4.draw(); //Draws the board after trying to place in full column, should look the same as above.
	}

	/**
	 * moveMade stores whether or not a move can be made on the Connect4 board.
	 */
	public boolean moveMade;

	/**
	 * Default coonstructor for the Move class.
	 */
	public Move() {
		moveMade = true; //Assumes the move is able to be made since this should only be used to initialize a Move object at the start of the game.
	}

	/**
	 * getMove checks whether a legal connect four move is possible for the current player and then places their token where they choose.
	 *
	 * @param board, token the current Connect4 board.
	 * @param column, the chosen column for the token.
	 * @param token, the token of a human or computer player.
	 * @return boolean, returns false if it cannot make a move, true if it can place a token in the given column at a specific row.
	 */
	public boolean getMove(char[][] board, int column, char token) {

		if (board[0][column] != '_') {  //Checks if top row for column is free or not
			moveMade = false;
			return moveMade;
		}

		for (int row = 0; row < 6; ++ row) {
			if (board[row][column] != '_') { //Loop iterates until it sees another token occupying a row
				board[row - 1][column] = token;  //Places the token in the free space above the other token it just found
				moveMade = true;
				return moveMade;
			}
		}

		board[5][column] = token; //Places a token at the bottom of the board since all the spaces in the column were free
		moveMade = true;
		return moveMade;
	}

}