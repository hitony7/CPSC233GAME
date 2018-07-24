/**
 * Winner looks at the Connect4 board condition and determines if there is a winner through its method getWinner.
 *
 * <p>Determination logic modified from https://www.ktbyte.com/resources/dist/assets/processing/examples/large/connect4.pde.
 *
 * @author T02-1 - Matthew Cox
 * @version 1.0
 */
public class Winner {

  /**
   *  winner stores the state of the game, won by a certain player ('x' or 'o'), ongoing ('_'), or ended in a draw ('D').
   */
  public char winner;

  /**
   * Default coonstructor for the Winner class.
   */
  public Winner() {
    winner = '_'; // Ensures the game is ongoing as the default.
  }

  /**
   * getWinner takes the Connect4 board as an argument and determines if there is a winner
   * based on the current moves made by the players or computer.
   *
   * @param board the current Connect4 board.
   * @return char, returns either the winner of the game, '_' since the game is ongoing,
   * or 'D' because the game ended in a draw.
   */
  public char getWinner(char[][] board) {

    //Determines if there is a winner in a row
    for (int row = 0; row < 6; row ++) {

      for (int column = 0; column < 4; column ++) { //Set up so the array can never be out of bounds

        if (board[row][column] != '_' &&
        board[row][column] == board[row][column + 1] &&
        board[row][column] == board[row][column + 2] &&
        board[row][column] == board[row][column + 3]) {

          winner = board[row][column]; //Sets winner to token of the winner in a row.
          return winner;

        }
      }
    }

    //Determines if there is a winner in a column
    for (int row = 0; row < 3; row ++) { //Set up so the array can never be out of bounds

      for (int column = 0; column < 7; column ++) {

        if (board[row][column] != '_' &&
        board[row][column] == board[row + 1][column] &&
        board[row][column] == board[row + 2][column] &&
        board[row][column] == board[row + 3][column]) {

          winner = board[row][column]; //Sets winner to token of the winner in a column.
          return winner;

        }
      }
    }

    //Determines if there is a winner in a ascending diagonal
    for (int row = 5; row > 2; row --) { //Set up so the array can never be out of bounds

      for (int column = 0; column < 4; column ++) { //Set up so the array can never be out of bounds

        if(board[row][column] != '_' &&
        board[row][column] == board[row - 1][column + 1] &&
        board[row][column] == board[row - 2][column + 2] &&
        board[row][column] == board[row - 3][column + 3]) {

          winner = board[row][column]; //Sets winner to token of the winner in a diagonal.
          return winner;

        }
      }
    }

    //Determines if there is a winner in a descending diagonal
    for (int row = 5; row > 2; row --) { //Set up so the array can never be out of bounds

      for (int column = 6; column > 2; column --) { //Set up so the array can never be out of bounds

        if(board[row][column] != '_' &&
        board[row][column] == board[row - 1][column - 1] &&
        board[row][column] == board[row - 2][column - 2] &&
        board[row][column] == board[row - 3][column - 3]) {

          winner = board[row][column]; //Sets winner to token of the winner in a diagonal.
          return winner;

        }
      }
    }

    //Determines if the game is being played still with no winner
    for (int row = 0; row < 6; row ++) {

      for (int column = 0; column < 7; column ++) {

        if (board[row][column] == '_') {

          winner = '_'; //Sets winner to free space token so the game continues.
          return winner;

        }
      }
    }

    //If there are no free spaces and no winner then game ends in a draw
    winner = 'D';
    return winner;

  }

}