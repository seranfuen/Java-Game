package es.sergioangelverbo.model.entity;

import java.io.IOException;

import es.sergioangelverbo.graphic.TiledFrame;

/**
 * Ground entity. It is solid and is tiled
 * @author Sergio
 *
 */
public class Ground extends Entity {
	
	public enum Side { TOP, LEFT, RIGHT, BUTTON };
	
	private static final String dir = "sprites\\scenery";
	
	private static TiledFrame getGround1(Size size) {
		try {
			return new TiledFrame(dir + "\\ground_01.png", size);
		} catch (IOException e) {
			return null;
		}
	}

	public Ground(Position initialPos, int width, int height) {
		super(initialPos, new Size(width, height), getGround1(new Size(width, height)));
		setSolid(true);
	}
	
}
