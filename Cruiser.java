package battleship;


/**
 * It is a subclass of the abstract class Ship
 * It Represents Cruiser with the length of 3
 * @author RuidaX & ZhiyuanD
 */
public class Cruiser extends Ship{
	
	//constructor
	/**
	 * create a cruiser with the given length
	 */
	public Cruiser() {
		//calling the constructor in the Ship class to create a cruiser
		super(3);
	}
	
	/**
	 * This method override the getShipType method in the Ship class
	 * It gets the type of the ship
	 * @return the type of the ship which is "cruiser"
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}
	
	
	/**
	 * This method implements the getAlias method in the Ship class
	 * It is used for the printWithShips method
	 * @return a String "C" that represents the cruiser
	 */
	@Override
	public String getAlias() {
		return "c";
	}
}
