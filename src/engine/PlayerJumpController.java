package engine;

import entity.Player;

public class PlayerJumpController {

	private Player player;
	private boolean isJumpPressed = false;
	private boolean canIncreaseJump = false;
	
	public PlayerJumpController(Player player) {
		this.player = player;
	}
	
	public void setJumpPressed() {
		if (!player.isJumping()) {
			player.jump();
			canIncreaseJump = true;
			player.setAnimation();
		}
		isJumpPressed = true;
	}
	
	public void unsetJumpPressed() {
		isJumpPressed = false;
		canIncreaseJump = false;
	}
	
	public void update(long msElapsed) {
		if (canIncreaseJump && isJumpPressed) {
			player.extendJump(msElapsed);
		}
	}
}
