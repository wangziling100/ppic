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

import image.GrayImageArray;
import util.analyser.SimpleAnalyser;
import util.common.CommonOpts;
import util.log.MyLogger;
import util.transform.Common;
import util.transform.IO;

public class GrayPicParsing{
	
		
	
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
		MyLogger.setLevel(MyLogger.INFO);
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
		SimpleAnalyser analyser = new SimpleAnalyser(grayArray);
		int threshold;
		GrayImageArray gia;
		BufferedImage bi = null;
		analyser.normalise(analyser.calcDistanceArray());
		int upper = analyser.getUpper();
		int lower = analyser.getLower();
		for(threshold=lower; threshold<=upper; threshold++){
			
			gia = analyser.analyse(threshold);
			bi = Common.grayImageArrayToBufferedImage(analyser.analyse(threshold));
			
			/*****************************************
			 * 			write image in file
			 */
			String prefix = ofilename.substring(0,ofilename.lastIndexOf('.'));
			String suffix = ofilename.substring(ofilename.lastIndexOf('.')+1);
			String s = prefix+threshold+'.'+suffix;
			IO.writeBufferedGrayImageAsFile(s, bi);
		}
		
		for(int[] rows:grayArray){
			for(int point:rows){
				logger.debug(point+"");
			}
		}
		
		IO.writeBufferedGrayImagesAsFile(ofilename, images);
	      
		
		
	} 
	
}