package battleship;

/**
 * It is a subclass of the abstract class Ship
 * It Represents an emptySea
 * @author RuidaX & ZhiyuanD
 */
public class EmptySea extends Ship{
	
	/**
	 * This is a boolean variable that represent the location was shot but missed
	 * Only accessible from within this class
	 */
	private boolean shotAndMissed = false;
	
	//constructor
	
	/**
	 * create an emptySea with the given length
	 */
	public EmptySea() {
		//calling the constructor in the super class to create an emtpySea
		super(1);
	}
	
	/**
	 * it overrides the shootAt method in the Ship class 
	 * it always returns false to indicate that nothing was hit
	 */
	@Override
	public boolean shootAt(int row, int column) {
		//set the boolean value as true
		this.shotAndMissed = true;
		return false;
	}
	
	/**
	 * This method overrides i​sSunk method in the Ship class 
	 * always returns false to indicate that you didn’t sink anything
	 */
	@Override
	public boolean isSunk() {
		return false;
	}
	
	/**
	 * if the emptySea was shot but missed, return "-" String to use in the ​Ocean​’s print method
	 * otherwise, return "."
	 */
	@Override
	public String toString() {
		//if the emptySea was shot but missed
		if (this.shotAndMissed){
			return "-";
		}else {
			//if it was not shot, return "."
			return ".";
		}
	}
	
	/**
	 * This method just returns the string "empty"
	 */
	@Override
	public String getShipType() {
		return "empty";
	}
	
	/**
	 * This method returns the string "e"
	 * It helps the printWithShips method
	 */
	@Override
	public String getAlias() {
		return "e";
	}
	
}