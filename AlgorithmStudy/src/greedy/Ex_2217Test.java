package greedy;

import java.util.Arrays;

public class Ex_2217Test {
	public static void main(String[] args) {
		while(true) {
			int[] arr = createArray();
			System.out.println("arr:"+Arrays.toString(arr));

			if (answer(arr) != correct(arr)) {
				System.out.println("no");
				break;
			}
		}
	}
	static int[] createArray() {
		int a = (int)(Math.random()*5+1);
		int[] arr = new int[a];
		for(int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*50);
		}
		return arr;
	}
	static int answer(int[] rope) {
			mergeSort(rope,0,rope.length-1);
			
			int max=0;
			int count=2;
			max = rope[rope.length-1];
			System.out.println("arr22:"+Arrays.toString(rope));
			for(int i=rope.length-2;i>=0;i--) {
				if(max<rope[i]*count) {
					max = rope[i]*count;
				}
				count++;
			}
			System.out.println("answer : "+max);
			return max;
	}
	
	static int correct(int[] arr) {
		
		        Arrays.sort(arr);

		        long max = 0;
		        for(int i = arr.length-1; i >= 0; i--) {
		            arr[i] = arr[i] * (arr.length-i);
		            if(max < arr[i]) max = arr[i];
		        }
				System.out.println("correct : "+max);
		        return (int) max;
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


