package graphic;

/**
 * An animation library is a container for Animation objects representing the
 * animation an entity can display. This class, intended to be subclassed,
 * offers three Animation fields: standingLeft, standingRight, walking left
 * and walking right.
 * Other types of animations may be added to subclasses
 * StandingLeft or StandingRight are used when the entity is standing and
 * looking to either direction
 * WalkingLEft or WalkingRight are used when the entity is moving to the left
 * or right
 * 
 * @author Sergio Ángel Verbo
 *
 */
public class AnimationLibrary {
	private Animation standingLeft, standingRight, walkingLeft, walkingRight,
		dying, jumpingRight, jumpingLeft;
	
	public AnimationLibrary(Animation standingLeft,
							Animation standingRight,
							Animation walkingLeft,
							Animation walkingRight,
							Animation dying) {
		
		this.standingLeft = standingLeft;
		this.standingRight = standingRight;
		this.walkingLeft = walkingLeft;
		this.walkingRight = walkingRight;
		this.jumpingLeft = standingLeft;
		this.jumpingRight = standingRight;
		this.dying = dying;
	}
	
	public AnimationLibrary(Animation standingLeft,
			Animation standingRight,
			Animation walkingLeft,
			Animation walkingRight,
			Animation dying,
			Animation jumpingLeft,
			Animation jumpingRight) {
		this(standingLeft, standingRight, walkingLeft, walkingRight, dying);
		this.jumpingLeft = jumpingLeft;
		this.jumpingRight = jumpingRight;
	}
	
	/**
	 * Gets the animation for the entity standing and looking to the left
	 * @return animation for entity standing and looking to the left
	 */
	public Animation getStandingLeft() {
		return standingLeft;
	}
	
	/**
	 * Gets the animation for the entity standing and looking to the right
	 * @return animation for entity standing and looking to the right
	 */	
	public Animation getStandingRight() {
		return standingRight;
	}
	
	/**
	 * Gets the animation for the entity walking leftwards
	 * @return animation for entity walking leftwards
	 */	
	public Animation getWalkingLeft() {
		return walkingLeft;
	}
	
	/**
	 * Gets the animation for the entity walking rightwards
	 * @return animation for entity walking rightwards
	 */		
	public Animation getWalkingRight() {
		return walkingRight;
	}
	
	public Animation getDying() {
		return dying;
	}
	
	public Animation getJumpingLeft() {
		return jumpingLeft;
	}
	
	public Animation getJumpingRight() {
		return jumpingRight;
	}
}
