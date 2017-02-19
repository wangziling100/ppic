package util.analyser;

public abstract class imageAnalyser{
	int[][] input;
	int[][] output;
	
	public imageAnalyser(int[][] data){
		this.input = data.clone();
		this.output = data.clone();
	}
	public abstract int[][] analyse(double threshold);
}