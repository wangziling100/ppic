package test.undef;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import util.common.CommonOpts;
import util.log.MyLogger;
import util.transform.IO;

public class GrayPicParsing{
	
	public static BufferedImage parseGrayImage(BufferedImage bi){
		
		// create gray buffered image
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage grayBI = new BufferedImage(
				width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		// parse gray image
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				// calc gray value
				final int color = bi.getRGB(x, y);
				final int r = (color >> 16)& 0xff;
				final int g = (color >> 8)& 0xff;
				final int b = color & 0xff;
				int gray = (int)(0.3*r + 0.59*g + 0.11*b);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayBI.setRGB(x, y, newPixel);
			}
		}
		return grayBI;
		
	}
	
	static int colorToRGB(int alpha, int red, int green, int blue){
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
	
	public static void main(String[] args){
		final String DEFAULT_FILE_NAME = "image";
		String DEFAULT_EXT = "GIF";
		String ifilename;
		String ofilename;
		BufferedImage[] images = null;
		FileInputStream fin;
		
		/**********************************
		 * 			preprocessing
		 */
		MyLogger.setLevel(MyLogger.DEBUG);
		final MyLogger logger = new MyLogger(GrayPicParsing.class);
		
		Options options = new Options();
		options.addOption(CommonOpts.input);
		options.addOption(CommonOpts.output);
		options.addOption(CommonOpts.help);
		
		HelpFormatter formatter = new HelpFormatter();
		
		
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Fail to parse parameters from command line");

		}
		
		
		
		
		/************************************************
		 * 				parameter processing
		 */
		if(cmd.hasOption("i") || cmd.hasOption("input")){
			
			ifilename = cmd.getOptionValue("i");
			
			logger.debug("parameter and value:input--"+ifilename);
			
		}else{
			logger.error("no input file");
			return;
		}
		if(cmd.hasOption("o") || cmd.hasOption("output")){
			
			ofilename = cmd.getOptionValue("o");
			
			logger.debug("parameter and value:input--"+ofilename);
			
		}else{
			
			ofilename = DEFAULT_FILE_NAME +"."+ DEFAULT_EXT;
			
			logger.debug("default output");
			
		}
		if(cmd.hasOption("h") || cmd.hasOption("help")){
			formatter.printHelp("GrayPicParsing", options);
		}
		
		
		
		/******************************************
		 * 				image processing
		 */
		
		images = IO.readImageAsBufferedGrayImage(ifilename);
		int[][] grayArray = IO.readImageAsArray(ifilename);
		for(int[] rows:grayArray){
			for(int point:rows){
				logger.debug(point+"");
			}
		}
		
		/*****************************************
		 * 			write image in file
		 */
		
		
		String osuffix = ofilename.substring(ofilename.lastIndexOf('.') + 1);
		Iterator imageWriters = ImageIO.getImageWritersByFormatName(osuffix);
	    ImageWriter imageWriter = (ImageWriter) imageWriters.next();
	    File file = new File(ofilename);
	    ImageOutputStream ios;
		try {
			ios = ImageIO.createImageOutputStream(file);
			imageWriter.setOutput(ios);
			for(BufferedImage bi:images){
				imageWriter.write(bi);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
		
	} 
	
}