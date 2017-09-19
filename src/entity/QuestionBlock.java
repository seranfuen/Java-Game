package entity;

import java.io.IOException;
import graphic.Animation;
import graphic.AnimationCreator;
import graphic.Frame;
import helper.ExceptionHelper;

/**
 * A question block that may contain something
 * @author Sergio
 *
 */
public class QuestionBlock extends Block {

	private static Animation unusedAni = null;
	private static Animation usedAni = null;
	private static final int squareSize = 48;
	private boolean bumping = false;
	private Position initialPosition;
	private int bumpSpeed = -200;
	
	private static final int aniSpeed = 150;
	
	public QuestionBlock(Position initialPos) {
		super(initialPos, new Size(squareSize, squareSize), null, true);
		this.initialPosition = initialPos;
	}
	
	@Override
	public Frame getSprite(long duration) {
		if (!used) {
			return getUnusedAni().getImage(duration);
		}
		// Change for used
		return getUsedAni().getImage(duration);
	}

	private static Animation getUsedAni() {
		if (usedAni == null) {
			AnimationCreator ac = new AnimationCreator("sprites\\scenery");
			try {
				return ac.getStaticAnimation("q_block_off.png");
			} catch (IOException e) {
				ExceptionHelper.ShowExceptionClose(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public void update(long elapsed) {
		super.update(elapsed);
		if (bumping) {
			if (position().vertical() >= initialPosition.vertical()) {
				setPosition(initialPosition);
				setAcceleration(0, 0);
				setSpeed(0, 0);
				bumping = false;
			}
		}
	}
	
	
	private static Animation getUnusedAni() {
		if (unusedAni == null) {
			AnimationCreator ac = new AnimationCreator("sprites\\scenery");
			try {
				ac.addFrame("q_block_01.png", aniSpeed);
				ac.addFrame("q_block_02.png", aniSpeed);
				ac.addFrame("q_block_03.png", aniSpeed);
				ac.addFrame("q_block_04.png", aniSpeed);
			} catch (IOException e) {
				ExceptionHelper.ShowExceptionClose(e);
				return null;
			}
			unusedAni = ac.createAnimation();
		}
		return unusedAni;
	}

	@Override
	public void activate() {
		if (!used) {
			setSpeed(0, bumpSpeed);
			setAcceleration(0, getGravity() * 1.2);
			used = true;
			bumping = true;
		}
	}
	
}
