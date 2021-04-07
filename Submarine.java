package battleship;

/**
 * It is a subclass of the abstract class Ship
 * It Represents a Submarine with the length of 1
 * @author RuidaX & ZhiyuanD
 */
public class Submarine extends Ship{
	
	//constructor
	
	/**
	 * create a submarine with the given length
	 */
	public Submarine() {
		//calling the constructor in the Ship class to create a submarine
		super(1);
	}
	
	/**
	 * This method override the getShipType method in the Ship class
	 * It gets the type of the ship
	 * @return the type of the ship which is "submarine"
	 */
	@Override
	public String getShipType() {
		return "submarine";
	}
	
	
	/**
	 * This method implements the getAlias method in the Ship class
	 * It is used for the printWithShips method
	 * @return a String "s" that represents the submarine
	 */
	@Override
	public String getAlias() {
		return "s";
	}
}