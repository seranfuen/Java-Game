package graphic;

import java.awt.Graphics;

import entity.Position;

/**
 * Interface for objects that take something as argument -for example, a
 * Frame object- and paint it at some position within the Graphics object
 * passed
 * @author Sergio
 *
 */
public interface IPainter {
	/**
	 * Paints something in the graphics object at the position specified
	 * @param g
	 */
	public void paint(Graphics g, Position position);
}
