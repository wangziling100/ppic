package util.analyser;

import image.GrayImageArray;

public abstract class imageAnalyser{
	GrayImageArray input;
	GrayImageArray output;
	
	public imageAnalyser(int[][] data){
		this.input =  new GrayImageArray(data);
		this.output = new GrayImageArray(data);
	}
	public abstract GrayImageArray analyse(double threshold);
	public abstract GrayImageArray normalise(GrayImageArray gia);
}