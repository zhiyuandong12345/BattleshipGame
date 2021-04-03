//Zhiyuan 4.2

package battleship;

import java.util.Scanner;

public class BattleshipGame {

	Ocean ocean;

	/**
	 * scanner player's input
	 * @param scanner
	 * @return true if the player's answer starts with 'Y' or 'y', false otherwise
	 */
	public static boolean getYesOrNoFromPlayer(Scanner scanner) {
		String playerAnswer = scanner.next();
		if ((playerAnswer.charAt(0) == 'y') | (playerAnswer.charAt(0) == 'Y')) {
			return true;
		}
		return false;
	}


	public void gameSetup() {
		this.ocean = new Ocean();
		this.ocean.placeAllShipsRandomly();
	}
	
	
	public void playersTurn(Scanner scanner) {
		//print the ocean
		this.ocean.print();
		System.out.println("Hit count " + ocean.getHitCount());
		
		//get user input
		System.out.println("Enter row and column numbers in the form of row,column (e.g. 3,4):");
		String userInput = scanner.next();
		
		String [] userInputParts = userInput.split(",");
		String rowString = userInputParts[0];
		String columnString = userInputParts[1];
		
		int row = Integer.parseInt(rowString);
		int column = Integer.parseInt(columnString);
		
		//shoot at the location with the given row and column
		boolean hitTarget = this.ocean.shootAt(row, column);
		
		if (hitTarget) {
			System.out.println("You hit a target.");
		}else {
			System.out.println("You missed a shot.");
		}
	}
	
	/**
	 * set up the game; accept shots from player, display results
	 * and ask the user if he/she wants to play again.
	 * @param args
	 */
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Battleship Game!");
		System.out.println("You will play against the computer on a 10 by 10 ocean");
		System.out.println("Would you like to start? (y or n)");

		boolean getStarted = BattleshipGame.getYesOrNoFromPlayer(scanner);

		while (getStarted) {
			
			BattleshipGame game = new BattleshipGame();
			
			game.gameSetup();
			
			//for checking whether the ships were placed correctly
			//print the location of ships visually
			game.ocean.printWithShips();
			
			while(!game.ocean.isGameOver()) {
				
				game.playersTurn(scanner);
				
			}
			
			System.out.println("Game Over!");
			System.out.println("Your total shots are " + game.ocean.getShotsFired());
			
			//ask the user whether wants to play again
			System.out.println("Play again? (y/n)");
			if (!BattleshipGame.getYesOrNoFromPlayer(scanner)) {
				getStarted = false;
			}

		}

		scanner.close();
	}
}