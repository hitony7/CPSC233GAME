/** 
* @author: Minnie 
* HumanPlayer class: gets move from HumanPlayer 
* includes input method to obtain move from player 
* validates decision to see if it is valid and return the decision to gameconfig 
*/ 

import java.util.Scanner; 

public class Player{
	
	
	public Player(){
	}
	
	/**
	* method to obtain a column to drop the char token into
	* does not check for if the column is available, that will be done in GameConfig
	* first checks if the input is a valid integer 
	* and then checks if the input is within range of the board
	*/ 
	
	public int getMove(){			
		isValid(); //exported the action of asking and checking into isInteger method 
		
		if (inputColumn >= 0 && inputColumn < 7){ 
			return inputColumn; 
		} else {
			System.out.println("Error, that choice was not valid!");
			isValid();
		}
		
	}
	/**
	* this method checks for the validity of input by the user
	* catches an error if it is not a valid integer, and it will prompt the user to re-enter their choice
	*/ 
	private static void isValid(){
		System.out.println("Please enter column would you like to put your token in: "); 
		
		try{
			int inputColumn = input.nextInt() - 1; // -1 because counting in computer is different from real world counting, 1 = 0 in computer counting. 
		} catch (Exception e){
			System.out.println("Error! That was not valid! Please try again: "); // catches the error if the user does not input a valid number
			isInteger();
		}
	}

}