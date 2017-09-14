package entity;

public class Size {
	
	private final int x, y;

	/**
	 * Creates a new Size of x, y. The horizontal / vertical parts are as seen
	 * if the object is not rotated (0 degrees of rotation)
	 * @param x the size of the horizontal part
	 * @param y the size of the vertical part
	 */
	public Size(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the width of the object in pixels
	 * @return the width of the object
	 */
	public int width() { return x; }
	
	/**
	 * Returns the height of the object in pixels
	 * @return the height of the object
	 */
	public int height() { return y; }

	/**
	 * Returns the area of the size, x * y 
	 * @return the area
	 */
	public int area() { return x*y; }

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null || o.getClass() == getClass()) return false;
		Size o2 = (Size)o;
		return o2.x == x && o2.y == y;
	}

	@Override
	public int hashCode() {
		return x * 17 + y * 23;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Size [x=");
		sb.append(x);
		sb.append(" pixels, y=");
		sb.append(y);
		sb.append(" pixels, area=");
		sb.append(area());
		sb.append(" pixels^2]");
		return sb.toString();
	}

}
