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
		dying;
	
	/**
	 * Constructs a new Animation Library
	 * @param standingLeft the animation for standing looking to the left
	 * @param standingRight the animation for standing looking to the right
	 * @param walkingLeft the animation for walking leftwards
	 * @param walkingRight the animation for walking rightwards
	 * @param dying the animation to show when the actor is dying
	 */
	public AnimationLibrary(Animation standingLeft,
							Animation standingRight,
							Animation walkingLeft,
							Animation walkingRight,
							Animation dying) {
		
		this.standingLeft = standingLeft;
		this.standingRight = standingRight;
		this.walkingLeft = walkingLeft;
		this.walkingRight = walkingRight;
		this.dying = dying;
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
	
}
