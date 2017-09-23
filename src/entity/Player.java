package entity;

import engine.GameSettings;
import graphic.AnimationLibrary;

public class Player extends Actor {

	private final static int bumpSpeed = -300;
	private int maxJumpExtensionMs;
	private int maxJumpExtension;
	
	private int remainingJumpExtensionSpeed = 0;
	
	public Player(Position initpos, Size size, AnimationLibrary animations) {
		super(initpos, size, animations);
		maxJumpExtension = GameSettings.getGameSettings().getMaxJumpExtension();
		maxJumpExtensionMs = GameSettings.getGameSettings().getMaxJumpExtensionMs();
	}

	/***
	 * Performs a "bump" (a small jump) after stomping on an enemy
	 */
	public void bump() {
		setSpeed(speed().getHorizontal(), bumpSpeed);
	}
	
	@Override
	public void jump(int speed) {
		remainingJumpExtensionSpeed = maxJumpExtension;
		super.jump(speed);
	}
	
	public void extendJump(long msEllapsed) {
		double jumpExtension = Math.min(remainingJumpExtensionSpeed, ((double)msEllapsed / (double)maxJumpExtensionMs) * (double)maxJumpExtension);
		if (jumpExtension > 0) {
			setSpeed(speed().getHorizontal(), speed().getVertical() - jumpExtension);
		}
		remainingJumpExtensionSpeed -= jumpExtension;
	}
}