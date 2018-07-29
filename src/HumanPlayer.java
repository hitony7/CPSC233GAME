import java.util.Scanner;

/**
 * HumanPlayer takes input from a user to place a token in the Connect4 board.
 *
 * @author T02-1 - Minnie Thai
 * @version 1.0
 */
public class HumanPlayer {

  /**
	* Main method for testing the HumanPlayer class implementation.
	*/

	/**
	 * input creates a Scanner object to store user input.
	 */
	public Scanner input = new Scanner(System.in);

	/**
	 * column stores column location choice from the user.
	 */
	public int column;

	/**
	 * getColumn returns the user's choice of column as an integer.
	 *
	 * @return column, the integer representation of the column to put the token.
	 */
	public int getColumn() {

		isNumber();

		if (isValid(column) == true) {

			column = column - 1; //Changes user's column choice to the correct column index
			return column;


		} else {

			getColumn();

		}
		return column; //Should not be possible.
	}

	/**
	 * isNumber checks to make sure the user enters an integer value for their column location.
	 */
	public void isNumber() {

		System.out.println("Please enter a column you'd like to drop your token in: ");

		try {

			column = input.nextInt();

		} catch (Exception e) {

			System.out.println("Error! That was not a number!");
			input.next();
			getColumn();

		}

	}

	/**
	 * isValid checks to make sure the column location is within the Connect4 board.
	 *
	 * @param someColumn the column in which the user is trying to drop their token.
	 * @return boolean, true if the column selection was valid, false if it wasn't.
	 */
	public boolean isValid(int someColumn) {

		if (someColumn >= 1 && someColumn <= 7) {

			return true;

		} else {

			System.out.println("Error, that choice was not on the board!");
			return false;

		}

	}

}