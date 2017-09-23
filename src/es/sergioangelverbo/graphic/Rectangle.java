package es.sergioangelverbo.graphic;

import java.awt.Color;
import java.awt.image.BufferedImage;

import es.sergioangelverbo.model.entity.Size;

public class Rectangle extends Frame {
	
	private Color color;
	
	/**
	 * Sets the image to a rectangle	
	 * @param x
	 * @param y
	 */
	public Rectangle(int x, int y, Color color) {
		super(0);
		frame = new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);
		frame.getGraphics().setColor(color);
		frame.getGraphics().drawRect(0, 0, x, y);
		this.color = color;
		size = new Size(x, y);
	}
	
	public Color getColor() { return color; }
}
