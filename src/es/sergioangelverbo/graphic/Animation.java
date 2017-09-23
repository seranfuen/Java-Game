package es.sergioangelverbo.graphic;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * An animation is a sorted collection of Frames. Each frame as a duration.
 * The animation object provides a method that takes the number of milliseconds
 * that have elapsed since the start of the game. Using modular arithmetic,
 * it determines the next Image to return from the corresponding frame
 * @author Sergio �ngel Verbo
 *
 */
public class Animation {

	private List<Frame> frames;
	private int aniduration;
	private Frame staticFrame;
	private boolean isStatic;
	
	/**
	 * Constructor for a still frame (duration of animation is 0, only one
	 * frame is always returned)
	 * @param frame the static frame
	 */
	public Animation(Frame frame) {
		staticFrame = frame;
		isStatic = true;
		aniduration = 0;
	}
	
	/**
	 * Constructor for an animation of 
	 * @param frames
	 */
	public Animation(List<Frame> frames) {
		if (frames.size() < 2) 
			throw new InvalidParameterException("Animation must have at least "
					+ "2 frames");
		this.frames = frames;
		aniduration = 0;
		for (Frame frm : frames) {
			aniduration += frm.getDuration();
		}
		if (aniduration == 0) 
			throw new InvalidParameterException("The frames in the animation "
					+ "must have at least 1 ms of duration");
		isStatic = false;		
	}
	
	/**
	 * Returns the frame corresponding to the duration given
	 * @param duration the time since the game started
	 * @return the image for the corresponding animation
	 */
	public Frame getImage(long duration) {
		if (isStatic) return staticFrame;
		long mod = duration % aniduration;
		int dur = 0;
		for (Frame frm : frames) {
			dur += frm.getDuration();
			if (dur > mod) {
				return frm;
			}
		}
		return frames.get(frames.size()-1);
	}
	
	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("Animation composed of the Frames: ");
		for (Frame frm : frames) {
			sb.append("\n");
			sb.append(frm.toString());
		}
		return sb.toString();
	}
	
}
