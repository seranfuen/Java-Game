package entity;

import java.io.IOException;

import graphic.Animation;
import graphic.AnimationCreator;
import graphic.AnimationLibrary;

public class Goomba extends Enemy {

	public Goomba(Position initpos) {
		super(initpos, new Size(44, 44), getAnimation());
		walkingSpeed = 110;
	}

	private static AnimationLibrary getAnimation() {
		final int frameSpeed = 180;
		AnimationCreator ac = new AnimationCreator("sprites\\goomba");
		try {
			Animation standingLeft = ac.getStaticAnimation("goomba_01.png");
			Animation standingRight = ac.getStaticAnimation("goomba_02.png");
			ac.addFrame("goomba_01.png", frameSpeed);
			ac.addFrame("goomba_02.png", frameSpeed);
			Animation walking = ac.createAnimation();
			return new AnimationLibrary(standingLeft, standingRight, walking, walking, walking);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void kill() {
		solid = false;
		flicker(160, 1500);
		super.kill(1500);
	}

}
