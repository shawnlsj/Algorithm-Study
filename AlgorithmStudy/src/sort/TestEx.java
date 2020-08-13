package sort;

import java.util.Arrays;

public class TestEx {
	public static void main(String[] args) {
		int[] a= makingIntArr();
		quickSort(a);
		System.out.println(Arrays.toString(a));
	}
	public static int[] makingIntArr() {
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 15);
		}
		return arr;
	}
	private static void quickSort(int[] arr) {
		quickSort(arr,0,arr.length-1);
		
	}
	private static void quickSort(int[] arr, int start, int end) {
		int part2 = partition(arr, start, end);
		if(start<part2-1) {
			quickSort(arr,start,part2-1);
		}
		if(part2<end) {
			quickSort(arr,part2,end);
		}
	}
	private static int partition(int[] arr, int start, int end) {
		int pivot = arr[(start+end)/2];
		while(start<=end) {
			while(arr[start]<pivot) start++;
			
			while(arr[end]>pivot) {
				end--;
			}
			
			if(start<=end) {
				swap(arr,start,end);
				start++;
				end--;
			}
		}
	
		return start;
	}
	private static void swap(int[] arr, int start, int end) {
		int tmp = arr[start];
		arr[start] = arr[end];
		arr[end] = tmp;
	}
	
}
