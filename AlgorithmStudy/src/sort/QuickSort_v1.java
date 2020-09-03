package sort;

import java.util.Arrays;

//소스가 길어진 이유 : 피벗은 무조건 고정되어 있어야 한다는 선입견 때문에
// i 또는 j가 피벗을 집고 옮길 수 있게 짜면 된다는 생각 자체를 못했음
public class QuickSort_v1 {

	public static void main(String[] args) {
		int[] arr = makingIntArr();
		System.out.println("정렬 전" + Arrays.toString(arr));
		quickSort(arr, 0, arr.length - 1);
		System.out.println("정렬 후" + Arrays.toString(arr));
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

				// ooo p ooo  (o는 피벗보다 작은 수 , x는 큰 수)
				if(i>end) {
					tmp = arr[end];
					arr[end] = arr[pivotIndex];
					arr[pivotIndex] = tmp;
					return end;
				}
			}

			while(arr[j]>=pivot && start<=j) {
				j--;

				// xxx p xxx  (o는 피벗보다 작은 수 , x는 큰 수)
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


		// ooo p xxx  (o는 피벗보다 작은 수 , x는 큰 수)
		if(pivotIndex>j && pivotIndex<i) {
			return pivotIndex;
		}


		// oox p xxx  (o는 피벗보다 작은 수 , x는 큰 수)
		if(pivotIndex>j && pivotIndex>i) {
			tmp = arr[pivotIndex];
			arr[pivotIndex] = arr[i];
			arr[i] =tmp;
			return i;
		}

		// ooo p oxx  (o는 피벗보다 작은 수 , x는 큰 수)
		if(pivotIndex<j && pivotIndex<i) {
			tmp = arr[pivotIndex];
			arr[pivotIndex] = arr[j];
			arr[j] =tmp;
			return j;

		}

		// 모든 경우의 수 5가지에 대하여 return문을 작성하였음
		// 따라서 여기까지는 절대 도달 할 수 없기 때문에 리턴값이 무엇이 되든 OK
		return 0;

	}

	public static int[] makingIntArr() {
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 30);
		}
		return arr;
	}



}
