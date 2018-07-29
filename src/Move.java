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