package es.sergioangelverbo.graphic;

import java.awt.Graphics;
import java.awt.Image;

import es.sergioangelverbo.model.entity.Position;

/**
 * Paints the frame for which it was constructed
 * @author Sergio �ngel Verbo
 *
 */
public class PainterFrame implements IPainter {
	
	private Frame frame;
	
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void paint(Graphics g, Position position) {
		if (frame != null && position != null) {
			Image img = frame.getImage();
			if (img == null) return;
			g.drawImage(img, position.horizontal(), position.vertical(), img.getWidth(null),
					img.getHeight(null), null, null);
					
		}

	}

}
