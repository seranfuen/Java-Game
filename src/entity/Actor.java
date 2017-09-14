package entity;

import engine.GameSettings;
import graphic.Animation;
import graphic.AnimationLibrary;
import graphic.Frame;

/**
 * An actor is an Entity that can move to the left or to the right and that can
 * fall downwards
 * @author Sergio Ángel Verbo
 */
public class Actor extends Entity {
	public enum Direction { LEFT, RIGHT };
	
	protected Animation currentAnimation;
	protected AnimationLibrary animationLibrary;
	protected int walkingSpeed = 200;
	protected boolean jumpable = true; // if false, jump will perform nothing
	
	// Do not confuse with jumpable. Sometimes a jumpable actor cannot perform
	// a jump at a particular moment. Jumpable disables jumping, canJump disables
	// it temporarily
	protected boolean canJump = true; 
	// If false, the actor is not allowed to walk (the speed is set to Zero in
	// all cases). It may still change the direction it's facing
	protected boolean canMove = true;
	
	// Flag to know the actor is set to die when dying counter runs out
	private boolean dying = false;
	private int dyingCounter = -1;
	
	
	// The direction where the actor is facing
	protected Direction direction = Direction.LEFT;
	
	public Actor(Position initpos,
				 Size size,
				 AnimationLibrary animations) {
		super(initpos, size);
		animationLibrary = animations;
		setDirection(Direction.RIGHT);
		setAnimation();
		setAcceleration(0, 0);
		setSolid(true);
	}
	
	public Actor(Position initpos, Size size, AnimationLibrary animations, Direction direction) {
		this(initpos, size, animations);
		setDirection(direction);
	}

	@Override
	public void update(long elapsed) {
		super.update(elapsed);
		if (dying) {
			dyingCounter -= elapsed;
			if (dyingCounter <= 0) {
				dying = false;
				setEnabled(false);
			}
		}
	}


	public void kill() {
		setEnabled(false);
	}
	
	/**
	 * Sets the actor to die (be set disabled) after the time in ms has elapsed.
	 * It is used when displaying some kind of animation before killing it,
	 * or if it is bound by some kind of counter
	 * @param time the time before it is killed. If <= 0, it dies instantly
	 */
	public void kill(int time) {
		if (time <= 0) this.kill();
		dying = true;
		dyingCounter = time;
	}

	
	/**
	 * Sets the actor to fall with accelerating speed by gravity, and disallows
	 * jumping
	 */
	public void fall() {
		setAcceleration(0, getGravity());
		canJump = false;
	}
	
	/**
	 * Gets the current direction the actor is facing
	 * @return
	 */
	public Direction getDirection() { return direction; }

	
	@Override
	public Frame getSprite(long duration) {
		return currentAnimation.getImage(duration);
	}
	
	@Override
	public boolean isStatic() { return false; }
	
	/**
	 * Sets the actor to perform a jump if it is jumpable
	 */
	public void jump() {
		jump(-700);
	}
	
	public void jump(int initialUpSpeed) {
		if (initialUpSpeed > 0) {
			initialUpSpeed = -initialUpSpeed;
		}
		if (jumpable && canJump) {
			setSpeed(speed().getHorizontal(), initialUpSpeed);
			setAcceleration(0, getGravity());
			canJump = false;
		}
	}
	
	/**
	 * Sets the actor to move to the left
	 */
	public void moveLeft() {
		setDirection(Direction.LEFT);
		if (canMove) {
			setSpeed(-walkingSpeed, speed().getVertical());
			setAnimation();
		}
	}
	
	/**
	 * Sets the actor to move to the right
	 */
	public void moveRight() {
		setDirection(Direction.RIGHT);
		if (canMove) {
			setSpeed(walkingSpeed, speed().getVertical());
			setAnimation();
		}
	}
	
	/**
	 * Reverses the horizontal motion from right to left or left to right
	 */
	public void reverseHorizontal() {
		if (direction == Direction.RIGHT) {
			moveLeft();
		} else if (direction == Direction.LEFT) {
			moveRight();
		}
	}
	
	/**
	 * Allows the actor to jump again
	 */
	public void resetJump() {
		canJump = true;
	}
	
	/**
	 * Sets the correct animation depending on speed and direction
	 */
	public void setAnimation() {
		switch (direction) {
		case LEFT:
			if (speed().getHorizontal() == 0) {
				setAnimation(animationLibrary.getStandingLeft());
			} else {
				setAnimation(animationLibrary.getWalkingLeft());
			}
		break;
		case RIGHT:
			if (speed().getHorizontal() == 0) {
				setAnimation(animationLibrary.getStandingRight());
			} else {
				setAnimation(animationLibrary.getWalkingRight());
			}
			break;
		}
	}
	
	/**
	 * Sets the current animation to the animation object given
	 * @param animation the animation
	 */
	public void setAnimation(Animation animation) {
		currentAnimation = animation; 
	}
	
	/**
	 * Sets the direction for the entity. It will modify the direction of the
	 * horizontal speed vector but will not modify its value
	 * @param dir the new direction to face
	 */
	public void setDirection(Direction dir) {
		direction = dir;
		
		//TODO: modify speed vector
		setAnimation();
	}
	
	
	
	/**
	 * Stops the movement of the object on its horizontal axis
	 */
	public void stop() {
		setSpeed(0, speed().getVertical());
		setAnimation();
	}
	
	/**
	 * Stops the fall of the object and places at the altitude given
	 * @param altitude
	 */
	public void stopFall(int altitude) {
		stopFall();
		setPosition(position.horizontal(), altitude);
		System.out.println("HERE " + acceleration);
	}
	
	/**
	 * Stops the movement of the object on its vertical axis and allows
	 * jumping again
	 */
	public void stopFall() {
		setAcceleration(acceleration.getHorizontal(), 0);
		setSpeed(speed().getHorizontal(), 0);
		canJump = true;
	}
}
