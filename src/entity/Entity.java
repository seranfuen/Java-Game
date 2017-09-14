package entity;

import engine.GameSettings;
import graphic.Frame;

/**
 * Generic solid static entity
 * 
 * @author Sergio �ngel Verbo
 *
 */
public class Entity implements IEntity {

	protected Position position;
	protected Position lastPosition;
	private Speed speed = Speed.zeroSpeed();
	protected Acceleration acceleration = Acceleration.zeroAcceleration();
	protected Size size;
	protected Frame staticFrame;
	protected boolean solid;
	private boolean isStatic;
	private boolean enabled = true;
	private int gravity;
	private int flickint = -1; // every how often the flicker flag is switched
	private int flicktime = -1;  // remaining flicker time in ms
	private int flickintrem = -1; // remaining time to switch the flicker flag
	private boolean flickering = false; // flicker flag

	/**
	 * Creates a static solid entity that only has an initial position (does not
	 * allow movement) and one static image
	 * 
	 * @param initialPos
	 * @param image
	 */
	public Entity(Position initialPos, Size size) {
		position = lastPosition = initialPos;
		this.size = size;
		gravity = GameSettings.getGameSettings().getGravity();
	}

	public Entity(Position initialPos, Size size, Frame staticFrame) {
		this(initialPos, size);
		this.staticFrame = staticFrame;
	}

	@Override
	public Acceleration acceleration() {
		return acceleration;
	}

	@Override
	public int getGravity() {
		return gravity;
	}

	/**
	 * Returns the lower right corner of the object
	 * 
	 * @return
	 */
	protected Position getOppositePosition() {
		int x = position.horizontal();
		int rx = x + size.width();
		int y = position.vertical();
		int ly = y + size.height();
		return new Position(rx, ly);
	}

	@Override
	public Frame getSprite(long duration) {
		return staticFrame;
	}

	@Override
	public boolean intersects(IEntity entity) {
		Position posoth = entity.position();
		Size sizoth = entity.size();
		return !(position.horizontal() > posoth.horizontal() + sizoth.width()
				|| position.vertical() > posoth.vertical() + sizoth.height()
				|| posoth.horizontal() > position.horizontal() + size.width() 
				|| posoth.vertical() > position.vertical() + size.height());
	}

	@Override
	public boolean intersects(Position otherPos) {
		Position opposite = getOppositePosition();
		return (otherPos.horizontal() > position.horizontal()
				&& otherPos.horizontal() < opposite.horizontal()
				&& otherPos.vertical() > position.vertical() && otherPos
				.vertical() < opposite.vertical());
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isSolid() {
		return solid;
	}

	@Override
	public boolean isStatic() {
		return isStatic;
	}

	@Override
	public Position position() {
		return position;
	}

	@Override
	public Position previousPosition() {
		return lastPosition;
	}

	@Override
	public void setAcceleration(double x, double y) {
		acceleration = new Acceleration(x, y);
	}

	@Override
	public void setEnabled(boolean value) {
		enabled = value;
	}

	@Override
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	@Override
	public void setPosition(int x, int y) {
		setPosition(new Position(x, y));
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public void setRelativePosition(int x, int y) {
		setRelativePosition(new Position(x, y));
	}

	@Override
	public void setRelativePosition(Position nPos) {
		setPosition(new Position(position.horizontal() + nPos.horizontal(),
				position.vertical() + nPos.vertical()));
	}
	
	
	private void updateFlicker(long elapsed) {
		if (flicktime > 0) {
			flickintrem -= elapsed;
			if (flickintrem <= 0) {
				flickering = !flickering;
				flicktime -= (elapsed - flickintrem);
				flickintrem = flickint;
			}
		} else {
			flickering = false;
		}
	}

	@Override
	public void setRelativeSpeed(double x, double y) {
		setRelativeSpeed(new Speed(x, y));
	}

	@Override
	public void setRelativeSpeed(Speed speed) {
		this.speed = this.speed.add(speed);

	}

	@Override
	public void setSize(int x, int y) {
		setSize(new Size(x, y));

	}

	@Override
	public void setSize(Size size) {
		this.size = size;

	}

	@Override
	public void setSolid(boolean solid) {
		this.solid = solid;

	}

	@Override
	public void setSpeed(double x, double y) {
		speed = new Speed(x, y);
	}

	@Override
	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	@Override
	public Size size() {
		return size;
	}

	@Override
	public Speed speed() {
		return speed;
	}
	
	/**
	 * Tells the painter it should not paint the item at determined intervals,
	 * and for the duration given (both interval and duration are computed
	 * by calling update())
	 */
	public void flicker(int interval, int duration) {
		if (flicktime <= 0) {
			flickering = true;
			flickint = flickintrem = interval;
			flicktime = duration;
		}
	}
	
	
	/**
	 * If true, the object is flickering and should not be drawn this time
	 * @return
	 */
	public boolean isFlickering() {
		return flickering;
	}

	@Override
	public void update(long elapsed) {
		speed = speed.updateSpeed(acceleration, elapsed);
		lastPosition = position;
		int relx = (int) (elapsed / 1000 * speed.getHorizontal() + Math
				.round((elapsed % 1000) / speed.xPixelMs()));
		int rely = (int) (elapsed / 1000 * speed.getVertical() + Math
				.round((elapsed % 1000) / speed.yPixelMs()));
		setRelativePosition(relx, rely);
		updateFlicker(elapsed);
	}

}
