package es.sergioangelverbo.engine;

import es.sergioangelverbo.model.entity.IEntity;

/**
 * Reports a trigger by the trigger entity given and the entity that is created
 * from the trigger and should be added to the game
 * @author Sergio Ángel Verbo
 *
 */
public interface ITriggerListener {
	public void trigger(ITrigger trigger, IEntity entity);
}
