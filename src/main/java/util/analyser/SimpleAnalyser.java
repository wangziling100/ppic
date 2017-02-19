package util.analyser;

import java.awt.Point;

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
	public int[][] analyse(double threshold) {
		// TODO Auto-generated method stub
		MaxDistance md = new MaxDistance();
		TwoPolesFilter tf = new TwoPolesFilter(threshold);
		ThreeXThreeWin tw = new ThreeXThreeWin();
		
		int x=0, y=0;
		for(int[] i1:input){
			for(int i2:i1){
				//search origin
				Point origin = new Point(x, y);
				double[] calcSet = new double[tw.getLength()];
				//traverse mask window
				for(int m=0;m<tw.getLength();m++){
					int mw = m/tw.getWidth();
					int mh = m/tw.getHeight();
					int xoffset = origin.x + mw;
					int yoffset = origin.y + mh;
					calcSet[m] = input[xoffset][yoffset];
				}
				// calc distance
				double distance = md.getDistance(calcSet);
				// filter input set
				output[origin.x][origin.y] = (int)tf.filter(distance);
				y++;
			}
			x++;
		}
		
		return output;
	}
	
}