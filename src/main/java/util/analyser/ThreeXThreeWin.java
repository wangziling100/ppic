package util.analyser;

public class ThreeXThreeWin implements ICalcWin{
	
	
	int indexOfOrigin = 0;
	public ThreeXThreeWin() {
		// TODO Auto-generated constructor stub
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return 9;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return 3;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 3;
	}

	public void setOrigin(int index) {
		// TODO Auto-generated method stub
		this.indexOfOrigin = index;
	}
	
	public int getIndex(int x, int y){
		int index = x*3 + y;
		return index;
	}
	
}