package util.analyser;

public interface IFilter{
	public double filter(double distance);
	public double filter(double distance, double threshold);
	public boolean ifRemain(double value);
}