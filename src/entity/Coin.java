package entity;

import graphic.Animation;
import graphic.AnimationCreator;
import graphic.AnimationLibrary;
import graphic.Constants;
import helper.ExceptionHelper;

public class Coin extends Power {
	
	private static final int jumpSpeed = -600;
	private int initialVerticalPos;
	
	public Coin(Position initpos) {
		super(initpos, new Size(24, 42), loadCoinAnimation());
		initialVerticalPos = initpos.vertical();
		type = Type.COIN;
		jump(jumpSpeed);
	}
	
	@Override
	public void onUpdated() {
		if (position.vertical() >= initialVerticalPos) {
			kill();
		}
	}

	private static AnimationLibrary loadCoinAnimation() {
		try {
			AnimationCreator ac = new AnimationCreator("sprites\\" + Constants.power);
			ac.addFrame("coin_01.png", 60);
			ac.addFrame("coin_02.png", 60);
			ac.addFrame("coin_03.png", 60);
			Animation ani = ac.createAnimation();
			return new AnimationLibrary(ani, ani, ani, ani, ani);
		} catch (Exception e) {
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
}
