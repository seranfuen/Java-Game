package graphic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Size;

/**
 * A Frame object represents a single image within an animation sequence, or
 * a still image if a sprite or tile is still. It contains an Image object
 * and a duration
 */ 
public class Frame {
	// the image
	protected Image frame;
	// the time in ms the frame is supposed to stay. 0 if the frame is a still
	int duration;
	// size of frame in pixels
	Size size;
	String filepath;
	
	/**
	 * Constructor that doesn't take an image path. It will need deriving 
	 * classes to set the image
	 * @param duration
	 */
	public Frame(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Creates a new Frame, loading the image whose path is given
	 * If the duration of the image is 0, then it is a still (an animation
	 * that only has one element)
	 * @param file the path to the image
	 * @param duration the duration of the frame inside an animation sequence
	 * @throws IOException if the image failed to load
	 */
	public Frame(String file, int duration) throws IOException {
		filepath = file;
		frame = null;
		frame = ImageIO.read(new File(file));
		this.duration = duration;
		size = new Size(frame.getWidth(null), frame.getHeight(null));
	}
	
	/**
	 * Returns the image
	 * @return the image of the frame
	 */
	public Image getImage() {
		return frame;
	}
	
	/**
	 * Returns the duration of the frame in a sequence in ms. 
	 * If 0, it is a still
	 * @return the duration of the frame in a sequence in ms
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Returns the size of the image
	 * @return the size of the image
	 */
	public Size getSize() {
		return size;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Frame at \"");
		sb.append(filepath);
		sb.append("\" with size of [x=");
		sb.append(size.width());
		sb.append(" px, y=");
		sb.append(size.height());
		sb.append(" px] and duration of ");
		sb.append(duration);
		sb.append(" ms");
		return sb.toString();
	}
}
