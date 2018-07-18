/**
 * Ryan's Class
 * move token to proper places in array
 *
 */
public class Move {

	/**
	 * makeMove checks whether a legal connect four move is possible for the current player and then places their token where they choose.
	 *
	 * @return boolean returns false if it cannot make a move, true if it can place a token in the given column.
	 */
	public static boolean makeMove(int[][] board, int column, char token) {

		if (board[0][column] != '_') {  //Checks if top row for column is free or not
			return false;
		}
		for (int row = 0; row < 7; ++ row) {
			if (board[row][column] != '_') { //Loop iterates until it sees another token occupying a row
				field[row-1][column] = token;  //Places the token in the free space above the other token it just found
				return true;
			}
		}

		board[6][column] = token; //Places a token at the bottom of the board since all the spaces in the column were free
		return true;
	}

}