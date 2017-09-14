package graphic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to build animations by loading, in order, a series of images.
 * It can set the working directory for the animation so only the name of the
 * item need be supplied
 * @author Sergio Ángel Verbo
 *
 */
public class AnimationCreator {
	private String workDir = "";
	private List<Frame> frames = new ArrayList<Frame>();
	
	/**
	 * Creates a new Animation Creator without providing a working directory
	 */
	public AnimationCreator() {	}
	
	/**
	 * Creates a new Animation Creator that will take images from the working
	 * directory provided
	 * @param workingDirectory directory where the images for the animation are
	 * stored
	 */
	public AnimationCreator(String workingDirectory) {
		workDir = workingDirectory;
		if (!workDir.endsWith(File.separator)) {
			workDir = workDir + File.separator;
		}
	}
	
	/**
	 * Adds the next frame to the animation
	 * @param imageName the name of the image (to be found at the working
	 * directory or in any directory relative to it)
	 * @param duration the duration of the frame in milliseconds
	 * @throws IOException if the file cannot be loaded onto the frame
	 */
	public void addFrame(String imageName, int duration) throws IOException {
		frames.add(new Frame(workDir + imageName, duration));
	}
	
	/**
	 * Returns an animation with one frame (static image). It does not modify
	 * the buffer of frames added by calling addFrame
	 * @param imageName the name of the image at the path or relative path
	 * @throws IOException if the image failed to load
	 */
	public Animation getStaticAnimation(String imageName) throws IOException {
		return new Animation(new Frame(workDir + imageName, 0));
	}
	
	/**
	 * Creates an animation from the frames currently held in the buffer. It 
	 * does not clear the buffer
	 * @return
	 */
	public Animation createAnimation() {
		return new Animation(frames);
	}
	
	/**
	 * Clears the frames in the buffer
	 */
	public void clear() {
		frames.clear();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Animation Creator [WorkDir=");
		sb.append(workDir);
		sb.append(",Frames=");
		sb.append(frames.size());
		sb.append("]");
		return sb.toString();
	}
	
}
