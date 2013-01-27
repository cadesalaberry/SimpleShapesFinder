import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;

// Template for assignment 1
public class Assig1 {

	public static void main(String[] args) {
		try {
			// arg 0 is the input image name
			BufferedImage img = ImageIO.read(new File(args[0]));
			// graphical output
			Graphics2D g2 = img.createGraphics();
			// use red
			g2.setColor(Color.RED);

			// arg 1 is the min radius
			int minr = Integer.parseInt(args[1]);
			// arg 2 is the max radius
			int maxr = Integer.parseInt(args[2]);

			// if present, arg 3 is the max width we consider
			int w = (args.length > 3) ? Integer.parseInt(args[3]) : img
					.getWidth();
			// if present, arg 4 is the max height we consider
			int h = (args.length > 4) ? Integer.parseInt(args[4]) : img
					.getHeight();


			for (int width = 0; width < w; width++) {
				for (int height = 0; height < h; height++) {
					for (int radius = minr; radius < maxr; radius++) {
						if (isCircle(width, height, radius, img)) {
							drawCircle(width, height, radius, img, g2);
						}
					}
				}
			}

			// call circle drawing algorithm
			drawCircle(5, 5, 3, img, g2);

			// write out the image
			File outputfile = new File("outputimage.png");
			ImageIO.write(img, "png", outputfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bresenham's algorithm to draw a circle requires circle center and radius,
	 * as well as the image and Graphics2D object with drawing colour already
	 * set.
	 * 
	 * @param cx
	 * @param cy
	 * @param r
	 * @param img
	 * @param g
	 */
	static void drawCircle(int cx, int cy, int r, BufferedImage img,
			Graphics2D g) {
		int f = 1 - r;
		int ddF_x = 1;
		int ddF_y = -2 * r;
		int x = 0;
		int y = r;

		// draw cardinal points
		g.drawLine(cx, cy + r, cx, cy + r);
		g.drawLine(cx, cy - r, cx, cy - r);
		g.drawLine(cx + r, cy, cx + r, cy);
		g.drawLine(cx - r, cy, cx - r, cy);

		// draw 1/8 of the circle, taking advantage of symmetry
		while (x < y) {
			if (f >= 0) {
				y--;
				ddF_y += 2;
				f += ddF_y;
			}
			x++;
			ddF_x += 2;
			f += ddF_x;

			g.drawLine(cx + x, cy + y, cx + x, cy + y);
			g.drawLine(cx - x, cy + y, cx - x, cy + y);
			g.drawLine(cx + x, cy - y, cx + x, cy - y);
			g.drawLine(cx - x, cy - y, cx - x, cy - y);
			g.drawLine(cx + y, cy + x, cx + y, cy + x);
			g.drawLine(cx - y, cy + x, cx - y, cy + x);
			g.drawLine(cx + y, cy - x, cx + y, cy - x);
			g.drawLine(cx - y, cy - x, cx - y, cy - x);
		}
	}
	
	static boolean isCircle(int cx, int cy, int r, BufferedImage img) {
		int f = 1 - r;
		int ddF_x = 1;
		int ddF_y = -2 * r;
		int x = 0;
		int y = r;
		
		// you can look at pixel values with this API call:
		 // get RGB value of pixel at (0,0)

		// Checks cardinal points
		int c = img.getRGB(cx, cy + r);
		int south = img.getRGB(cx, cy - r);
		int east = img.getRGB(cx + r, cy);
		int west = img.getRGB(cx - r, cy);

		// draw 1/8 of the circle, taking advantage of symmetry
		while (x < y) {
			if (f >= 0) {
				y--;
				ddF_y += 2;
				f += ddF_y;
			}
			x++;
			ddF_x += 2;
			f += ddF_x;

			g.drawLine(cx + x, cy + y, cx + x, cy + y);
			g.drawLine(cx - x, cy + y, cx - x, cy + y);
			g.drawLine(cx + x, cy - y, cx + x, cy - y);
			g.drawLine(cx - x, cy - y, cx - x, cy - y);
			g.drawLine(cx + y, cy + x, cx + y, cy + x);
			g.drawLine(cx - y, cy + x, cx - y, cy + x);
			g.drawLine(cx + y, cy - x, cx + y, cy - x);
			g.drawLine(cx - y, cy - x, cx - y, cy - x);
		}
	}
}
