package engine;

/**
 * For the keys used in the game, keeps a map of the currently pressed and
 * not pressed keys
 * @author Sergio Ángel Verbo
 *
 */
public class KeyMap {

	private boolean rightKey = false;
	private boolean leftKey = false;
	private boolean upKey = false;
	private boolean downKey = false;
	private boolean aKey = false;
	private boolean bKey = false;
	private boolean startKey = false;
	private boolean selectKey = false;
	
	public void setRightKey(boolean value) {
		rightKey = value;
	}
	
	public boolean getRightKey() {
		return rightKey;
	}
	
	public void setLeftKey(boolean value) {
		leftKey = value;
	}
	
	public boolean getLeftKey() {
		return leftKey;
	}
	
	public void setUpKey(boolean value) {
		upKey = value;
	}
	
	public boolean getUpKey() {
		return upKey;
	}
	
	public void setDownKey(boolean value) {
		downKey = value;
	}
	
	public boolean getDownKey() {
		return downKey;
	}
	
	public void setAKey(boolean value) {
		aKey = value;
	}
	
	public boolean getAKey() {
		return aKey;
	}
	
	public void setBKey(boolean value) {
		bKey = value;
	}
	
	public boolean getBKey() {
		return bKey;
	}
	
	public void setStartKey(boolean value) {
		startKey = value;
	}
	
	public boolean getStartKey() {
		return startKey;
	}
	
	public void setSelectKey(boolean value) {
		selectKey = value;
	}
	
	public boolean getSelectKey() {
		return selectKey;
	}
	
	/**
	 * Returns true if the right or left key is still pressed, false if none
	 * of them is pressed
	 * @return
	 */
	public boolean directionKeyPressed() {
		return rightKey || leftKey; 
	}
	
}
