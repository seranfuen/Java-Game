package entity;

import engine.GameSettings;

/**
 * Defines the speed of an entity for the two axes. If positive, for the x axis
 * it means going to the right, for the y axis going down. If negative, for the
 * x axis it means going to the left, for the y axis going up
 * @author Sergio Ángel Verbo
 *
 */
public class Speed {
	
	/**
	 * Returns a speed representing no movement (0,0)
	 * @return zero speed
	 */
	public static Speed zeroSpeed() {
		return new Speed(0,0);
	}

	private final double x, y;
	
	/**
	 * Creates a new object with the speeds given in pixels/second
	 * @param x the speed for the horizontal axis. If positive, it indicates
	 * movement to the right, if negative to the left, if 0 no movement
	 * @param y the speed for the vertical axis. If positive it indicates
	 * movement downwards, if negative upwards, if 0 no movement
	 */
	public Speed(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Adds the horizontal and vertical values of the caller and the parameter
	 * and returns a new speed object with the result
	 * @param speed the speed to add to the current speed
	 * @return calling object + speed
	 */
	public Speed add(Speed speed) {
		return new Speed(x + speed.getHorizontal(), y + speed.getVertical());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null || o.getClass() != getClass()) return false;
		Speed o2 = (Speed)o;
		return o2.getHorizontal() == getHorizontal() &&
				o2.getVertical() == getVertical();
	}
	
	/**
	 * Returns the horizontal speed
	 * @return the horizontal speed in pixels/second
	 */
	public double getHorizontal() { return x; }
	
	/**
	 * Returns the vertical speed
	 * @return the vertical speed in pixels/second
	 */
	public double getVertical() { return y; }
	
	
	@Override
	public int hashCode() {
		return 17 * Double.valueOf(x).hashCode() + 23 
				* Double.valueOf(y).hashCode();
	}
	
	/**
	 * Returns a new Speed object with reverted horizontal speed
	 */
	public Speed invertHorizontal() {
		return new Speed(-x, y);
	}
	
	/**
	 * Returns a new Speed object with reverted horizontal speed
	 */
	public Speed invertVertical() {
		return new Speed(x, -y);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Speed [x=");
		sb.append(x);
		sb.append(" pixels/s, y=");
		sb.append(y);
		sb.append(" pixels/s]");
		return sb.toString();
	}
	
	/**
	 * Returns a new Speed object 
	 * @param acc
	 * @param lapse
	 * @return
	 */
	public Speed updateSpeed(Acceleration acc, long lapse) {
		int x, y;
		x = (int)(this.x + Math.round((lapse/1000)*acc.getHorizontal()) + 
				Math.round((lapse%1000) / (1000/acc.getHorizontal())));
		y = (int) (this.y + Math.round((lapse/1000)*acc.getVertical()) + 
				Math.round((lapse%1000) / (1000/acc.getVertical())));
		return new Speed(x, y);
	}
	
	/**
	 * The number of ms that elapse between the advancement of each x. If 
	 * 0 < x < 1, then more than one pixel advances each ms
	 * @return
	 */
	public double xPixelMs() {
		return 1000.0 / x;
	}
	
	/**
	 * The number of ms that elapse between the advancedment of each y. If 
	 * 0 < x < 1, then more than one pixel advances each ms
	 * @return
	 */
	public double yPixelMs() {
		return 1000.0 / y;
	}
	
}
