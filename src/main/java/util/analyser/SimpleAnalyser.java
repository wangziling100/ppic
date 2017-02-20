package util.analyser;


import image.GrayImageArray;

/**
 * output = analyse (input)
 * @author xingbo
 *
 */
public class SimpleAnalyser extends imageAnalyser{

	public SimpleAnalyser(int[][] data) {
		super(data);
		// TODO Auto-generated constructor stub
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

	@Override
	public GrayImageArray normalise(GrayImageArray gia) {
		// TODO Auto-generated method stub
		return null;
	}
	
}