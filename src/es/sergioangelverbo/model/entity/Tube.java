package es.sergioangelverbo.model.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import es.sergioangelverbo.graphic.Constants;
import es.sergioangelverbo.graphic.Frame;
import es.sergioangelverbo.graphic.TiledFrame;

public class Tube extends Entity {
	public enum Type { GREEN };
	
	private static final String dir = Constants.scenery;
	
	
	
	public static Tube getTube(Type type, Position initialPos, int height) {
		switch (type) {
		case GREEN:
			return new Tube(initialPos, new Size(96, height), 
					new TubeImages(
					dir + "/green_tube_top.png",
					dir + "/green_tube_body.png"));
		}
		return null;
	}
	
	private Tube(Position initialPos, Size size, TubeImages images) {
		super(initialPos, size, getTubeImage(size, images));
		setSolid(true);
	}

	private static Frame getTubeImage(Size size, TubeImages images) {
		Image img = new BufferedImage(size.width(), size.height(), 
				BufferedImage.TRANSLUCENT);
		try {
			Frame top = new Frame(images.getTop(), 0);
			Frame body = new TiledFrame(images.getBody(), 
					new Size(top.getSize().width()-6, size.height()-top.getSize().height()));
			
			Graphics g = img.getGraphics();
			g.drawImage(top.getImage(), 0, 0, top.getSize().width(), 
					top.getSize().height(), null);
			int x = (top.getSize().width()-body.getSize().width())/2;
			g.drawImage(body.getImage(), x, top.getSize().height(), 
					body.getSize().width(), body.getSize().height(), null);
			return new Frame(img, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
