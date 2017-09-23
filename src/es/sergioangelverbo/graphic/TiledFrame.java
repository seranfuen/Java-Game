package es.sergioangelverbo.graphic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import es.sergioangelverbo.model.entity.Size;

public class TiledFrame extends Frame {
	
	private Image original;
	private Image nframe;
	
	public TiledFrame(String file, Size size) throws IOException {
		super(file, 0);
		nframe = new BufferedImage(size.width(), size.height(), 
				BufferedImage.TYPE_INT_RGB);
		original = frame;
		Graphics g = nframe.getGraphics();
		int oheight = original.getHeight(null);
		int owidth = original.getWidth(null);
		for (int i  = 0; i < size.width(); i+= owidth) {
			for (int j = 0; j < size.height(); j += oheight) {
				int w = i + owidth > size.width() ? size.width() - i : owidth;
				int h = j + oheight > size.height() ? size.height() - j : oheight;
				g.drawImage(original, i, j, w, h, null);
				System.out.println("w: " + w + " h: " + h);
			}
		}
		frame = nframe;
	}

}
