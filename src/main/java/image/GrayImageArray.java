package image;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import util.log.MyLogger;

public class GrayImageArray{
	
	List<Integer> array = new ArrayList<Integer>();
	int width = 0;
	int height = 0;
	
	public GrayImageArray(){
		
	}
	
	public GrayImageArray(int[][] list){
		for(int[] l1:list){
			this.width = 0;
			for(int l2:l1){
				array.add(l2);
				this.width++;
			}
			this.height++;
		}
		
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	public GrayImageArray(List list, int width, int height){
		array = list;
		this.width = width;
		this.height = height;
	}
	
	public int getValue(int x, int y){
		
		int index = (x)*this.width + (y);
		return array.get(index);
	}
	
	public void setValue(int x, int y, int value){
		int index = (x)*this.width + (y);
		array.set(index, value);
	}
	
	public int getIndex(int x, int y){
		int index = (x)*this.width + (y);
		return index;
	}
	
	public void add(int value){
		array.add(value);
	}
	
	public List getArray(){
		return array;
	}
	
	public static void main(String[] args){
		
		MyLogger.setLevel(MyLogger.DEBUG);
		MyLogger logger = new MyLogger(GrayImageArray.class);
		Marker mk = MarkerFactory.getMarker("GrayImageArray");
		logger.setMarkerFilter(mk);
		int[][] data = {{1,2,3}, {4,5,6},{7,8,9}};
		GrayImageArray gia = new GrayImageArray(data);
		for(int i = 0; i<3; i++){
			for(int j=0; j<3; j++){
				logger.debug(mk,gia.getValue(i, j)+"");
			}
		}
	} 
	
	
}
