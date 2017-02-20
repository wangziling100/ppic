package util.analyser;


import java.util.List;

import image.GrayImageArray;

/**
 * output = analyse (input)
 * @author xingbo
 *
 */
public class SimpleAnalyser extends imageAnalyser{
	
	// upper and lower limit of distance array
	final int INIT_UPPER = -1;
	final int INIT_LOWER = 1000;
	int upper = INIT_UPPER;
	int lower = INIT_LOWER;

	public SimpleAnalyser(int[][] data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public GrayImageArray calcDistanceArray() {
		// TODO Auto-generated method stub
		MaxDistance md = new MaxDistance();
		ThreeXThreeWin tw = new ThreeXThreeWin();
		int width = input.getWidth();
		int height = input.getHeight();
		double[] calcData = new double[tw.getLength()];
		
		// traverse the gray image array
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				// get data set to calc distance
				for(int m=0; m<tw.getWidth(); m++){
					for(int n=0; n<tw.getHeight(); n++){
						int indexOfCalcData = tw.getIndex(m, n);
						calcData[indexOfCalcData] = input.getValue(x+m, y+n);
					}
				}
				
				// calc distance
				double distance = md.getDistance(calcData);
				this.distanceArray.setValue(x, y, (int)distance);
			
			}
		}
		
		return this.distanceArray;
	}
	

	@Override
	public GrayImageArray analyse(double threshold) {
		// TODO Auto-generated method stub
		MaxDistance md = new MaxDistance();
		TwoPolesFilter tf = new TwoPolesFilter(threshold);
		ThreeXThreeWin tw = new ThreeXThreeWin();
		int width = input.getWidth();
		int height = input.getHeight();
		double[] calcData = new double[tw.getLength()];
		
		// traverse the gray image array
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				// get data set to calc distance
				for(int m=0; m<tw.getWidth(); m++){
					for(int n=0; n<tw.getHeight(); n++){
						int indexOfCalcData = tw.getIndex(m, n);
						calcData[indexOfCalcData] = input.getValue(x+m, y+n);
					}
				}
				
				// calc distance
				double distance = md.getDistance(calcData);
				
				// figure out output
				output.setValue(x, y, (int)tf.filter(distance));
			
			}
		}
		
		return this.output;
	}

	/**
	 *  only calculate lower and upper limit of layer filter
	 */
	@Override
	public GrayImageArray normalise(GrayImageArray gia) {
		// TODO Auto-generated method stub
		List<Integer> l = gia.getArray();
		for(int element : l){
			this.upper = this.upper>element?this.upper:element;
			this.lower = this.lower<element?this.lower:element;
		}
		return null;
	}
	
	public int getUpper(){
		if(INIT_UPPER == this.upper){
			normalise(output);
		}
		return this.upper;
	}
	
	public int getLower(){
		if(INIT_LOWER == this.lower){
			normalise(output);
		}
		return this.lower;
	}

	
}