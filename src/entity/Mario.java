package entity;

import java.io.IOException;

import graphic.Animation;
import graphic.AnimationCreator;
import graphic.AnimationLibrary;
import helper.ExceptionHelper;

public class Mario extends Player {
	
	private static final String dir = "sprites\\mario";
	private static final int animationSpeed = 80;
	private static final int marioWalkingSpeed = 250;
	private static final int marioRunningSpeed = 450;

	public Mario(Position initpos) {
		super(initpos, new Size(40, 77), loadAnimations());
		walkingSpeed = marioWalkingSpeed;
		runningSpeed = marioRunningSpeed;
	}

	private static AnimationLibrary loadAnimations() {
		return new AnimationLibrary(getStandingLeft(),
				getStandingRight(),
				getWalkingLeft(),
				getWalkingRight(),
				getWalkingRight(),
				getJumpingLeft(),
				getJumpingRight());
	}
	
	private static Animation getStandingRight() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			return ac.getStaticAnimation("mario_big_right_standing.png");
		} catch (IOException e) {
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
	
	private static Animation getStandingLeft() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			return ac.getStaticAnimation("mario_big_left_standing.png");
		} catch (IOException e) {
			ExceptionHelper.ShowExceptionClose(e);
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
			ExceptionHelper.ShowExceptionClose(e);
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
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
	
	private static Animation getJumpingLeft() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			return ac.getStaticAnimation("mario_big_left_jump.png");
		} catch (IOException e) {
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
	
	private static Animation getJumpingRight() {
		AnimationCreator ac = new AnimationCreator(dir);
		try {
			return ac.getStaticAnimation("mario_big_right_jump.png");
		} catch (IOException e) {
			ExceptionHelper.ShowExceptionClose(e);
			return null;
		}
	}
}
