/*
 *	TriangleComponent.java
 *
 *  Draws a triangle in the center of the screen, 5 pixels from the edges.
 *  Using recursion, draws a triangle inside each corner using the midpoints
 *  of the sides. Continues until a triangle is too small to draw.
 */
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JComponent;

public class TriangleComponent extends JComponent
{
	// constants for 1st triangle
	private static final int EDGE_OFFSET = 5;
	
	// constant class array to hold progression of colors
	private static final Color LAVENDER = new Color(204, 153, 255);
	private static final Color PURPLE = new Color(153, 0, 255);
	private static final Color[] colors = {LAVENDER, Color.cyan, Color.darkGray, 
		Color.blue, PURPLE, Color.pink, Color.black, Color.lightGray};
		
	// Graphics object used for drawing
	private Graphics2D gr;
	
	/** Draw the triangles
	 *  @param g the Graphics object for drawing in this component
	 */
	public void paintComponent(Graphics g) 
	{
		gr = (Graphics2D) g;
		
		int topX = getWidth() / 2;
		int topY = EDGE_OFFSET;
		int leftX = EDGE_OFFSET;
		int rightX = getWidth() - EDGE_OFFSET;
		int bottomY = getHeight() - EDGE_OFFSET;
		drawTriangle(topX, topY, leftX, bottomY, rightX, bottomY, 0);
	}
	
	/** Draw the given triangle and triangles inside each corner
	 *  @param x1 the x-coordinate for the top corner
	 *  @param y1 the y-coordinate for the top corner
	 *  @param x2 the x-coordinate for the left corner
	 *  @param y2 the y-coordinate for the left corner
	 *  @param x3 the x-coordinate for the right corner
	 *  @param y3 the y-coordinate for the right corner
	 *  @param colorIndex the index into the color array for drawing
	 */
	private void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int colorIndex) 
	{
		// Create x/y arrays for polygon
		int[] xpoints = {x1, x2, x3};
		int[] ypoints = {y1, y2, y3};

/*		// 80-pt version
		gr.setColor(Color.BLUE);   
		gr.draw(new Polygon(xpoints, ypoints, 3));
*/				
			
		// 90+ versions
		gr.setColor(colors[colorIndex]);
		gr.fill(new Polygon(xpoints, ypoints, 3));
		
		// Calculate midpoints for next set of calls
		int midX = Math.abs(x1 - x2) / 2;
		int midY = Math.abs(y1 - y2) / 2;
		if (midX > 1 && midY > 1) 
		{
			int newColorIndex = (colorIndex + 1) % colors.length;

			// Draw top triangle
			drawTriangle(x1, y1, x1 - midX, y1 + midY, 
				x1 + midX, y1 + midY, newColorIndex); 

			// Draw bottom left triangle
			drawTriangle(x2 + midX, y2 - midY, x2, y2, 
				x1, y2, newColorIndex); 

			// Draw bottom right triangle
			drawTriangle(x1 + midX, y1 + midY, x1, y3, 
				x3, y3, newColorIndex); 
		}
	}
	
}