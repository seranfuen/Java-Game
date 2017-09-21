package entity;

import java.io.IOException;

import engine.ITriggerListener;
import graphic.AnimationCreator;
import graphic.Frame;

public class WoodenBlock extends Block {

	public WoodenBlock(Position initialPos) {
		super(initialPos, new Size(48, 48), getAnimation(), true);
	}

	private static Frame getAnimation() {
		AnimationCreator ac = new AnimationCreator("sprites\\scenery");
		try {
			return ac.getStaticAnimation("wooden_block.png").getImage(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener(ITriggerListener list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rearm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRearmable() {
		// TODO Auto-generated method stub
		return false;
	}

}
