/**
 * 
 */
package engine;

/**
 * @author Sergio Ángel Verbo
 *
 */
public final class GameSettings {

	private static GameSettings instance;
	
	public static GameSettings getGameSettings() {
		if (instance == null) instance = new GameSettings();
		return instance;
	}
	
	private final int gravity = 1500;
	
	public int getGravity() {
		return gravity;
	}
	
	private GameSettings() {
		
	}
	
}
