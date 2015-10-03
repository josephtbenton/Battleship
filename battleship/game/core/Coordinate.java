package game.core;

/**
 * Created by Eric on 10/2/2015.
 */
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int X, int Y) {
		x = X;
		y = Y;
	}
	
	
	public String toString() {
		if (getX() == 0) {
			return "A" + (getY() + 1);
		}
		if (getX() == 1) {
			return "B" + (getY() + 1);
		}
		if (getX() == 2) {
			return "C" + (getY() + 1);
		}
		if (getX() == 3) {
			return "D" + (getY() + 1);
		}
		if (getX() == 4) {
			return "E" + (getY() + 1);
		}
		if (getX() == 5) {
			return "F" + (getY() + 1);
		}
		if (getX() == 6) {
			return "G" + (getY() + 1);
		}
		if (getX() == 7) {
			return "H" + (getY() + 1);
		}
		if (getX() == 8) {
			return "I" + (getY() + 1);
		}
		if (getX() == 9) {
			return "J" + (getY() + 1);
		}
		return "" + getX() + getY();
	}
	
	public Boolean equals(Coordinate other) {
		if (this.getX() == other.getX() && this.getY() == other.getY()) {
			return true;
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Boolean isLegal() {
		if (getX() < 0 || getX() > 9 || getY() < 0 || getY() > 9) {
			return false;
		}
		return true;
	}
}
