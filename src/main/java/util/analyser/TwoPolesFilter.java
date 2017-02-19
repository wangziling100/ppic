package util.analyser;

public class TwoPolesFilter implements IFilter{
	
	double threshold = 0;
	public TwoPolesFilter(double threshold){
		this.threshold = threshold;
	}

	public double filter(double distance){
		return filter(threshold);
	}
	public double filter(double distance, double threshold) {
		// TODO Auto-generated method stub
		
		return distance>threshold?255:0;
	}

	public boolean ifRemain(double value) {
		// TODO Auto-generated method stub
		return false;
	}
	
}