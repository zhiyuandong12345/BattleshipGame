//Zhiyuan 4.2
package battleship;

public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	private boolean[] hit;

	/**
	 * Constructor.
	 * @param length: length of the ship
	 */
	public Ship(int length) {
		this.length = length;

		this.hit = new boolean[this.length];
		for (int i = 0; i < this.hit.length; i++) {
			this.hit[i] = false;
		}
	}

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

	//helper method
	/**
	 * This is a helper method for printing the ocean
	 * get the boolean value of whether a ship at certain location is hit
	 * @return true if it is hit
	 * @return false if it is missed
	 */

	public boolean getHitInOcean(int row, int column) {
		int shipLocation = 0;

		if (this.isHorizontal()) {
			shipLocation = this.getBowColumn() - column;
			return this.getHit()[shipLocation];
		}else if (!this.isHorizontal()) {
			shipLocation = this.getBowRow() - row;
			return this.getHit()[shipLocation];
		}return false;
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

	public abstract String getShipType();


	/**
	 *Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location; 
	 *Return false otherwise. 
	 *The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
	 *And it must not ”stick out” beyond the array. 
	 *Does not actually change either the ship or the O​cean ​- it just says if it is legal to do so.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		int shipLength = this.length;
		Ship[][] ships = ocean.getShipArray();


		//check whether any part of the ship stick out beyond the array
		if (column > 9 || column < 0 || row > 9 || column < 0) {
			return false;
		}

		//if the ship is horizontal
		if (horizontal) {
			//check whether the ship stick out beyond the array on the left
			if (column - shipLength + 1 < 0) {
				return false;
			}

			//check for adjacent ships
			//iterate over each part of the ship
			for (int i = column - shipLength + 1; i <= column; i++) {
				//each location that the ship occupied should have been empty
				if(ocean.isOccupied(row, i)) {

					return false;
				}

				//check the north and south of the ship 
				if(ocean.isOccupied(row + 1, i) && ocean.isOccupied(row -1, i)) {
					return false;
				}

				//for the location of ship bow
				if (i == column) {
					//check whether there are any adjacent ship at bowRow
					if (ocean.isOccupied(row, i + 1) && ocean.isOccupied(row - 1, i + 1)
							&& ocean.isOccupied(row + 1, i + 1)) {
						return false;
					}
				}

				//for the location of the end of the ship
				if( i == column - shipLength +1) {
					if(ocean.isOccupied(row, i - 1) && ocean.isOccupied(row - 1, i - 1)
							&& ocean.isOccupied(row + 1, i - 1)) {
						return false;
					}
				}
				
				return true;


			}

		}else if (!horizontal) {
			//check whether ship stick beyond the array
			if (row - shipLength + 1 < 0) {
				return false;
			}
			
			//check for adjacent ships
			//iterate over each part of the ship
			for (int i = row - shipLength + 1; i <= row; i++) {
				//each location that the ship occupied should have been empty
				if(ocean.isOccupied(i, column)){

					return false;
				}
				//check the east and west of the ship
				if (ocean.isOccupied(i, column + 1) && ocean.isOccupied(i, column -1)) {
					return false;
				}
				
				//check the location of ship bow
				if (i == row) {
					if (ocean.isOccupied(i + 1, column) && ocean.isOccupied(i + 1, column -1)
							&& ocean.isOccupied(i + 1, column + 1)) {
						return false;
					}
				}
				
				//check the location of the front of the ship
				if (i == row - shipLength +1) {
					if(ocean.isOccupied(i - 1, column) && ocean.isOccupied(i - 1, column - 1) && ocean.isOccupied(i - 1, column + 1)) {
						return false;
					}
				}
				
				return true;
			}
			
		}
		return true;
		
		
	}
	/**
	 * This method puts the ship in the ocean
	 * It will give values to bowRow, bowColumn, horizontal instance variables to the ship
	 * It also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the ​Ocean ​object. 
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
		if (this.isHorizontal()) {
			for (int i = column - shipLength + 1; i <= column; i++) {
				//for(int i = column; i >= column - shipLength + 1; i--) {

				ocean.getShipArray()[row][i] = this;
			}
		}else if (!this.isHorizontal()) {
			for (int i = row - shipLength + 1; i <= row; i++) {
				//for (int i = row; i>= row - shipLength +1; i--) {

				ocean.getShipArray()[i][column] = this;
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, 
	 * mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return true; 
	 * otherwise return false.
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		int bowColumn = this.getBowColumn();
		int bowRow = this.getBowRow();
		int shipLength = this.getLength();
		int hitLocationIndex= 0;

		if (!this.isSunk()) {
			if (this.isHorizontal()) {
				if (bowRow == row) {
					for (int i = bowColumn - shipLength + 1; i <= bowColumn; i++) {
						if (i == column) {

							hitLocationIndex = bowColumn - i;
							this.getHit()[hitLocationIndex] = true;
							return true;
						}
					}
				}
			}else if (!this.isHorizontal()) {
				if (bowColumn == column) {
					for (int i = bowRow - shipLength + 1; i <= bowRow; i++) {
						if (i == row) {
							hitLocationIndex = bowRow - i;
							this.getHit()[hitLocationIndex] = true;
							return true;
						}
					}
				}

			}
		}return false;
	}


	boolean isSunk() {
		// if any part is not hit, return not sunk
		for (boolean b : this.hit) {
			if (b == false) return false;
		}

		return true;
	}

	@Override
	public String toString() {
		if (isSunk()) {
			return "s";
		}else {
			return "x";
		}
	}
}