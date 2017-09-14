package entity;

import graphic.Frame;

/**
 * The state of an actor determines the sprite to be returned next.
 * Implementing classes should take a reference to the actor and, upon 
 * creation (which determines that a state has been changed) it should modify, 
 * if necessary, the state of the actor
 * @author Sergio Ángel Verbo
 *
 */
public interface IActorState {

	/**
	 * Returns the next corresponding frame
	 * @param interval
	 * @return
	 */
	public Frame getSprite(long interval);
}
