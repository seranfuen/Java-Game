package entity;

import java.io.IOException;

import graphic.Animation;
import graphic.AnimationCreator;
import graphic.AnimationLibrary;
import graphic.Constants;

/**
 * Represetns power ups
 * @author Sergio
 *
 */
public class Power extends Actor {
	
	public enum Type { COIN, EMPTY, MUSHROOM, STAR }
	private static final Size coinSize = new Size(24, 42);
	
	private static final Size mushroomSize = new Size(48, 48);
	private static final AnimationCreator ac = 
			new AnimationCreator(Constants.power);

	private static AnimationLibrary coinAnimationLibrary() {
		
		try {
			ac.addFrame("coin_01.png", 90);
			ac.addFrame("coin_02.png", 90);
			ac.addFrame("coin_03.png", 90);
			ac.addFrame("coin_02.png", 90);
			Animation ani = ac.createAnimation();
			ac.clear();
			return new AnimationLibrary(ani, ani, ani, ani, ani);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static AnimationLibrary getAnimations(Type type) {
		switch (type) {
		case COIN:
			return coinAnimationLibrary();
		case MUSHROOM:
			return mushroomAnimationLibrary();
		}
		return null;
	}
	
	private static AnimationLibrary mushroomAnimationLibrary() {
		Animation a = null;
		try {
			a = ac.getStaticAnimation("mushroom_01.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AnimationLibrary(a, a, a, a, a);
	}

	private boolean destr = false; // destroy on contact with player?


	/**
	 * If positive, destroy power up after its height becomes greater than
	 * maxHeight. Used for coins that must be destroyed
	 */
	private int maxHeight = -1;
	
	protected Type type;
	
	/**
	 * Factory method to return powers of the type and at the position specified
	 * @param initpos
	 * @param type
	 * @return
	 */
	public static Power getPower(Position initpos, Power.Type type) {
		switch (type) {
		case STAR:
			return new Star(initpos);
		default:
			return new Power(initpos, type);
		}
		
	}
	
	/**
	 * Constructor for subclasses that have already loaded an animation library
	 * @param initpos
	 * @param size
	 * @param animations
	 */
	protected Power(Position initpos, Size size, AnimationLibrary animations) {
		super(initpos, size, animations, Actor.Direction.RIGHT);
	}


	private Power(Position initpos, Power.Type type) {
		super(initpos, coinSize, getAnimations(type), Actor.Direction.RIGHT);
		walkingSpeed = 100;
		this.type = type;
		switch (type) {
		case COIN:
			setGravity(1900);
			jump(-900);
			setMaxHeight(initpos.vertical()-20);
			break;
		case MUSHROOM:
			moveRight();
			setSize(mushroomSize);
			break;
		default:
			setSolid(true);
			destr = true;
		}
	}


	/**
	 * Tells the collision engine if the power object should be destroyed
	 * when it collides with the player (eg mushrooms) or not (eg coins)
	 * @return
	 */
	public boolean destroyWithPlayer() {
		return destr;
	}; // power up types
	public Power.Type getType() {
		return type;
	}
	/**
	 * Sets the maximum height (usually the same as the block where it was
	 * contained) after which it must be destroyed
	 * @param height
	 */
	public void setMaxHeight(int height) {
		maxHeight = height;
	}
	
	@Override
	public void update(long lapse) {
		super.update(lapse);
		if (position().vertical() >= maxHeight && maxHeight > 0 && 
				speed().getVertical() > 0) {
			setEnabled(false);
		}
	}
	
}
