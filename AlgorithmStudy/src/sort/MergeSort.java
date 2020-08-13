package sort;

import java.util.Arrays;

public class MergeSort {

	public static int[] makingIntArr() {
		int[] arr = new int[10];
		for(int i=0; i<10 ;i++) {
			arr[i]=(int)(Math.random()*300);
	}
	return arr;	
	}

	public static void main(String[] args) {
		
		int[] arr = makingIntArr();
		System.out.println("정렬 전" + Arrays.toString(arr));

		mergeSort(arr, 0, arr.length - 1);
		System.out.println("정렬 후 " + Arrays.toString(arr));
		
		
	}

	public static void mergeSort(int[] arr, int start, int end) {

		if ((end - start + 1) == 1) //배열의 크기가 1이면 return 
			return;
		int middle = start + ((end - start) / 2);

		mergeSort(arr, start, middle);
		mergeSort(arr, middle + 1, end);
		merge(arr, start, middle, middle + 1, end);

	}

	public static void merge(int[] arr, int start, int end, int start2, int end2) {
		int[] sorted = new int[end2 - start + 1];
		int i = start;
		int j = start2;
		int k = 0;
		while ((i > end || j > end2) != true) {
			if (arr[i] < arr[j]) {
				sorted[k] = arr[i++];
			} else {
				sorted[k] = arr[j++];
			}
			k++;
		}
		if (i > end) {
			for (int t = j; t <= end2; t++) {
				sorted[k] = arr[t];
				k++;
			}
		} else {
			for (int t = i; t <= end; t++) {
				sorted[k] = arr[t];
				k++;
			}
		}

		for (int t = start; t <= end2; t++) {
			arr[t] = sorted[t - start];
		}

	}

}

