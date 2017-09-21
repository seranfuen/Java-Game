package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Actor;
import entity.Block;
import entity.Coin;
import entity.Enemy;
import entity.Goomba;
import entity.Ground;
import entity.IEntity;
import entity.IKilledListener;
import entity.Position;
import entity.QuestionBlock;
import entity.QuestionBlock.PowerupType;
import entity.Size;
import entity.WoodenBlock;
import graphic.PainterFrame;

/**
 * A stage represents a particular self-contained game stage, with all the
 * entities included in it, the current state, scrolling rules, etc
 * 
 * @author Sergio Ángel Verbo
 *
 */
public class Stage {

	public enum Enemies {
		GOOMBA
	};

	public enum Direction {
		LEFT, RIGHT, STATIC
	};

	public enum Blocks {
		QUESTION, WOODEN
	};

	private static final int scoreRightOffset = 180;
	private static final int scoreTopOffset = 50;
	
	private final int gravity;
	private int xoffset = 0;
	private int yoffset = 0;
	private int xoffsetcut;
	private int yoffsetcut;
	private double xoffsetcutratio = 0.55;
	private double yoffsetcutratio = 0.6;
	private PainterFrame pframe = new PainterFrame();
	private Size screenSize;
	private Size stageSize;
	private CollisionEngine coleng = new CollisionEngine(this);

	private List<IEntity> entities = new ArrayList<IEntity>();
	// Elements that can act as floor
	private List<IEntity> solids = new ArrayList<IEntity>();
	private List<Block> blocks = new ArrayList<Block>();
	private List<Ground> ground = new ArrayList<Ground>();
	private List<Actor> actors = new ArrayList<Actor>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<IEntity> entitiesToAdd = new ArrayList<IEntity>();
	private Actor player;
	private KeyMap kmap = new KeyMap();

	private Score score;
	

	/**
	 * Creates a new stage
	 * 
	 * @param gravity
	 *            the gravity as measured in pixels/s^2
	 * @param stageSize
	 *            the size of the whole stage in pixels
	 * @param screenSize
	 *            the size of the game screen in pixels
	 * @param player
	 *            the player
	 */
	public Stage(int gravity, Size stageSize, Size screenSize, Actor player) {
		this.gravity = gravity;
		this.stageSize = stageSize;
		this.screenSize = screenSize;
		this.player = player;
		score = new Score(5);
		xoffsetcut = (int) (screenSize.width() * (xoffsetcutratio >= 0.5 ? xoffsetcutratio : 0.5));
		yoffsetcut = (int) (screenSize.height() * (yoffsetcutratio >= 0.5 ? yoffsetcutratio : 0.5));
		addActor(player);
	}

	public void toggleMarioRunning() {
		if (player.isRunning()) {
			player.setWalkingSpeed();
		} else {
			player.setRunningSpeed();
		}
	}

	public void addGround(Ground g) {
		ground.add(g);
		entities.add(g);
		solids.add(g);
	}

	/**
	 * Adds an actor to the stage
	 * 
	 * @param actor
	 *            the actor
	 */
	public void addActor(Actor actor) {
		entities.add(actor);
		actors.add(actor);
	}

	/**
	 * Adds a new block to the stage
	 * 
	 * @param block
	 */
	public void addBlock(Block block) {
		blocks.add(block);
		addEntity(block);
		solids.add(block);
		block.addListener(new ITriggerListener() {
			@Override
			public void trigger(ITrigger trigger, IEntity entity) {
				entitiesToAdd.add(entity);
				if (entity instanceof Coin) {
					score.increaseCoins();
				}
			}
		});
	}

	public Block addBlock(Blocks type, int x, int y, PowerupType powerupType) {
		Block b = createBlock(type, x, y, powerupType);
		addBlock(b);
		return b;
	}

	private Block createBlock(Blocks type, int x, int y, PowerupType powerupType) {
		switch (type) {
		case QUESTION:
			return new QuestionBlock(new Position(x, y), powerupType);
		case WOODEN:
			return new WoodenBlock(new Position(x, y));
		}
		return null;
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
		entities.add(enemy);
		actors.add(enemy);
		enemy.addKilledListener(new IKilledListener() {
			@Override
			public void killed(Actor actor) {
				if (actor.getScoredKilled() > 0) {
					score.increaseScore(actor.getScoredKilled());
				}
			}
		});
	}

	/**
	 * Creates an enemy entity of the type given, at the (x, y) position provided
	 * and moving in the direction given and returns a reference to it
	 * 
	 * @param enemy
	 * @param x
	 * @param y
	 * @param dir
	 * @return
	 */
	public Enemy addEnemy(Enemies enemy, int x, int y, Direction dir) {
		Enemy e = createEnemy(enemy, x, y);
		addEnemy(e);
		switch (dir) {
		case LEFT:
			e.moveLeft();
			break;
		case RIGHT:
			e.moveRight();
			break;
		default:
			e.stop();
			break;
		}
		return e;
	}

	/**
	 * Create enemy of type given at initial position
	 * 
	 * @param enemy
	 * @param x
	 * @param y
	 * @return
	 */
	private Enemy createEnemy(Enemies enemy, int x, int y) {
		try {
			switch (enemy) {
			case GOOMBA:
				return new Goomba(new Position(x, y));
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Adds the generic entity
	 * 
	 * @param entity
	 */
	private void addEntity(IEntity entity) {
		entity.setGravity(gravity);
		entities.add(entity);
	}

	/**
	 * Paints the current state of the stage on the graphics object
	 * 
	 * @param g
	 */
	public void paint(Graphics g, long ms) {
		paintBackground(g);
		for (IEntity e : entities) {
			if (e.isEnabled() && !e.isFlickering() && inScreen(e)) {
				pframe.setFrame(e.getSprite(ms));
				pframe.paint(g, e.position().add(new Position(-xoffset, -yoffset)));
			}
		}
		paintInformation(g);
	}

	private void paintInformation(Graphics g) {
		String totalScore = String.format("%010d", score.getScore());
		paintScore(g, totalScore, 1);
		
		String coinScore = String.format("%08d", score.getCoins());
		paintScore(g, coinScore, 2);
	}
	
	private void paintScore(Graphics g, String scoreStr, int lineNumber) {
		int x = screenSize.width() - scoreRightOffset;
		int y = scoreTopOffset + 35 * (lineNumber - 1);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 30));

		g.drawString(scoreStr, shiftWest(x, 1), shiftNorth(y, 1));
		g.drawString(scoreStr, shiftWest(x, 1), shiftSouth(y, 1));
		g.drawString(scoreStr, shiftEast(x, 1), shiftNorth(y, 1));
		g.drawString(scoreStr, shiftEast(x, 1), shiftSouth(y, 1));

		g.setColor(Color.WHITE);
		g.drawString(scoreStr, x, y);
	}

	private void paintBackground(Graphics g) {
		g.setColor(new Color(204, 255, 255));
		g.fillRect(0, 0, screenSize.width(), screenSize.height());
	}

	int shiftNorth(int p, int distance) {
		return (p - distance);
	}

	int shiftSouth(int p, int distance) {
		return (p + distance);
	}

	int shiftEast(int p, int distance) {
		return (p + distance);
	}

	int shiftWest(int p, int distance) {
		return (p - distance);
	}

	/**
	 * Tests if an object can be seen in the screen and should be drawn
	 * 
	 * @param e
	 * @return true if the entity is within the current screen bounds, false
	 *         otherwise
	 */
	private boolean inScreen(IEntity e) {
		return !(e.position().horizontal() + e.size().width() < xoffset
				|| e.position().horizontal() > xoffset + screenSize.width()
				|| e.position().vertical() + e.size().height() < yoffset
				|| e.position().vertical() > yoffset + screenSize.height());
	}

	/**
	 * Updates the state of the stage after n milliseconds
	 */
	public void update(long ms) {
		for (IEntity e : entities) {
			if (e.isEnabled()) {
				e.update(ms);
			}
		}

		for (Actor act : actors) {
			for (IEntity ent : entities) {
				if (act.isEnabled() && ent.isEnabled()) {
					if (act.intersects(ent) && act.isSolid() && ent.isSolid()) {
						coleng.collision(act, ent);
					}
				}
			}
		}

		if (player.position().horizontal() < 0) {
			player.setPosition(0, player.position().vertical());
		}
		if (player.position().horizontal() + player.size().width() > stageSize.width()) {
			player.setPosition(stageSize.width() - player.size().width(), player.position().vertical());
		}
		for (Actor a : actors) {
			if (coleng.shouldFall(a, solids)) {
				a.fall();
			}
		}

		for (IEntity entity : entitiesToAdd) {
			entities.add(entity);
			if (entity instanceof Actor) {
				actors.add((Actor) entity);
			}
		}

		entitiesToAdd.clear();

		updateScreenBounds();
	}

	/**
	 * If the player moves to the right or left and the screen can scroll, update
	 * the parameters
	 */
	private void updateScreenBounds() {
		if (player.position().equals(player.previousPosition()))
			return;
		if (player.position().horizontal() > (xoffset + xoffsetcut)) {
			xoffset += player.position().horizontal() - (xoffset + xoffsetcut);
			if (xoffset + screenSize.width() > stageSize.width()) {
				xoffset = stageSize.width() - screenSize.width();
			}
		} else if (player.position().horizontal() < (xoffset + screenSize.width() - xoffsetcut)) {
			xoffset += player.position().horizontal() - (xoffset + screenSize.width() - xoffsetcut);
			if (xoffset < 0) {
				xoffset = 0;
			}
		}
		if (player.position().vertical() > (yoffset + yoffsetcut)) {
			yoffset += player.position().vertical() - (yoffset + yoffsetcut);
			if (yoffset + screenSize.height() > stageSize.height()) {
				yoffset = stageSize.height() - screenSize.height();
			}
		}
		// TODO for vertical the rules are different when ascending

	}

	/**
	 * Reports an external key press event. Key is compared against the static
	 * values of KeyEvent
	 * 
	 * @param key
	 */
	public void keyPressed(int key) {
		switch (key) {
		case KeyEvent.VK_RIGHT:
			kmap.setRightKey(true);
			player.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			kmap.setLeftKey(true);
			player.moveLeft();
			break;
		case KeyEvent.VK_SPACE:
			player.jump();
			break;
		case KeyEvent.VK_X:
			player.setRunningSpeed();
			break;
		}
	}

	/**
	 * Reports an external key release event
	 * 
	 * @param key
	 */
	public void keyReleased(int key) {
		switch (key) {
		case KeyEvent.VK_RIGHT:
			kmap.setRightKey(false);
			if (!kmap.directionKeyPressed()) {
				player.stop();
			} else {
				player.moveLeft();
			}
			break;
		case KeyEvent.VK_LEFT:
			kmap.setLeftKey(false);
			if (!kmap.directionKeyPressed()) {
				player.stop();
			} else {
				player.moveRight();
			}
			break;
		case KeyEvent.VK_X:
			player.setWalkingSpeed();
			break;
		}
	}

	public Actor getPlayer() {
		return player;
	}
}
