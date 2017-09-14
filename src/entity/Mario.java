package entity;

import java.io.IOException;

import graphic.Animation;
import graphic.AnimationCreator;
import graphic.AnimationLibrary;

public class Mario extends Player {
	
	private static final String dir = "sprites\\mario";
	private static final int animationSpeed = 80;

	public Mario(Position initpos) {
		super(initpos, new Size(40, 77), loadAnimations());
		// TODO Auto-generated constructor stub
		walkingSpeed = 330;
	}

	/**
	 * Loads the animation library for Mario
	 * @return animation library for Mario
	 */
	private static AnimationLibrary loadAnimations() {
		return new AnimationLibrary(getStandingLeft(),
				getStandingRight(),
				getWalkingLeft(),
				getWalkingRight(),
				getWalkingRight());
	}
	
	private static Animation getStandingRight() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			return ac.getStaticAnimation("mario_big_right_standing.png");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Animation getStandingLeft() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			return ac.getStaticAnimation("mario_big_left_standing.png");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Animation getWalkingRight() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			ac.addFrame("mario_big_right_standing.png", animationSpeed);
			ac.addFrame("mario_big_right_walk_01.png", animationSpeed);
			ac.addFrame("mario_big_right_walk_02.png", animationSpeed);
			ac.addFrame("mario_big_right_walk_01.png", animationSpeed);
			return ac.createAnimation();
		} catch (IOException e) {
			System.out.println(System.getProperty(("user.dir")));
			e.printStackTrace();
			return null;
			
		}
	}
	
	private static Animation getWalkingLeft() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			ac.addFrame("mario_big_left_standing.png", animationSpeed);
			ac.addFrame("mario_big_left_walk_01.png", animationSpeed);
			ac.addFrame("mario_big_left_walk_02.png", animationSpeed);
			ac.addFrame("mario_big_left_walk_01.png", animationSpeed);
			return ac.createAnimation();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
