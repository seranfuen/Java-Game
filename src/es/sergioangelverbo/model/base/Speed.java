package es.sergioangelverbo.model.base;

/**
 * Defines the speed of an entity for the two axes. 
 * For x axis, if > 0, it means going to the right; if < 0, it means going to the left
 * For y axis, if > 0, it means going downwards, if < 0, it means going upwards
 * @author Sergio Ángel Verbo
 *
 */
public class Speed {
	
	private final double x, y;
	
	public static Speed zeroSpeed() {
		return new Speed(0,0);
	}

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
	
	public double getHorizontal() { return x; }

	public double getVertical() { return y; }
	
	public Speed updateSpeedByAcceleration(Acceleration acc, long lapse) {
		double x, y;
		x = updateSpeedAxis(this.x, acc.getHorizontal(), lapse);
		y = updateSpeedAxis(this.y, acc.getVertical(), lapse);
		return new Speed(x, y);
	}
	
	private double updateSpeedAxis(double speed, double acceleration, long lapse) {
		return  speed + lapse/1000d * acceleration;
	}
	
	/**
	 * The number of ms that elapse between the advancement of each horizontal pixel. If 
	 * 0 < x < 1, then more than one pixel advances each ms
	 * @return
	 */
	public double xPixelMs() {
		return 1000.0 / x;
	}
	
	/**
	 * The number of ms that elapse between the advancement of each vertical pixel. If 
	 * 0 < x < 1, then more than one pixel advances each ms
	 * @return
	 */
	public double yPixelMs() {
		return 1000.0 / y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null || o.getClass() != getClass()) return false;
		Speed o2 = (Speed)o;
		return o2.getHorizontal() == getHorizontal() &&
				o2.getVertical() == getVertical();
	}
	
	@Override
	public int hashCode() {
		return 17 * Double.valueOf(x).hashCode() + 23 
				* Double.valueOf(y).hashCode();
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
}
