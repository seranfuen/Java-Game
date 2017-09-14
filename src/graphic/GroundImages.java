package graphic;

/**
 * Ground images have a top, left-top, right-top, left, right and inner
 * sides used to render the ground. They are Frame images containing one image,
 * with duration ignored, used to render a piece of ground
 * @author Sergio
 *
 */
public class GroundImages {
	private String left_top, right_top, left, right, top, inner;
	
	public GroundImages(String left_top, String right_top, String left, 
			String right, String top, String inner) {
		this.left_top = left_top;
		this.right_top = right_top;
		this.left = left;
		this.right = right;
		this.top = top;
		this.inner = inner;
	}
	
	public String getLeftTop() { return left_top; }
	public String getRightTop() { return right_top; }
	public String getTop() { return top; }
	public String getRight() { return right; }
	public String getLeft() { return left; }
	public String getInner() { return inner; }

}
