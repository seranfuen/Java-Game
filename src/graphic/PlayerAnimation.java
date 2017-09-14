package graphic;

public class PlayerAnimation extends AnimationLibrary {

	private Animation leftJump, rightJump, leftCrouch, rightCrouch,
		leftTurn, rightTurn;
	
	public PlayerAnimation(Animation standingLeft, Animation standingRight,
			Animation walkingLeft, Animation walkingRight, Animation dying,
			Animation jumpLeft, Animation jumpRight,
			Animation crouchLeft, Animation crouchRight, Animation turnLeft, Animation turnRight) {
		super(standingLeft, standingRight, walkingLeft, walkingRight, dying);
		this.leftJump = jumpLeft;
		this.rightJump = jumpRight;
		this.leftCrouch = crouchLeft;
		this.rightCrouch = crouchRight;
		this.leftTurn = turnLeft;
		this.rightTurn = turnRight;
	}
	
	public Animation getLeftJump() { return leftJump; }
	public Animation getRightJump() { return rightJump; }
	public Animation getLeftCrouch() { return leftCrouch; }
	public Animation getRightCrouch() { return rightCrouch; }
	public Animation getLeftTurn(){ return leftTurn; }
	public Animation getRightTurn() { return rightTurn; }
	

}
