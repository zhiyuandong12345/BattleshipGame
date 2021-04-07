//Zhiyuan 4.2

package battleship;

import java.util.Random;

/**
 * It contains a 10 by 10 array of ships, representing an ocean
 * It includes some methods to manipulate it
 * @author RuidaX & ZhiyuanD
 *
 */
public class Ocean {
	
	//instance variables
	
	/**
	 * It will be used to quickly determine which ship is at certain location 
	 */
	private Ship[][] ships = new Ship[10][10];
	
	/**
	 * It represents the total number of shots fired by the user
	 */
	private int shotsFired;
	
	/**
	 * It represents the total number of shots which hit a ship
	 * Note: if the user shoots the same part of a ship more than once, every hit is counted
	 */
	private int hitCount;
	
	/**
	 * It tracks the number of ships sunk (10 in total)
	 */
	private int shipsSunk;

	Random random = new Random();

	//constructor
	/**
	 * It creates an empty ocean
	 * It also fills the ships array with EmptySea objects
	 * It initializes any game variables, such as how many shots have been fired
	 */
	public Ocean() {
		// initialize the ocean as empty
		for (int i = 0; i < this.ships.length; i++) {
			for (int j = 0; j < this.ships[0].length; j++) { 
				//create an EmptySea object
				Ship ship = new EmptySea();
				//place the object at the location
				ship.placeShipAt(i, j, true, this);

			}
		}
		
		//initialize the game variables, including shotsFired, hitCount, and shipsSunk
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;

	}
	
	/**
	 * This method places all the ships to be shot in the ocean randomly
	 * It calls the okToPlaceShipAt method to place the ships according to the rules
	 * @param ship
	 */
	public void tryPlacingShipUntilSuccess(Ship ship) {
		//create variables to indicate the location in the ocean
		int row, col;
		//specify the boolean value of whether the ship is horizontal
		boolean hor;

		while (true) {
			//assign a random number  within 0 to 9 to row and col
			row = random.nextInt(10);
			col = random.nextInt(10);
			//assign a random boolean value to hor
			hor = random.nextBoolean();
			
			//if it is ok to place the ship at the given location
			if (ship.okToPlaceShipAt(row, col, hor, this)) {
				//place the ship at that location
				ship.placeShipAt(row, col, hor, this);
				
				break;
			}
		}
	}

	/**
	 * Keep generating random locations and orientation for the ships from longest to shortest
	 * If a generated way to place the ship is invalid, discard and generate again
	 */
	void placeAllShipsRandomly() {
		
		// there will be 1 battleship, 2 cruisers, 3 destroyers, and 4 submarines
		//create a Battleship object and place it randomly
		Battleship B = new Battleship();
		tryPlacingShipUntilSuccess(B);

		//create a Cruiser object and place it randomly
		for (int i = 0; i < 2; i++) {
			Cruiser C = new Cruiser();
			tryPlacingShipUntilSuccess(C);
		}
		
		//create a Destroyer object and place it randomly
		for (int i = 0; i < 3; i++) {
			Destroyer D = new Destroyer();
			tryPlacingShipUntilSuccess(D);
		}
		
		//create a Submarine object and place it randomly
		for (int i = 0; i < 4; i++) {
			Submarine S = new Submarine();
			tryPlacingShipUntilSuccess(S);
		}
		
	}
	
	/**
	 * It returns true if the given location contains a ship
	 * Return false if it does not
	 * @param row
	 * @param column
	 * @return the boolean value of whether the location is occupied
	 */
	boolean isOccupied(int row, int column) {
		
		//if the location is an instance of an EmptySea class, return false
		if (this.ships[row][column] instanceof EmptySea) {
			return false;
		}else {
			//otherwise, return true
			return true;	
		}
	}
	
	/**
	 * It returns true if the given location contains an afloat "real" ship 
	 * Returns false if it does not
	 * It also updates the number of shots and hits
	 * @param row of the ocean
	 * @param column of the ocean
	 * @return the boolean value of whether the given location contains an afloat ship
	 */
	boolean shootAt(int row, int column) {
		//update the shots fired
		this.shotsFired++;
		
		//if the location is occupied by a "real" ship and the ship has not been sunk
		if (this.isOccupied(row, column) & (!this.ships[row][column].isSunk())) {
			
			//update the hit count
			this.hitCount++;
			
		}
		//return the boolean value of whether the given location contains a real ship 
		boolean isShot = this.ships[row][column].shootAt(row, column);
		
		// if the ship at the given location was shot
		if (isShot) {
			//print the hit message
			System.out.println("You hit a target.");
			
			//if the ship is sunk
			if (this.ships[row][column].isSunk()) {
				
				//increase the shipsSunk count
				this.shipsSunk++;
				
				//print the sunk message
				System.out.println("You just sank a " + this.ships[row][column].getShipType());
			}
			
		}else {
			//otherwise, print the following line
			System.out.println("You missed a shot.");
		}
		return isShot;
	}
	
	/**
	 * It returns the number of shots fired in the game
	 * @return the number of shots fired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * It returns the number of hits recorded in the game
	 * All hits should be counted
	 * @return the number of hits
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * It returns the number of ships sunk in the game
	 * @return the number of ships sunk
	 */
	int getShipsSunk(){
		return this.shipsSunk;
	}
	
	/**
	 * It determines whether the game is over
	 * It returns true if all ships (10 ships) have been sunk
	 * @return the boolean value of whether the game is over
	 */
	boolean isGameOver(){
		//if the number of ship sunk is 10, return true
		if (getShipsSunk() == 10) {
			return true;
		}else {
			return false; //otherwise, return false
		}
	}
	
	/**
	 * It returns the 10 by 10 array of ships
	 * @return the 10 by 10 array of ships
	 */
	Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * This method set the ships array with the given location and the ship object
	 * @param row of the Ocean 
	 * @param column of the Ocean
	 * @param ship
	 */
	void setShipArray(int row, int column, Ship ship) {
		this.ships[row][column] = ship;
	}

	/**
	 * 
	 * This method prints the Ocean 	 
	 * Row numbers will be displayed along the left edge of the array (0-9)
	 * Column numbers will be displayed along the top (0-9)
	 * x means fired at and hit
	 * - means fired at and missed
	 * s means sunken
	 * . means never fired at
	 */
	void print() {
		//print column numbers

		System.out.println("  0 1 2 3 4 5 6 7 8 9");

		//print row numbers
		for(int i = 0; i < this.ships.length; i++) {
			Ship ship;
			System.out.print(i + " ");

			//print the value of each ship
			for (int j = 0; j < this.ships[0].length; j++) {
				ship = this.ships[i][j];
				
				if (ship instanceof EmptySea) {
					//if the location was never shot, print dot
					System.out.print(ship + " ");
				}else {
					//if the ship is sunken, or if the shot is missed or hit
					if (ship.isSunk()) {

						//print "s" representing sunk
						System.out.print("s ");
					}else {
						//if the ship is not sunk but hit
						if (ship.getHitInOcean(i, j)) {
							System.out.print("x ");
						}else {
							System.out.print(". ");
						}
						
					}
				}
			}
			System.out.println("");
		}

	}
	
	/**
	 * This method is used for debugging purpose only
	 * this method prints the O​cean​ with row numbers displayed along the left edge of the array 
	 * and column numbers displayed along the top.
	 * It shows the location of the ships​. 
	 * It can be used during development and debugging, to see where ships are actually being placed in the Ocean​. 
	 * ‘b’: Use ‘​b’ to indicate a ​Battleship​.
	 * ‘c’: Use ‘c’ to indicate a ​Cruiser​.
	 * ‘d’: Use ‘d​’ to indicate a ​Destroyer​.
	 * ‘s’: Use ‘​s​’ to indicate a ​Submarine​.
	 * ‘ ‘: Use ‘​ ​’ (single space) to indicate an ​EmptySea​.
	 */
	void printWithShips() {
		//print column numbers

		System.out.println("  0 1 2 3 4 5 6 7 8 9");

		//print row numbers
		for(int i = 0; i < this.ships.length; i++) {
			Ship ship;
			System.out.print(i + " ");
		
			//print the value of each ship
			for (int j = 0; j < this.ships[i].length; j++) {
				ship = this.ships[i][j];
				
				//if the ship type is empty sea
				if (ship.getShipType() == "empty") {
					System.out.print("  ");
				}else {
					//print the type of each ship
					System.out.print(ship.getAlias() + " ");
				}
			}
			System.out.println("");
		}
	}
	
	
}