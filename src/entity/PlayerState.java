package entity;

import graphic.Animation;
import graphic.Frame;

/**
 * A class to implement the state of a player. The class has factory methods to
 * obtain a particular state and it also implements a finite state machine to
 * obtain the next state
 * 
 * @author Sergio Ángel Verbo
 *
 */
public class PlayerState implements IActorState {

	// The state (ie what power up was given)
	public enum State {
		SMALL, SUPER, RACCOON, FIRE, TANOOKI, FROG, SHOE, HAMMER
	}

	public enum Action {
		STAND
	}

	private Animation animation; // the current animation

	@Override
	public Frame getSprite(long interval) {
		return animation.getImage(interval);
	}
}