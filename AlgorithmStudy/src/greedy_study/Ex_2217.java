package greedy_study;

import java.io.*;
import java.util.Arrays;

public class Ex_2217 {
	public static void main(String[] args){
		try { 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(in.readLine());
		int[] rope = new int[size];
		
		for(int i=0; i<rope.length;i++) {
			rope[i] = Integer.parseInt(in.readLine());
		}
		
		mergeSort(rope,0,rope.length-1);
		System.out.println("arr:"+Arrays.toString(rope));
		int max=0;
		int count=2;
		max = rope[rope.length-1];
		for(int i=rope.length-2;i>=0;i--) {
			System.out.println("max:"+max);
			System.out.println("rope[i]"+rope[i]);
			System.out.println("count"+count);
			if(max<rope[i]*count) {
				max = rope[i]*count;
			}
			count++;
		}
		System.out.println(max);
		}catch(Exception e) {
			
		}
		
	}

	public static void mergeSort(int[] arr, int start, int end) {
		if ((end - start + 1) == 1)
			return;

		int middle = (end - start) / 2 + start;
		

		mergeSort(arr, start, middle);
		mergeSort(arr, middle + 1, end);
		merging(arr, start, middle, middle + 1, end);
	}

	public static void merging(int[] arr, int start, int end, int start2, int end2) {
		int[] sorted = new int[end2 - start + 1];
		int i = start;
		int j = start2;
		int k = 0;
		while ((i > end || j > end2) != true) {
			if (arr[i] < arr[j]) {
				sorted[k++] = arr[i++];
			} else {
				sorted[k++] = arr[j++];
			}
		}
		if (i > end) {
			while (j > end2 != true) {
				sorted[k++] = arr[j++];
			}
		} else {
			while (i > end != true) {
				sorted[k++] = arr[i++];
			}
		}

		for (int t = start; t <= end2; t++) {
			arr[t] = sorted[t - start];
		}

	}
}
