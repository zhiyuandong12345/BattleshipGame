
package battleship;

import java.util.Scanner;


/**
 * This is the main class containing the main method of the game play
 * @author RuidaX & ZhiyuanD
 */
public class BattleshipGame {
	
	
	private Ocean ocean;

	/**
	 * scanner player's input
	 * @param scanner
	 * @return true if the player's answer starts with 'Y' or 'y', false otherwise
	 */
	public static boolean getYesOrNoFromPlayer(Scanner scanner) {
		//scan the user input as playerAnswer
		String playerAnswer = scanner.next();
		
		//if the first character of the user input is either 'y' or 'Y', return true
		if ((playerAnswer.charAt(0) == 'y') | (playerAnswer.charAt(0) == 'Y')) {
			return true;
		}
		return false; //otherwise, return false
	}

	
	/**
	 * This method sets up the game by creating an Ocean
	 * And place all the ships randomly
	 */
	public void gameSetup() {
		//create an instance of Ocean
		this.ocean = new Ocean();
		//place all the ships randomly
		this.ocean.placeAllShipsRandomly();
	}
	
	/**
	 * the whole process of one player's turn
	 * @param scanner
	 */
	public void playersTurn(Scanner scanner) {
		//print the ocean
		this.ocean.print();
		System.out.println("Hit count " + ocean.getHitCount());
		
		//get user input
		System.out.println("Enter row and column numbers in the form of row,column (e.g. 3,4):");
		String userInput = scanner.next();
		
		//split the user input
		String [] userInputParts = userInput.split(",");
		String rowString = userInputParts[0];
		String columnString = userInputParts[1];
		
		//convert the string to integer
		int row = Integer.parseInt(rowString);
		int column = Integer.parseInt(columnString);
		
		//shoot at the location with the given row and column
		this.ocean.shootAt(row, column);
		
		
	}
	
	/**
	 * set up the game; accept shots from player, display results
	 * and ask the user if he/she wants to play again.
	 * @param args
	 */
	public static void main(String args[]) {
		
		//create scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		//print welcome messages
		System.out.println("Welcome to Battleship Game!");
		System.out.println("You will play against the computer on a 10 by 10 ocean");
		System.out.println("Would you like to start? (y or n)");
		
		//get the input from user to start
		boolean getStarted = BattleshipGame.getYesOrNoFromPlayer(scanner);

		while (getStarted) {
			
			//create an instance of the BattleshipGame class
			BattleshipGame game = new BattleshipGame();
			
			//set up the game
			game.gameSetup();
			
			//for checking whether the ships were placed correctly
			//print the location of ships visually
			game.ocean.printWithShips();
			
			//while the game is not over
			while(!game.ocean.isGameOver()) {
				
				//start the player's turn
				game.playersTurn(scanner);
				
			}
			
			System.out.println("Game Over!");
			
			//print the total shots in the game
			System.out.println("Your total shots are " + game.ocean.getShotsFired());
			
			//ask the user whether wants to play again
			System.out.println("Play again? (y/n)");
			if (!BattleshipGame.getYesOrNoFromPlayer(scanner)) {
				getStarted = false;
			}

		}
		//close the scanner
		scanner.close();
	}
}