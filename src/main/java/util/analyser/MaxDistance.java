package util.analyser;


/**
 *  calculate the max distance between each two elements
 *  in the same data set
 * @author xingbo
 *
 */
public class MaxDistance implements IDistance{

	public double getDistance(double[] data) {
		// TODO Auto-generated method stub
		return getDistance(0, data);
	}

	public double getDistance(int index_of_origin, double[] data) {
		// TODO Auto-generated method stub
		double distance = 0;
		final double origin = data[index_of_origin];
		for(double element:data){
			// calculate the max distance between each two elements
			distance = Math.max(distance, Math.abs(element-origin));
		}
		return distance;
	}
	
}