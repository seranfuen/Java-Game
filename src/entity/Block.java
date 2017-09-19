package entity;

import engine.ITrigger;
import graphic.Frame;

/**
 * Blocks represent entities that are squared, usually solid and which may
 * contain an object inside 
 * @author Sergio Ángel Verbo
 *
 */
public abstract class Block extends Entity implements ITrigger {
	
	// if it can contain something, flag setting whether it's been already
	// used
	protected boolean used = false;

	public Block(Position initialPos, Size size, Frame staticFrame, boolean solid) {
		super(initialPos, size, staticFrame);
		this.solid = solid;
	}
	
	abstract public void activate();
	
	

}
