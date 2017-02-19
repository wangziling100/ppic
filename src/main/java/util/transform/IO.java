package util.transform;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class IO{
	
	
	public static BufferedImage[] readImageAsBufferedImage(String filename){
		BufferedImage[] images = null;
		try {
			
			FileInputStream fin = new FileInputStream(filename);
			String isuffix = filename.substring(filename.lastIndexOf('.') + 1);
			Iterator readers = ImageIO.getImageReadersBySuffix(isuffix);
			ImageReader imageReader = (ImageReader) readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(fin);
			imageReader.setInput(iis, false);
			int num = imageReader.getNumImages(true);
			images = new BufferedImage[num];
			for (int i = 0; i < num; ++i) {
				// transform imageReader to buffered image
				images[i] = imageReader.read(i);
			}
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return images;
	}
	
	
	public static BufferedImage[] readImageAsBufferedGrayImage(String filename){
		BufferedImage[] images = null;
		try {
			
			FileInputStream fin = new FileInputStream(filename);
			String isuffix = filename.substring(filename.lastIndexOf('.') + 1);
			Iterator readers = ImageIO.getImageReadersBySuffix(isuffix);
			ImageReader imageReader = (ImageReader) readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(fin);
			imageReader.setInput(iis, false);
			int num = imageReader.getNumImages(true);
			images = new BufferedImage[num];
			for (int i = 0; i < num; ++i) {
				// transform to gray image
				images[i] = Common.parseGrayImage(imageReader.read(i));
			}
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return images;
	}
	
	
	/**
	 * note: only the first image in image set will be read as array
	 * @param filename
	 * @return array of gray value, indices are coordinate
	 */
	public static int[][] readImageAsArray(String filename) {
		int[][] result = null;
		try {

			FileInputStream fin = new FileInputStream(filename);
			String isuffix = filename.substring(filename.lastIndexOf('.') + 1);
			Iterator readers = ImageIO.getImageReadersBySuffix(isuffix);
			ImageReader imageReader = (ImageReader) readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(fin);
			imageReader.setInput(iis, false);
			int num = imageReader.getNumImages(true);
			result = Common.parseGrayImageAsArray(imageReader.read(0));
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	
	

}