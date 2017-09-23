package es.sergioangelverbo.model.base;

/**
 * Represents an acceleration that changes a particular speed, measured in
 * pixels / s^2
 * @author Sergio Ángel Verbo
 *
 */
public class Acceleration {
	
	public static Acceleration zeroAcceleration() {
		return new Acceleration(0,0);
	}
	
	private final double x, y;
	
	/**
	 * @param x the horizontal acceleration in pixels s^2
	 * @param y the vertical acceleration in pixels s^2
	 */
	public Acceleration(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getHorizontal() {
		return x;
	}
	
	public double getVertical() {
		return y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null || o.getClass() != getClass()) return false;
		Acceleration o2 = (Acceleration)o;
		return o2.getHorizontal() == getHorizontal() &&
				o2.getVertical() == getVertical();
	}
	
	@Override
	public int hashCode() {
		return 17 * Double.valueOf(x).hashCode() +
				19 * Double.valueOf(y).hashCode();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Acceleration [x=");
		sb.append(x);
		sb.append(" pixels/s^2, y=");
		sb.append(y);
		sb.append(" pixels/s^2]");
		return sb.toString();
	}
}
