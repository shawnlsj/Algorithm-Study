package sort;

import java.util.Arrays;

public class QuickSort_v1 {

	public static void main(String[] args) {
		int[] a = makingIntArr();
		System.out.println(Arrays.toString(a));

		
		quickSort(a, 0, a.length - 1);
		
		System.out.println(Arrays.toString(a));

	}

	public static int[] makingIntArr() {
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 30);
		}
		return arr;
	}

	public static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int pivotIndex = partition(arr, start, end);
			quickSort(arr, start, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, end);
		}

	}

	public static int partition(int[] arr, int start, int end) {
		int pivotIndex = (end+start)/2;
		int pivot = arr[pivotIndex];
		int i = start;
		int j = end;
		int tmp = 0;

		
			while(i<j) {

				while(arr[i]<=pivot && i<=end) {
					i++;
						if(i>end) {
						tmp = arr[end];
						arr[end] = arr[pivotIndex];
						arr[pivotIndex] = tmp;
						return end;
						}
					}
				while(arr[j]>=pivot && start<=j) {
					j--;
					if(j<start) {
						tmp = arr[pivotIndex];
						arr[pivotIndex] = arr[start];
						arr[start] = tmp;
						return start;
					}
				}
				if(i<j) {
					 tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
				
				
			}//while end
		
		if(pivotIndex>j && pivotIndex<i) {
			return pivotIndex;
		}

		if(pivotIndex>j && pivotIndex>i) {
			tmp = arr[pivotIndex];
			arr[pivotIndex] = arr[i];
			arr[i] =tmp;
			return i;
		}
		
		if(pivotIndex<j && pivotIndex<i) {
			tmp = arr[pivotIndex];
			arr[pivotIndex] = arr[j];
			arr[j] =tmp;
			return j;
		
		}
		
		return 0;
		
	}

}
