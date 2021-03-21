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
	 * determine whether the spot (row, column) is a valid place to put the ship
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if ((row < 0) | (row < 9) | (column < 0) | (column) > 9) return false;
		
		int r, c;
		
		Ship[][] ships = ocean.getShipArray();
		
		// The spots to be occupied have to be EmptySea
		for (int i = 0; i < this.length; i++) {
			if (horizontal) {
				r = row;
				c = column - i;
				if (c < 0) return false;
			}else {
				r = row - i;
				c = column;
				if (r < 0) return false;
			}
			if (!(ships[r][c] instanceof EmptySea)) {
				return false;
			}
		}
		
		// The neighboring spots have to be either EmptySea or out of bound
		
		return true;
	}
	
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
	}
	
	boolean shootAt(int row, int column) {
		return true;
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

