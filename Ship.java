
package battleship;

/**
 * This abstract class describes the characteristics common to all ships
 * It has five subclasses including Battleship, Cruiser, Destroyer, Submarine and EmptySea 
 * @author RuidaX & ZhiyuanD
 *
 */
public abstract class Ship {

	//instance variables
	/**
	 * The row that contains the bow (the front part of the ship)
	 * Only accessible from within this class
	 */
	private int bowRow;

	/**
	 * The column that contains the bow (the front part of the ship)
	 * Only accessible from within this class
	 */
	private int bowColumn;

	/**
	 * The length of the ship
	 * Only accessible from within this class
	 */
	private int length;


	/**
	 * This is a boolean variable that represents whether the ship is placed horizontally or vertically
	 * Only accessible from within this class
	 */
	private boolean horizontal;

	/**
	 * This is an array of booleans that indicates whether that part of the ship has been hit or not
	 * Only accessible from within this class
	 */
	private boolean[] hit;


	//Constructor
	/**
	 * Constructor which creates a ship with the given length
	 * It also initialize the hit array based on that length
	 * @param length of the ship
	 */
	public Ship(int length) {

		//set the given length as the length of the ship
		this.length = length;

		//initialize the hit array based on the length
		this.hit = new boolean[this.length];

		//iterate over each location of the ship
		for (int i = 0; i < this.hit.length; i++) {
			//set the initial value
			this.hit[i] = false;
		}
	}

	//Setters and Getters

	/**
	 * get the length of the ship
	 * @return
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * get the row index of the front of the ship
	 * @return
	 */
	public int getBowRow() {
		return this.bowRow;
	}

	/**
	 * get the column index of the front of the ship
	 * @return
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * get a list of boolean variables indicating whether the spot of the ship has been hit
	 * @return
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * whether the ship is placed horizontally
	 * @return
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}



	/**
	 * set the row index of the front of the ship
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * set the column index of the front of the ship
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * set whether the ship is placed horizontally
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * an abstract method to get the type of the ship
	 */
	public abstract String getShipType();


	/**
	 * Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location; 
	 * Return false otherwise. 
	 * The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
	 * And it must not stick out beyond the array. 
	 * Does not actually change either the ship or the ocean - it just says if it is legal to do so.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return boolean value of whether it is ok to place the ship at the given location
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		int shipLength = this.length;

		// the bow of the ship has to be on the 10 by 10 ocean
		if (column > 9 || column < 0 || row > 9 || column < 0) return false;

		// the entire ship has to be place on EmptySea
		// its neighboring locations cannot contain other ship

		if (horizontal) {
			// the rear of the ship has to be on the 10 by 10 ocean
			if (column - shipLength + 1 < 0) return false;

			//check for adjacent ships
			//iterate over each part of the ship
			for (int i = column - shipLength + 1; i <= column; i++) {
				//each location that the ship is to occupy should be empty
				if(ocean.isOccupied(row, i)) return false;

				//check the north and south of the ship 
				if ((row + 1 < 10) && ocean.isOccupied(row + 1, i)) return false;
				if ((row - 1 >= 0) && ocean.isOccupied(row - 1, i)) return false;

				//for the 3 locations beyond the bow of the ship
				if ((i == column) && (i + 1 < 10)) {
					if (ocean.isOccupied(row, i + 1)) return false; 
					if ((row + 1 < 10) && (ocean.isOccupied(row + 1, i + 1))) return false; 
					if ((row - 1 >= 0) && (ocean.isOccupied(row - 1, i + 1))) return false; 
				}

				//for the 3 locations beyond the rear of the ship
				if ((i == column - shipLength + 1) && (i - 1 >= 0)) {
					if (ocean.isOccupied(row, i - 1)) return false;
					if ((row - 1 >= 0) && ocean.isOccupied(row - 1, i - 1)) return false;
					if ((row + 1 < 10) && ocean.isOccupied(row + 1, i - 1)) return false; 
				}
			}
		}else {
			// the rear of the ship has to be on the 10 by 10 ocean
			if (row - shipLength + 1 < 0) return false;

			//check for adjacent ships
			//iterate over each part of the ship
			for (int j = row - shipLength + 1; j <= row; j++) {
				//each location that the ship is to occupy should be empty
				if(ocean.isOccupied(j, column)) return false;

				//check the north and south of the ship 
				if ((column + 1 < 10) && ocean.isOccupied(j, column + 1)) return false;
				if ((column - 1 >= 0) && ocean.isOccupied(j, column - 1)) return false;

				//for the 3 locations beyond the bow of the ship
				if ((j == row) && (j + 1 < 10)) {
					if (ocean.isOccupied(j + 1, column)) return false; 
					if ((column + 1 < 10) && (ocean.isOccupied(j + 1, column + 1))) return false; 
					if ((column - 1 >= 0) && (ocean.isOccupied(j + 1, column - 1))) return false; 
				}

				//for the 3 locations beyond the rear of the ship
				if ((j == row - shipLength + 1) && (j - 1 >= 0)) {
					if (ocean.isOccupied(j - 1, column)) return false;
					if ((column - 1 >= 0) && ocean.isOccupied(j - 1, column - 1)) return false;
					if ((column + 1 < 10) && ocean.isOccupied(j - 1, column + 1)) return false; 
				}
			}
		}
		return true;


	}
	/**
	 * This method puts the ship in the ocean
	 * It will give values to bowRow, bowColumn, horizontal instance variables to the ship
	 * It also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the â€‹Ocean â€‹object. 
	 * @param row of the ship
	 * @param columnn of the ship 
	 * @param horizontal boolean value of the ship 
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		//sets the column, row and horizontal values to this ship
		this.setBowColumn(column);
		this.setBowRow(row);
		this.setHorizontal(horizontal);

		//The following block of code is to put a reference to the ship in the ships array
		int shipLength = this.getLength();

		//if the ship is horizontal
		if (horizontal) {

			for (int i = column - shipLength + 1; i <= column; i++) {
				ocean.setShipArray(row, i, this);
			}
		}else {

			for (int i = row - shipLength + 1; i <= row; i++) {
				ocean.setShipArray(i, column, this);
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship has not been sunk, 
	 * mark that part of the ship as "hit" (in the hit array, index 0 indicates the bow) and return true; 
	 * otherwise return false.
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		// if ship is already sunk, return false
		if ((this instanceof EmptySea) || (this.isSunk())) return false;

		int bowColumn = this.getBowColumn();
		int bowRow = this.getBowRow();

		// if the shot does not hit the ship, return false
		if ((bowRow != row) && (bowColumn != column)) return false;
		if ((bowRow == row) && (bowColumn != column) && !this.horizontal) return false;
		if ((bowRow != row) && (bowColumn == column) && this.horizontal) return false;

		int hitIndex = (bowRow - row) + (bowColumn - column);

		if (hitIndex >= this.length || hitIndex < 0) return false;

		// rule out all the miss scenarios, then it is a valid hit
		this.hit[hitIndex] = true;
		return true;
	}

	/**
	 * It checks if the ship was sunk
	 * If it has been sunk, return true
	 * Otherwise, return false
	 * @return the boolean value of whether the ship was sunk
	 */
	boolean isSunk() {
		// if any part is not hit, return not sunk
		for (boolean b : this.hit) {
			if (b == false) return false;
		}

		return true;
	}

	//helper method
	/**
	 * This is a helper method for printing the ocean
	 * get the boolean value of whether a ship at certain location is hit
	 * @return true if it is hit
	 * @return false if it is missed
	 */

	public boolean getHitInOcean(int row, int column) {

		//initialize the index of the location of the ship
		int shipLocation;

		//if the ship is horizontal
		if (this.horizontal) {
			shipLocation = this.bowColumn - column;

			//if the ship if vertical
		}else {
			shipLocation = this.bowRow - row;
		}
		//return the boolean value of whether that location was hit
		return this.hit[shipLocation];
	}

	/**
	 * This method override the toString method
	 * It can be used to print out locations in the ocean that have been shot at
	 * It returns a single character String
	 */
	@Override
	public String toString() {
		//if the ship is sunk, return "s"
		if (isSunk()) {
			return "s";
			//otherwise, return "x" 
		}else {
			return "x";
		}
	}

	/**
	 * This is an abstract method which enables the subclasses to implement it
	 */
	public abstract String getAlias();

}