package battleship;

/**
 * It is a subclass of the abstract class Ship
 * It Represents a Battleship with the length of 4
 * @author RuidaX & ZhiyuanD
 */
public class Battleship extends Ship {

	//constructor

	/**
	 * create a battleship with the given length
	 */
	public Battleship() {
		//calling the constructor in the Ship class to create a Battleship
		super(4);
	}

	/**
	 * This method override the getShipType method in the Ship class
	 * It gets the type of the ship
	 * @return the type of the ship which is "battleship"
	 */
	@Override
	public String getShipType() {
		return "battleship";
	}


	/**
	 * This method implements the getAlias method in the Ship class
	 * It is used for the printWithShips method
	 * @return a String "b" that represents the battleship
	 */
	@Override
	public String getAlias() {
		return "b";
	}



}