package util.analyser;

public class TwoPolesFilter implements IFilter{
	
	double threshold = 0;
	public TwoPolesFilter(double threshold){
		this.threshold = threshold;
	}

	public double filter(double distance){
		return distance>this.threshold?255d:0d;
//		return filter(threshold);
	}
	public double filter(double distance, double t) {
		// TODO Auto-generated method stub
		
		return distance>t?255d:0d;
	}

	public boolean ifRemain(double value) {
		// TODO Auto-generated method stub
		return false;
	}

	public double layerFilter(double distance, double upper, double lower) {
		// TODO Auto-generated method stub
		return (distance<upper && distance>lower)?255d:0d;
	}
	
}