/**
 * ComputerPlayer utilizes a minmax variant algorithm called Negamax to chose which column it should optimally drop it's token into.
 *
 * <p>Utlizes a blocking method to prevent the user from winning, as well as a negamax algorithm and a board evaluation method
 * to help choose it's next move (https://en.wikipedia.org/wiki/Negamax). Player's can pick the 'depth' or a pseudo difficulty
 * for which the algorithm uses to call itself recursively for the best evaluation. Modified logic from https://bit.ly/2LyDRth.
 *
 * @author T02-1 - Matthew Cox
 * @version 1.1
 */
import java.util.Arrays;

public class ComputerPlayer {

	/**
	 * maxDepth stores the depth that the negamax method searches to.
	 *
	 * <p>Essentially the maximum amount of times negamax will call itself to find the best column.
	 */
	private int maxDepth;

	/**
	 * c4Board stores the current configuration of the connect4 game state.
	 */
  private char[][] c4Board;

  /**
   * ComputerPlayer constructor that takes in the connect4 board and a search depth.
   */
	public ComputerPlayer(char[][] board, int depth) {
    this.c4Board = board;
    this.maxDepth = depth;
	}

	/**
   * Copy Constructor for the connect4 board.
   */
	private char[][] copyBoard(char[][] boardCopy) {
    if (boardCopy == null) {
      return null;
    }

    char[][] newBoard = new char [boardCopy.length][];

		for (int row = 0; row < boardCopy.length; row ++) {
      newBoard[row] = Arrays.copyOf(boardCopy[row], boardCopy[row].length);
    }

    return newBoard;
	}

  /**
   * calcValue figures out what column the computer wants to play their token in.
   */
	public int calcValue(char[][] board) {

		Move move = new Move();
		Winner winner = new Winner();

    int count = 0; // Tracks how many moves have been made so far.
		char[][] boardCopy = copyBoard(board);

		// Counts the amount of move the user has done.
		for (int row = 0; row < 6; row ++) {
      for (int col = 0; col < 7; col ++) {
        if (c4Board[row][col] == 'x') {
          count ++;
        }
      }
    }


		// The ComputerPlayer needs some handholding before relying on negamax since the board is too empty for evaluation to work properly.
		if (count == 0 || count == 1) { // Ensures on that the computer plays in the center column on it's first turn (the best possible move going first or second).
        return 3;
		} else if (count == 2) {
			if (c4Board[5][3] == 'x' && c4Board[5][4] == 'x') {
				return 5;
			} else if (c4Board[5][3] == 'x' && c4Board[5][2] == 'x') {
				return 1;
			} else if (c4Board[5][3] == 'x' && c4Board[5][5] == 'x') {
				return 4;
			} else if (c4Board[5][3] == 'x' && c4Board[5][1] == 'x') {
				return 2;
			}
		}

		// Retruns the negamax column location if it wasn't the ComputerPlayer's first or second turn.
		return negamax(c4Board, -1000000, 0, 1);

  }

	/**
	 * negamax is a recursive method set up to evaluate a human or computer's best move until the maximum specified depth is reached. It then
	 * returns the best column for the ComputerPlayer to choose once the value exceeds a large alpha number.
	 *
	 * @param board The current connect4 game board.
	 * @param alpha Value to compare evaluated moves against during the recursive calling of negamax, typically a very large number.
	 * @param depth Initial depth of the negamax search (0), increases by 1 each time negamax is called, until it equals maxDepth.
	 * @param color The color signifies which player is being evaluated. Always starts at 1 since it evaluates the computer's position first.
	 * @return column Returns the column index to win the game, block a player's winning move, bestPath, or bestValue based on the
	 * evaluation of the current board state for either the player or the computer.
	 */
	private int negamax(char[][] board, int alpha, int depth, int color) {

		int bestPath = 0;
		int bestValue = alpha;
		Move move = new Move();
		Winner winner = new Winner();
		char player;

		char[][] newBoard = copyBoard(board);

		if (color == 1) {
			player = 'o';
		} else {
			player = 'x';
		}

		for (int col = 0; col < 7; col ++) { // Sees into the future and plays the computer's winning move.
			char[][] winMove = copyBoard(board);
			if (move.getMove(winMove, col, 'o')) {
				if (winner.getWinner(winMove) == 'o') {
					return col;
				}
			}
		}

		for (int col = 0; col < 7; col ++) { // Sees into the future and blocks the player's winning move.
			char[][] blockMove = copyBoard(board);
			if (move.getMove(blockMove, col, 'x')) {
				if (winner.getWinner(blockMove) == 'x') {
					return col;
				}
			}
		}

		if (winner.getWinner(board) == 'o' || winner.getWinner(board) == 'x') {
			if (winner.getWinner(board) == player) {
				bestValue = color * (alpha - depth);
			} else {
				bestValue = color * (-alpha + depth);
			}
		} else if (winner.getWinner(board) == 'D') {
			bestValue = 0;
		} else if (depth == maxDepth) {
			int mid = eval(newBoard, player);
			if (mid != 0) {
				bestValue = color * (mid - depth);
			} else {
				bestValue = mid;
			}
		} else {
			for (int col = 0; col < 7; col ++) {
				char[][] dummyBoard = copyBoard(newBoard);
				if (move.getMove(dummyBoard, col, player)) {
					if (depth < maxDepth) {
						int evaluated = eval(dummyBoard, player);
						if (evaluated >= bestValue) {
							bestPath = col;
							bestValue = evaluated;
						} else {
							evaluated = -negamax(dummyBoard, -alpha, depth + 1, color * -1);
						}
					}
				}
			}
		}

		if (depth == 0) {
			return bestPath;
		} else {
			return bestValue;
		}

	}

  /**
   * eval method evaluates the current board on the current player and returns a value based on how good the current position is.
   *
	 * <p>Uses weights on vertical, diagonal, and horizontal connections as well as how manny tokens are in a row (2 or 3). Value builds
	 * with each potentially good move.
	 *
   * @return value How good the current position is for the player.
   */
	private int eval(char[][] board, char player) {

    int vertical = 1;
		int diagonal = 2;
		int horizontal = 3;

		int twoIn = 10;
		int threeIn = 1000;

		int value = 0;

    // Checking for the different configurations of two tokens in a horizontal row.
    for (int row = 0; row < 6; row ++) {
      for (int col = 0; col < 4; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row][col + 1] &&
        board[row][col + 2] == '_' &&
        board[row][col + 3] == '_') {

          value += twoIn * horizontal;
        }

        else if (board[row][col] == player &&
        board[row][col + 1] == player &&
        board[row][col + 2] == '_' &&
        board[row][col + 3] == '_') {

          value += twoIn * horizontal;
        }

        else if (board[row][col] == player &&
        board[row][col + 1] == '_' &&
        board[row][col + 2] == '_' &&
        board[row][col + 3] == player) {

          value += twoIn * horizontal;
        }

        else if (board[row][col] == '_' &&
        board[row][col + 1] == player &&
        board[row][col + 2] == '_' &&
        board[row][col + 3] == player) {

          value += twoIn * horizontal;
        }

        else if (board[row][col] == '_' &&
        board[row][col + 1] == '_' &&
        board[row][col + 2] == player &&
        board[row][col + 3] == player) {

          value += twoIn * horizontal;
        }

        else if (board[row][col] == '_' &&
        board[row][col + 1] == player &&
        board[row][col + 2] == player &&
        board[row][col + 3] == '_') {

          value += 2 * twoIn * horizontal;
        }
      }
    }

    // Checking for the two tokens in a row in a vertical column.
    for (int row = 5; row > 1; row --) {
      for (int col = 0; col < 7; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row - 1][col] &&
        board[row - 2][col] == '_') {

          value += twoIn * vertical;
        }
      }
    }

    // Checking for the different configurations of two tokens in an ascending diagonal.
    for (int row = 5; row > 2; row --) {
      for (int col = 0; col < 4; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row - 1][col + 1] &&
        board[row - 2][col + 2] == '_' &&
        board[row - 3][col + 3] == '_') {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row - 1][col + 1] == '_' &&
        board[row - 2][col + 2] == '_' &&
        board[row][col] == board[row - 3][col + 3]) {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row - 1][col + 1] == '_' &&
        board[row - 2][col + 2] == player &&
        board[row - 3][col + 3] == player) {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row - 1][col + 1] == player &&
        board[row - 2][col + 2] == '_' &&
        board[row - 1][col + 1] == board[row - 3][col + 3]) {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row - 1][col + 1] == '_' &&
        board[row][col] == board[row - 2][col + 2] &&
        board[row - 3][col + 3] == '_') {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row - 1][col + 1] == player &&
        board[row - 1][col + 1] == board[row - 2][col + 2] &&
        board[row - 3][col + 3] == '_') {

          value += 2 * twoIn * diagonal;
        }
      }
    }

    // Checking for the different configurations of two tokens in a descending diagonal.
    for (int row = 0; row < 3; row ++) {
      for (int col = 0; col < 4; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row + 1][col + 1] &&
        board[row + 2][col + 2] == '_' &&
        board[row + 3][col + 3] == '_') {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row + 1][col + 1] == '_' &&
        board[row + 2][col + 2] == '_' &&
        board[row][col] == board[row + 3][col + 3]) {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row + 1][col + 1] == '_' &&
        board[row + 2][col + 2] == player &&
        board[row + 3][col + 3] == player) {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row + 1][col + 1] == player &&
        board[row + 2][col + 2] == '_' &&
        board[row + 1][col + 1] == board[row + 3][col + 3]) {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row + 1][col + 1] == '_' &&
        board[row][col] == board[row + 2][col + 2] &&
        board[row + 3][col + 3] == '_') {

          value += twoIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row + 1][col + 1] == player &&
        board[row + 1][col + 1] == board[row + 2][col + 2] &&
        board[row + 3][col + 3] == '_') {

          value += 2 * twoIn * diagonal;
        }
      }
    }

    // Checking for the different configurations of three tokens in a horizontal row.
    for (int row = 0; row < 6; row ++) {
      for (int col = 0; col < 4; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row][col + 1] &&
        board[row][col + 2] == '_' &&
        board[row][col] == board[row][col + 3]) {

          value += threeIn * horizontal;
        }

        else if (board[row][col] == player &&
        board[row][col + 1] == '_' &&
        board[row][col] == board[row][col + 2] &&
        board[row][col] == board[row][col + 3]) {

          value += threeIn * horizontal;
        }

        else if (board[row][col] == '_' &&
        board[row][col + 1] == player &&
        board[row][col + 1] == board[row][col + 2] &&
        board[row][col + 1] == board[row][col + 3]) {

          value += threeIn * horizontal;
        }

        else if (board[row][col] == player &&
        board[row][col] == board[row][col + 1] &&
        board[row][col] == board[row][col + 2] &&
        board[row][col + 3] == '_') {

          value += threeIn * horizontal;
        }
      }
    }

    // Checking for three tokens in a row in a vertical column.
    for (int row = 5; row > 2; row --) {
      for (int col = 0; col < 7; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row - 1][col] &&
        board[row][col] == board[row - 2][col] &&
        board[row - 3][col] == '_') {

          value += threeIn * vertical;
        }
      }
    }

    // Checking for the different configurations of three tokens in an ascending diagonal.
    for (int row = 5; row > 2; row --) {
      for (int col = 0; col < 4; col ++) {

        if (board[row][col] == player &&
        board[row][col] == board[row - 1][col + 1] &&
        board[row][col] == board[row - 2][col + 2] &&
        board[row - 3][col + 3] == '_') {

          value += threeIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row][col] == board[row - 1][col + 1] &&
        board[row - 2][col + 2] == '_' &&
        board[row][col] == board[row - 3][col + 3]) {

          value += threeIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row - 1][col + 1] == '_' &&
        board[row][col] == board[row - 2][col + 2] &&
        board[row][col] == board[row - 3][col + 3]) {

          value += threeIn * diagonal;
        }

        else if (board[row][col] == '_' &&
        board[row - 1][col + 1] == player &&
        board[row - 1][col + 1] == board[row - 2][col + 2] &&
        board[row - 1][col + 1] == board[row - 3][col + 3]) {

          value += threeIn * diagonal;
        }
      }
    }

    // Checking for the different configurations of three tokens in a descending diagonal.
    for (int row = 0; row < 3; row ++) {
      for (int col = 0; col < 4; col ++) {

        if (board[row][col] == '_' &&
        board[row + 1][col + 1] == player &&
        board[row + 1][col + 1] == board[row + 2][col + 2] &&
        board[row + 1][col + 1] == board[row + 3][col + 3]) {

          value += threeIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row + 1][col + 1] == '_' &&
        board[row][col] == board[row + 2][col + 2] &&
        board[row][col] == board[row + 3][col + 3]) {

          value += threeIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row][col] == board[row + 1][col + 1] &&
        board[row + 2][col + 2] == '_' &&
        board[row][col] == board[row + 3][col + 3]) {

          value += threeIn * diagonal;
        }

        else if (board[row][col] == player &&
        board[row][col] == board[row+1][col+1] &&
        board[row][col] == board[row+2][col+2] &&
        board[row+3][col+3] == '_') {

          value += threeIn * diagonal;
        }
      }
    }

    // Checking for open ended three tokens in a horizontal row.
    for (int row = 0; row < 6; row ++) {
      for (int col = 0; col < 3; col ++) {

        if (board[row][col] == '_' &&
        board[row][col + 1] == player &&
        board[row][col + 2] == player &&
        board[row][col + 3] == player &&
        board[row][col + 4] == '_') {

          value += 2 * threeIn * horizontal;
        }
      }
    }

    // Checking for open ended three tokens in an ascending diagonal.
    for (int row = 5; row > 3; row --){
      for (int col = 0; col < 3; col ++){

        if (board[row][col] == '_' &&
        board[row - 1][col + 1] == player &&
        board[row - 2][col + 2] == player &&
        board[row - 3][col + 3] == player &&
        board[row - 4][col + 4] == '_'){

          value += 2 * threeIn * diagonal;
        }
      }
    }

    // Checking for open ended three tokens in a descending diagonal.
    for (int row = 0; row < 2; row ++){
      for (int col = 0;col < 3; col ++){

        if (board[row][col] == '_' &&
        board[row + 1][col + 1] == player &&
        board[row + 2][col + 2] == player &&
        board[row + 3][col + 3] == player &&
        board[row + 4][col + 4] == '_') {

          value += 2 * threeIn * diagonal;
        }
      }
    }

    return value;
  }

}