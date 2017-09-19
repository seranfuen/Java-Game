package entity;

import graphic.Animation;
import graphic.AnimationCreator;
import graphic.AnimationLibrary;
import graphic.Constants;
import helper.ExceptionHelper;

public class Coin extends Power {

	public Coin(Position initpos, Size size) {
		super(initpos, size, loadCoinAnimation());
		type = Type.COIN;
	}

	private static AnimationLibrary loadCoinAnimation() {
		try {
			AnimationCreator ac = new AnimationCreator(Constants.power);
			ac.addFrame("coin_01.png", 60);
			ac.addFrame("coin_02.png", 60);
			ac.addFrame("coin_03.png", 60);
			Animation ani = ac.createAnimation();
			ac.clear();
			return new AnimationLibrary(ani, ani, ani, ani, ani);
		} catch (Exception e) {
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
	
}
