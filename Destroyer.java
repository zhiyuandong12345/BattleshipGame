package battleship;

/**
 * It is a subclass of the abstract class Ship
 * It Represents a destroyer with the length of 2
 * @author RuidaX & ZhiyuanD
 */
public class Destroyer extends Ship{
	
	//constructor
	
	/**
	 * create a destroyer with the given length
	 */
	public Destroyer() {
		//calling the constructor in the Ship class to create a destroyer
		super(2);
	}

	/**
	 * This method override the getShipType method in the Ship class
	 * It gets the type of the ship
	 * @return the type of the ship which is "destroyer"
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}
	
	
	/**
	 * This method implements the getAlias method in the Ship class
	 * It is used for the printWithShips method
	 * @return a String "d" that represents the destroyer
	 */
	@Override
	public String getAlias() {
		return "d";
	}
}