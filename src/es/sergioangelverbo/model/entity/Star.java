package es.sergioangelverbo.model.entity;

import es.sergioangelverbo.graphic.Animation;
import es.sergioangelverbo.graphic.AnimationCreator;
import es.sergioangelverbo.graphic.AnimationLibrary;
import es.sergioangelverbo.graphic.Constants;
import es.sergioangelverbo.graphic.Frame;
import es.sergioangelverbo.helper.ExceptionHelper;

public class Star extends Power {
	public Star(Position initialPosition) {
		super(initialPosition, new Size(48, 48), loadStarAnimation());
		moveRight();
		walkingSpeed = 90;
		type = Type.STAR;
	}
	
	@Override
	public void jump() {
		jump(700);
	}
	
	/**
	 * Overrides stopFall from Actor, initiating the jump again
	 */
	@Override
	public void stopFall() {
		super.stopFall();
		this.jump();
	}

	private static AnimationLibrary loadStarAnimation() {
		try {
			AnimationCreator ac = new AnimationCreator(Constants.power);
			ac.addFrame("star_01.png", 60);
			ac.addFrame("star_02.png", 60);
			Animation ani = ac.createAnimation();
			ac.clear();
			return new AnimationLibrary(ani, ani, ani, ani, ani);
		} catch (Exception e) {
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
}
