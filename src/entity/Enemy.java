package entity;

import graphic.AnimationLibrary;

/**
 * Enemy entities should derive from Enemy
 * @author Sergio
 *
 */
public class Enemy extends Actor {
	
	private boolean killer = true;

	public Enemy(Position initpos, Size size, AnimationLibrary animations) {
		super(initpos, size, animations);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns true if the Enemy can kill the player, false otherwise
	 */
	public boolean canKill() {
		return killer;
	}
	
	/**
	 * Sets whether the enemy can kill the player
	 * @param value
	 */
	public void setKill(boolean value) {
		killer = value;
	}
	
	@Override
	public void kill(int time) {
		killer = false;
		stop();
		super.kill(time);
	}
}
