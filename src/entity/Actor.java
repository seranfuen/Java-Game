package entity;

import java.util.ArrayList;
import java.util.List;

import graphic.Animation;
import graphic.AnimationLibrary;
import graphic.Frame;

/**
 * An actor is an entity that can move (left or right), optionally jump and may fall
 * 
 * @author Sergio Ángel Verbo
 */
public class Actor extends Entity {
	
	public enum Direction { LEFT, RIGHT };
	
	private static final int defaultJumpSpeed = -800;
	
	private boolean isRunning = false;
	private List<IKilledListener> killedListeners;
	// Flag to know the actor is set to die when dying counter runs out
	private boolean dying = false;
	private int dyingCounter = -1;
	
	protected Direction direction = Direction.LEFT;
	protected int walkingSpeed = 200;
	protected int runningSpeed = 400;
	protected Animation currentAnimation;
	protected AnimationLibrary animationLibrary;
	
	// Sets whether the actor can ever jump or not
	protected boolean jumpable = true;
	
	// If jumpable is true, setting this to false can momentarily disable jumps for the actor
	protected boolean canJump = true; 
	protected boolean canMove = true;
	
	public Actor(Position initpos,
				 Size size,
				 AnimationLibrary animations) {
		super(initpos, size);
		killedListeners = new ArrayList<IKilledListener>();
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
		for (IKilledListener listener : killedListeners) listener.killed(this);
		if (time <= 0) this.kill();
		dying = true;
		dyingCounter = time;
	}
	
	public void addKilledListener(IKilledListener listener) {
		killedListeners.add(listener);
	}

	/**
	 * Sets the actor to fall with accelerating speed by gravity, and disallows
	 * jumping
	 */
	public void fall() {
		setAcceleration(0, getGravity());
		canJump = false;
	}
	
	public boolean isRunning() {
		return isRunning;
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
		jump(getJumpSpeed());
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
	
	public int getScoredKilled() {
		return 0;
	}
	
	public void setWalkingSpeed() {
		isRunning = false;
		updateSpeed();
	}
	
	public void setRunningSpeed() {
		isRunning = true;
		updateSpeed();
	}
	
	private void updateSpeed() {
		if (isMovingHorizontal() && !isMovingVertical()) {
			if (direction == Direction.RIGHT) {
				moveRight();
			} else {
				moveLeft();
			}
		}
	}
	
	protected boolean isMovingHorizontal() {
		return speed().getHorizontal() != 0;
	}
	
	protected boolean isMovingVertical() {
		return speed().getVertical() != 0;
	}
	
	public int getCurrentReachableSpeed() {
		return isRunning ? runningSpeed : walkingSpeed;		
	}
	
	/**
	 * Sets the actor to move to the left
	 */
	public void moveLeft() {
		setDirection(Direction.LEFT);
		if (canMove) {
			setSpeed(-getCurrentReachableSpeed(), speed().getVertical());
			setAnimation();
		}
	}
	
	/**
	 * Sets the actor to move to the right
	 */
	public void moveRight() {
		setDirection(Direction.RIGHT);
		if (canMove) {
			setSpeed(getCurrentReachableSpeed(), speed().getVertical());
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
	
	protected int getJumpSpeed() {
		return defaultJumpSpeed;
	}
}