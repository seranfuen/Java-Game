package es.sergioangelverbo.engine;

import es.sergioangelverbo.model.entity.IEntity;

/**
 * Triggers define a condition that will cause an IEntity to be returned
 * and which should be added by the engine at that moment.
 * If a Trigger is rearmable, it will keep track of the state of the last
 * object returned and if it is destroyed/disabled, the trigger will be
 * rearmed: it will fire again if the condition is met
 * @author Sergio
 *
 */
public interface ITrigger {
	/**
	 * Adds an entity that will listen to trigger events
	 * @param list
	 */
	public void addListener(ITriggerListener list);
	/**
	 * Returns the entity that is created after the trigger is fired. A new
	 * entity is created if the trigger is fired ,otherwise a reference to the
	 * last entity, or null if no entity was created before, is returned
	 * @return
	 */
	public IEntity getEntity();
	/**
	 * Forces the trigger to rearm
	 */
	public void rearm();
	/**
	 * Tests if the ITrigger is rearmable or not
	 * @return true if it is rearmable. When the last entity returned
	 * is disabled, the trigger will rearm
	 */
	public boolean isRearmable();
}
