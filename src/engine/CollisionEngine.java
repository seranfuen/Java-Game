package engine;

import java.util.List;

import entity.Actor;
import entity.Block;
import entity.Enemy;
import entity.Ground;
import entity.IEntity;
import entity.Player;
import entity.Position;
import entity.Speed;

/**
 * When two entities intersect, the collision Engine determines what to do with
 * them, modifying their position, state, or performing anything that needs to
 * be done with them
 * 
 * @author Sergio Ángel Verbo
 *
 */
public class CollisionEngine {

	private final Stage stage;

	public enum CollisionDirection {
		LEFT, RIGHT, UP, DOWN, STATIC
	}

	public CollisionEngine(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Tests if the actor has anything below it (at 0 or 1 pixels of distance). If
	 * false is returned, there is nothing directly under the feet of the actor and
	 * it should be made to fall if needed
	 * 
	 * @param actor
	 *            the actor
	 * @param entities
	 *            list of entities that can act as floor
	 * @return
	 */
	public boolean shouldFall(Actor actor, List<IEntity> entities) {
		for (IEntity e : entities) {
			if (e.equals(actor))
				continue;
			if (actor.notInContact(e)) {
				continue;
			}
			if (getVerticalSeparation(actor, e) <= 1)
				return false;
		}
		return true;
	}

	private int getVerticalSeparation(Actor actor, IEntity e) {
		return e.position().vertical() - (actor.position().vertical() + actor.size().height());
	}

	/**
	 * Point of entry for collisions. The engine will determine the entity types,
	 * use the stage reference it got in the constructor to consult anything it
	 * needs about the state of the game, and then determine what to do with the two
	 * entities that have intersected (collided). By default, it returns them to
	 * their previous positions before the collision
	 * 
	 * @param entity1
	 *            the first entity, it usually must be an actor. If two actors
	 *            collide, then one of the two. If an actor collides with a block or
	 *            other entity, the actor should be entity1
	 * @param entity2
	 *            the second entity
	 */
	public void collision(IEntity entity1, IEntity entity2) {
		CollisionDirection dir = getCollisionDirection(entity1, entity2);
		if (dir != CollisionDirection.STATIC)
			if (entity1 instanceof Actor) {
				collisionActor((Actor) entity1, entity2, dir);
			}
			// by default, restore previous positions if both entities are solid
			else {
				if (entity1.isSolid() && entity2.isSolid()) {
					restorePosition(entity1);
					restorePosition(entity2);
				}
			}
	}

	/**
	 * Determines on which side of entity1 it came into contact with entity2. If
	 * entity1 was not moving, it will be STATIC
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return
	 */
	private CollisionDirection getCollisionDirection(IEntity entity1, IEntity entity2) {
		if (entity1.previousPosition().horizontal() + entity1.size().width() < entity2.previousPosition()
				.horizontal()) {
			return CollisionDirection.RIGHT;
		} else if (entity1.previousPosition().horizontal() > entity2.previousPosition().horizontal()
				+ entity2.size().width()) {
			return CollisionDirection.LEFT;
		} else if (entity1.previousPosition().vertical() + entity1.size().height() < entity2.previousPosition()
				.vertical()) {
			return CollisionDirection.DOWN;
		} else if (entity1.previousPosition().vertical() > entity2.size().height()
				+ entity2.previousPosition().vertical()) {
			return CollisionDirection.UP;
		}
		return CollisionDirection.STATIC;
	}

	/**
	 * Moves e1 to a place where it is adjacent to, but not intersecting, e2. Used
	 * when two objects intersect and they aren't allowed to do so The direction is
	 * used to determine where the object e1 should be place
	 * 
	 * @param e1
	 * @param e2
	 * @param dir
	 */
	private void undoCollision(IEntity e1, IEntity e2, CollisionDirection dir) {
		if (dir == CollisionDirection.LEFT) {
			e1.setPosition(e2.position().horizontal() + e2.size().width() + 1, e1.position().vertical());
		} else if (dir == CollisionDirection.RIGHT) {
			e1.setPosition(e2.position().horizontal() - e1.size().width() - 1, e1.position().vertical());
		} else if (dir == CollisionDirection.UP) {
			e1.setPosition(e1.position().horizontal(), e2.position().vertical() + e2.size().height() + 1);
		} else if (dir == CollisionDirection.DOWN) {
			e1.setPosition(e1.position().horizontal(), e2.position().vertical() - e1.size().height() - 1);
		}
	}

	/**
	 * Decide what to do when the first entity of a collision is an actor
	 * 
	 * @param entity1
	 *            the actor
	 * @param entity2
	 *            the other entity the actor collides with
	 */
	private void collisionActor(Actor entity1, IEntity entity2, CollisionDirection dir) {
		if (entity2 instanceof Block) {
			collisionWithBlock(entity1, (Block) entity2, dir);
		}
		if (entity2 instanceof Ground) {
			collisionWithGround(entity1, (Ground) entity2, dir);
		}
		if (entity2 instanceof Enemy) {
			collisionWithEnemy(entity1, (Enemy) entity2, dir);
		}
	}

	private void collisionWithEnemy(Actor entity1, Enemy entity2, CollisionDirection dir) {
		if (entity1 instanceof Enemy) {
			collisionTwoEnemies((Enemy) entity1, entity2, dir);
		} else if (entity1 instanceof Player) {
			collisionEnemyPlayer((Player) entity1, entity2, dir);
		}
	}

	/**
	 * Do something when the player has a collision with an enemy
	 * 
	 * @param entity1
	 * @param entity2
	 * @param dir
	 */
	private void collisionEnemyPlayer(Player entity1, Enemy entity2, CollisionDirection dir) {
		if (dir != CollisionDirection.DOWN) {
			entity1.flicker(60, 1500);
		} 
		else if (entity2.canKill()) {
			entity2.kill();
		}
	}

	private void collisionTwoEnemies(Enemy entity1, Enemy entity2, CollisionDirection dir) {
		if (dir == CollisionDirection.RIGHT) {
			entity1.setPosition(entity2.position().horizontal() - entity1.size().width() - 1,
					entity1.position().vertical());
		} else if (dir == CollisionDirection.LEFT) {
			entity1.setPosition(entity2.position().horizontal() + entity2.size().width() + 1,
					entity1.position().vertical());
		}
		entity1.reverseHorizontal();
		entity2.reverseHorizontal();

	}

	private void collisionWithGround(Actor entity1, IEntity entity2, CollisionDirection dir) {
		if (dir == CollisionDirection.DOWN) {
			entity1.setPosition(entity1.position().horizontal(),
					entity2.position().vertical() - entity1.size().height() - 1);
			entity1.stopFall();
		} else {
			undoCollision(entity1, entity2, dir);
		}
	}

	/**
	 * Decides what to do when an actor collides with a block. By default, the
	 * action will be to leave the block static and make the actor stay just at the
	 * edge on which it collided with the entity. It will also clear the speed in
	 * the direction of the collision
	 * 
	 * @param entity1
	 *            the actor that collided with the block
	 * @param entity2
	 *            the block
	 */
	private void collisionWithBlock(Actor actor, Block block, CollisionDirection dir) {
		if (actor instanceof Enemy) {
			enemyBlockCollision((Enemy) actor, block, dir);
		} else {
			defaultActorBlockCollision(actor, block, dir);
		}
	}

	private void enemyBlockCollision(Enemy actor, Block block, CollisionDirection dir) {
		undoCollision(actor, block, dir);
		if (dir == CollisionDirection.DOWN) {
			actor.stopFall(block.position().vertical() + actor.size().height() + 1);
		} 
		else if (dir == CollisionDirection.UP) {
			actor.setSpeed(actor.speed().getHorizontal(), 0);
		}
		if (dir == CollisionDirection.RIGHT) {
			actor.moveLeft();
		} 
		else if (dir == CollisionDirection.LEFT) {
			actor.moveRight();
		}
	}

	/**
	 * The default action for an actor - block collision is to leave the block where
	 * it is, and have the actor just collide with the edge, setting the speed on
	 * that axis to 0
	 * 
	 * @param actor
	 * @param block
	 */
	private void defaultActorBlockCollision(Actor actor, Block block, CollisionDirection dir) {
		undoCollision(actor, block, dir);
		switch (dir) {
		case DOWN:
			actor.stopFall(block.position().vertical() - actor.size().height() - 1);
			break;
		case UP:
			actor.setSpeed(new Speed(actor.speed().getHorizontal(), 0));
			actor.setPosition(new Position(actor.position().horizontal(),
					block.position().vertical() + block.size().height() + 1));
			block.activate();
			break;
		case LEFT:
			actor.setPosition(new Position(block.position().horizontal() + block.size().width() + 1,
					actor.position().vertical()));
			break;
		case RIGHT:
			actor.setPosition(new Position(block.position().horizontal() - actor.size().width() - 1,
					actor.position().vertical()));
			break;
		case STATIC:
			break;
		}
	}

	/**
	 * Restores the entity to its previous position. Does not modify speed or
	 * acceleration
	 * 
	 * @param entity
	 *            the entity whose previous position is restores
	 */
	private void restorePosition(IEntity entity) {
		entity.setPosition(entity.previousPosition());

	}

}
