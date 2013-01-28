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
			
			// Try all radii between minr and maxr
			for (int radius = minr; radius < maxr; radius++) {
				
				// Try all x coordinate potentially containing a circle of specified radius
				for (int width = radius + 1; width < w - radius - 1; width++) {
					
					// Try all y coordinate potentially containing a circle of specified radius
					for (int height = radius + 1; height < h - radius - 1; height++) {

						
						
						if (isCircle(width, height, radius, img)) {

							System.out.println("Circle Found: [" + width + "," + height
									+ "] @r=" + radius);
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

		int externalColor = getColorOfCircle(cx, cy, r + 1, img);
		int internalColor = getColorOfCircle(cx, cy, r - 1, img);
		int circleColor = getColorOfCircle(cx, cy, r, img);
		
		
		// Checks if a circle exist with the specified radius.
		if (isACircle(cx, cy, r, img)) {
		
			// Checks that the inner circle if of different color.
			if (isACircle(cx, cy, r - 1, img) && internalColor != circleColor) {
				
				//System.out.println("Circles inside and on r=" + r + "@" + cx + ";" + cy);
				
				// Checks that the outter circle is of different color.
				if (isACircle(cx, cy, r + 1, img) && externalColor != circleColor) {
					//System.out.println("Circle Found: " + "[" + cx + "," + cy + "] @r=" + r);
					return true;
				}
			}
		}
		
		return false;
	}

	static int getColorOfCircle(int cx, int cy, int r, BufferedImage img) {
		return img.getRGB(cx, cy + r);
	}
	static boolean isACircle(int cx, int cy, int r, BufferedImage img) {

		int f = 1 - r;
		int ddF_x = 1;
		int ddF_y = -2 * r;
		int x = 0;
		int y = r;
		boolean isCircle = true;

		// Checks if color at cardinal points match
		final int c = img.getRGB(cx, cy + r);

		isCircle = isCircle && (c == img.getRGB(cx, cy - r));
		isCircle = isCircle && (c == img.getRGB(cx + r, cy));
		isCircle = isCircle && (c == img.getRGB(cx - r, cy));

		// draw 1/8 of the circle, taking advantage of symmetry
		while (x < y && isCircle) {
			if (f >= 0) {
				y--;
				ddF_y += 2;
				f += ddF_y;
			}
			x++;
			ddF_x += 2;
			f += ddF_x;

			isCircle = isCircle && c == img.getRGB(cx + x, cy + y);
			isCircle = isCircle && c == img.getRGB(cx - x, cy + y);
			isCircle = isCircle && c == img.getRGB(cx + x, cy - y);
			isCircle = isCircle && c == img.getRGB(cx - x, cy - y);
			isCircle = isCircle && c == img.getRGB(cx + y, cy + x);
			isCircle = isCircle && c == img.getRGB(cx - y, cy + x);
			isCircle = isCircle && c == img.getRGB(cx + y, cy - x);
			isCircle = isCircle && c == img.getRGB(cx - y, cy - x);
		}
		//System.out.println(isCircle);
		return isCircle;
	}
}
