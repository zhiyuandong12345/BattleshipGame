package battleship;

public class EmptySea extends Ship{
	
	private boolean shotAndMissed = false;
	
	public EmptySea() {
		super(1);
	}
	
	@Override
	public boolean shootAt(int row, int column) {
		this.shotAndMissed = true;
		return false;
	}
	
	@Override
	public boolean isSunk() {
		return false;
	}
	
	@Override
	public String toString() {
		if (this.shotAndMissed){
			return "-";
		}else {
			return ".";
		}
	}
	
	@Override
	public String getShipType() {
		return "empty";
	}
	
	public String getAlias() {
		return "e";
	}
	
	
}