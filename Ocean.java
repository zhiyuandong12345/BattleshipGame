package battleship;

import java.util.Random;

public class Ocean {
	private Ship[][] ships = new Ship[10][10];
	
	private int shotsFired;
	
	private int hitCount;
	
	private int shipsSunk;
	
	Random random = new Random();
	
	
	public Ocean() {
		// initialize the ocean as empty
		for (int i = 0; i < this.ships.length; i++) {
			for (int j = 0; j < this.ships[0].length; j++) { 
				this.ships[i][j] = new EmptySea();   

			}
		}
		
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		
		placeAllShipsRandomly();  // is it the right place to call this method? 
		
	}
	
	public void tryPlacingShipUntilSuccess(Ship ship) {
		int row, col;
		boolean hor;
		
		while (true) {
			row = random.nextInt(10);
			col = random.nextInt(10);
			hor = random.nextBoolean();
			
			if (ship.okToPlaceShipAt(row, col, hor, this)) {
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
		Battleship B = new Battleship();
		tryPlacingShipUntilSuccess(B);
		
		for (int i = 0; i < 2; i++) {
			Cruiser C = new Cruiser();
			tryPlacingShipUntilSuccess(C);
		}
		
		for (int i = 0; i < 3; i++) {
			Destroyer D = new Destroyer();
			tryPlacingShipUntilSuccess(D);
		}
		
		for (int i = 0; i < 4; i++) {
			Submarine S = new Submarine();
			tryPlacingShipUntilSuccess(S);
		}
	}
	
	boolean isOccupied(int row, int column) {
		if (this.ships[row][column] instanceof EmptySea) {
			return false;
		}else {
			return true;	
		}
	}
	
	boolean shootAt(int row, int column) {
		this.shotsFired++;
		
		if (isOccupied(row, column)) {
			this.hitCount++;
			return true;
		}else {
			return false;
		}
	}
	
	int getShotsFired() {
		return this.shotsFired;
	}
	
	int getHitCount() {
		return this.hitCount;
	}
	
	int getShipsSunk(){
		return this.shipsSunk;
	}
	
	boolean isGameOver(){
		if (getShipsSunk() == 10) {
			return true;
		}else {
			return false;
		}
	}
	
	Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * x means fired at and hit
	 * - means fired at and missed
	 * s means sunken
	 * . means never fired at
	 */
	void print() {
		//print column numbers
		for (int i = 0; i < this.ships.length; i++) {
			System.out.println(i + " ");
		}
		
		//print a blank line
		System.out.println("");
		
		//print row numbers
		for(int i = 0; i < this.ships[0].length; i++) {
			Ship ship;
			System.out.println(i + " ");
			
			//print the value of each ship
			for (int j = 0; j < this.ships[i].length; j++) {
				ship = this.ships[i][j];
				
			//if the ship is sunken, or if the shot is missed or hit
				if (ship.isSunk() || ship.getHitInOcean(i, j)) {
					
					//print the ship object and calls the toString method in Ship class
					System.out.println(ship + " ");
				}else {
					//if the location was never shot, print dot
					System.out.println("." + " ");
				}
			}
			
		}
		
		
		
	}
	
	void printWithShips() {
		System.out.println(" 0123456789");
		
	}
}