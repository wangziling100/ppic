package util.analyser;

public interface IFilter{
	public double filter(double distance);
	public double filter(double distance, double threshold);
	public double layerFilter(double distance, double upper, double lower);
	public boolean ifRemain(double value);
}