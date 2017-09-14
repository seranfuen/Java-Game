package entity;

import graphic.Frame;

/**
 * Interface for Entities, which are any objects or elements that belong inside
 * the game. They have a position (in (x, y) coordinates), a movement
 * (expressed in pixels/s for each coordinate), a draw() method to return their
 * appearance 
 * 
 * @author Sergio Ángel Verbo
 *
 */
public interface IEntity {
	/**
	 * Returns the size of the entity
	 * @return the size of the entity
	 */
	Size size();
	
	/**
	 * Returns the image for the entity. Duration is the time elapsed since the
	 * program started and is used to retrieve the correct image if the sprite
	 * is animated (otherwise, the same will always be returned)
	 * @param duration
	 * @return
	 */
	Frame getSprite(long duration);
	
	/**
	 * This function tests if the calling object and the object passed as
	 * argument intersect (occupy the same space)
	 * @param entity
	 * @return
	 */
	boolean intersects(IEntity entity);
	
	/**
	 * Tests if a position (point in space) is within the boundaries of the
	 * entity. False is returned if it is at the boundary or outside
	 * @param position the position whose inclusion in the space occupied by the
	 * entity is tested
	 * @return true if position is within the entity, false otherwise
	 */
	boolean intersects(Position position);
	
	/**
	 * If true, no other object may intersect with it. If false, it may move
	 * @return
	 */
	boolean isSolid();
	
	/**
	 * If true, the object is static and never supposed to move. If false, it
	 * may move
	 * @return true if the entity is static, false otherwise
	 */
	boolean isStatic();
	
	/**
	 * Returns the current position of the entity. The position is measured,
	 * for entities with a size greater than 0, as the position of the
	 * upper-left corner
	 * @return the position of the entity
	 */
	Position position();
	
	/**
	 * Every entity keeps a cache of the previous position. This is taken to 
	 * be the last valid position. If the new position is not validated, the
	 * last position can be returned
	 * @return returns the last position
	 */
	Position previousPosition();
	
	/**
	 * Moves the object to the new position If it is static, it must have no
	 * effect
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 */
	void setPosition(int x, int y);
	
	/**
	 * Moves the object to the new position. If it is static, it must have no
	 * effect
	 * @param position the new position
	 */
	void setPosition(Position position);
	
	/**
	 * Moves the entity to the relative position by adding the coordinates
	 * given to the current position. If it is static, it must have no
	 * effect
	 * @param x the relative horizontal coordinate
	 * @param y the relative vertical coordinate
	 */
	void setRelativePosition(int x, int y);
	
	/**
	 * Moves the entity to the relative position by adding the position given
	 * to the current position. If it is static, it must have no
	 * effect
	 * @param position the relative position to move the entity to
	 */
	void setRelativePosition(Position position);
	
	/**
	 * Sets the size of the entity
	 * @param x the width when unrotated
	 * @param y the height when unrotated 
	 */
	void setSize(int x, int y);
	
	/**
	 * Sets the object to solid  (true) or not solid (false)
	 * @param solid solid flag
	 */
	void setSolid(boolean solid);
	
	/**
	 * Gets the current speed
	 * @return the current speed
	 */
	Speed speed();
	
	/**
	 * Sets the current speed
	 * @param speed new speed
	 */
	void setSpeed(Speed speed);
	
	/**
	 * Sets the current speed
	 * @param x the new horizontal speed
	 * @param y the new vertical speed
	 */
	void setSpeed(double x, double y);
	
	/**
	 * Sets the speed relative to the current speed by adding the speed
	 * given to the speed passed
	 * @param speed
	 */
	void setRelativeSpeed(Speed speed);
	
	/**
	 * Sets the speed relative to the current speed by adding the speeds given
	 * @param x the horizontal speed to add
	 * @param y the vertical speed to add
	 */
	void setRelativeSpeed(double x, double y);
	
	/**
	 * Sets the current acceleration in pixels/s^2. It means the internal
	 * speed will be updated when calling update() based on the value of the
	 * acceleration
	 * @param x horizontal acceleration
	 * @param y vertical acceleration
	 */
	void setAcceleration(double x, double y);
	
	/**
	 * Returns the current acceleration
	 * @return the acceleration
	 */
	Acceleration acceleration();
	

	/**
	 * Changes the size of the entity
	 * @param size the size of the entity
	 */
	void setSize(Size size);
	
	/**
	 * Updates any internal data for the object  
	 * @param elapsed the time elapsed since the last update in ms
	 */
	void update(long elapsed);
	
	/**
	 * Sets the gravity affecting the entity measured in pixels/s^2
	 * @param gravity the gravity
	 */
	void setGravity(int gravity);
	
	/**
	 * Returns the gravity affecting the entity measured in pixels/s^2
	 * @return the gravity
	 */
	int getGravity();
	
	/**
	 * Tells the painter it should not paint the item at determined intervals,
	 * and for the duration given (both interval and duration are computed
	 * by calling update())
	 */
	void flicker(int interval, int duration);
	
	
	/**
	 * If true, the object is flickering and should not be drawn this time
	 * @return
	 */
	public boolean isFlickering();
	
	/**
	 * Returns whether the entity is enabled in the game or not. Typically, it
	 * means it won't be rendered of updated if not
	 * @return true if the entity is enabled, false if it is disabled
	 */
	boolean isEnabled();
	
	/**
	 * Sets the enabled flag
	 * @param value true if the entity is enabled, false if it is disabled
	 */
	void setEnabled(boolean value);
}
