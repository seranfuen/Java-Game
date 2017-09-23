/**
 * 
 */
package es.sergioangelverbo.engine;

/**
 * @author Sergio Ángel Verbo
 *
 */
public final class GameSettings {

	private static GameSettings instance;
	
	// The budget of how much "speed" we can add if we don't release the jump key
	private static final int maxJumpExtension = 2800;
	// Max time of ms that keeping the jump button pressed will extend the jump
	private static final int maxJumpExtensionMs = 1500;
	
	private static final int gravity = 3300;
	private static final int maxFallingSpeed = 1500;
	
	
	public static GameSettings getGameSettings() {
		if (instance == null) instance = new GameSettings();
		return instance;
	}
	
	public int getGravity() {
		return gravity;
	}
	
	private GameSettings() {
		
	}
	
	public int getMaxJumpExtension() {
		return maxJumpExtension;
	}
	
	public int getMaxJumpExtensionMs() {
		return maxJumpExtensionMs;
	}
	
	public int getMaxFallingSpeed() {
		return maxFallingSpeed;
	}
	
}
