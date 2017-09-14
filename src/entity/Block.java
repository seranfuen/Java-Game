package entity;

import graphic.Frame;

/**
 * Blocks represent entities that are squared, usually solid and which may
 * contain an object inside 
 * @author Sergio
 *
 */
public abstract class Block extends Entity {
	
	// if it can contain something, flag setting wheteher it's been already
	// used
	protected boolean used = false;

	public Block(Position initialPos, Size size, Frame staticFrame, boolean solid) {
		super(initialPos, size, staticFrame);
		this.solid = solid;
	}
	
	abstract public void activate();
	
	

}
