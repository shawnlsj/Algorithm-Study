package sort;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class InsertSort {

	public static void main(String[] args) {
	Integer[] arr = makeIntegerArray();
	InsertSorting(arr);

	
	
	}
	public static void InsertSorting(Object[] array) {
		Integer[] arr = (Integer[])array;
		int select = 0;

		for(int i=1;i<arr.length;i++) {
			select = arr[i];

			for(int j=i-1;j>=0;j--) {
				if(arr[j]>select) {
					arr[j+1]=arr[j];
					arr[j] = select;
				}else {
					arr[j+1] = select;
					break;
					
				}
			}
		}
	
		
		}
	
	
	public static  Integer[] makeIntegerArray() {
		LinkedHashSet<Integer> s = new LinkedHashSet<>();
		int i=0;
		while(s.size()<8){
		i = (int)(Math.random()*15);
			s.add(i);
		}
		Integer[] arr = s.toArray(new Integer[s.size()]);
		return arr;
	}
}
