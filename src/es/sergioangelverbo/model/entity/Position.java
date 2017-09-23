package es.sergioangelverbo.model.entity;

/**
 * A position (x, y) from the coordinate origin
 * @author Sergio �ngel Verbo
 *
 */
public class Position {
	
	private final int x, y;
	
	/**
	 * Creates a new position object
	 * @param x the x coordinate (horizontal)
	 * @param y the y coordinate (vertical)
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x coordinate
	 * @return the x coordinate
	 */
	public int horizontal() { return x; }
	
	/**
	 * Returns the y coordinate
	 * @return the y coordinate
	 */
	public int vertical() { return y; }
	
	/**
	 * Performs an addition of the calling object with the parameter
	 * @param position the position to add to the current position
	 * @return the addition of calling object + position
	 */
	public Position add(Position position) {
		return new Position(x + position.horizontal(), y + position.vertical());
	}
	
	@Override
	public int hashCode() { return 19 * x + 17 * y; }
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		Position p = (Position)o;
		return p.x == x && p.y == y;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Position [x=");
		sb.append(x);
		sb.append(", y=");
		sb.append(y);
		sb.append("]");
		return sb.toString();
	}
	
}
