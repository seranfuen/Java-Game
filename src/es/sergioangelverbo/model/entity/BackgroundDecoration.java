package es.sergioangelverbo.model.entity;

import es.sergioangelverbo.graphic.Constants;
import es.sergioangelverbo.graphic.Frame;

/**
 * A background decoration that does not interact with anybody
 * @author Sergio
 *
 */
public class BackgroundDecoration extends Entity {
	

	public enum Type { SINGLE_CLOUD, THREE_CLOUDS };
	
	public BackgroundDecoration(Position initialPos, Type type) {
		super(initialPos, loadImage(type).getSize(), loadImage(type));
		setSolid(false);
	}

	private static Frame loadImage(Type type) {
		try {
			switch (type) {
			case SINGLE_CLOUD:
				return new Frame(Constants.scenery + "/cloud_1.png", 0);
			case THREE_CLOUDS:
				return new Frame(Constants.scenery+"/cloud_2.png", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
