package util.transform;

import java.awt.image.BufferedImage;

public class Common {
	public static BufferedImage parseGrayImage(BufferedImage bi) {

		// create gray buffered image
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage grayBI = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		// parse gray image
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// calc gray value
				final int color = bi.getRGB(x, y);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayBI.setRGB(x, y, newPixel);
			}
		}
		return grayBI;

	}

	static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel;
	}
	
	static int[][] parseGrayImageAsArray(BufferedImage bi){
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int[][] result = new int[width][height];
		// parse gray image
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// calc gray value
				final int color = bi.getRGB(x, y);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				result[x][y] = gray;
			}
		}
		return result;
	}
	
	
	/*
	 * 未验证
	 */
	static int[][] bufferedGrayImageToArray(BufferedImage bi){
		int width = bi.getWidth();
		int height = bi.getHeight();
		int[][] result = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final int color = bi.getRGB(x, y);
				final int gray = color & 0xff;
				result[x][y] = gray;
			}
		}
		return result;
	}
}