package sort;

import java.util.Arrays;

//v1에서 소스코드 개선
//이제는 i 또는 j가 피벗을 집을 수 있게 되었다!
public class QuickSort_v1_2 {

	public static void main(String[] args) {
		int[] arr = makingIntArr();
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

	public static int[] makingIntArr() {
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 40);
		}
		return arr;
	}

	public static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int pivotIndex = partition(arr, start, end);

			quickSort(arr, start, pivotIndex - 1);
			quickSort(arr, pivotIndex, end);
		}

		//만일 리턴받는 i의 값이 항상 partition()에서 정의한 피벗의 인덱스라면
		//			quickSort(arr, start, pivotIndex - 2);
		//			quickSort(arr, pivotIndex, end);
		// 이렇게 작성하는 것이 가능하다 (pivotIndex-2와 pivotIndex 사이의 피벗을 잡고 정렬한 거니까)

		//하지만 i의 값은 항상 피벗의 인덱스가 아니다
		//oooo p 일경우는 pivotIndex == p
		//(pivotIndex는 partition()의 return i; 의 값, p는 피벗의 인덱스 값)
		//oooo (p)

		//p xxxx 일 경우는 pivotIndex == p+1 이 되기 때문에
		//p (x)xxx

		// 이 두가지 경우의 수를 커버하려면
		// 왼쪽 파티션의 end와
		// 오른쪽 파티션의 start 사이에 1칸이라도 존재하면 안된다
	}

	public static int partition(int[] arr, int start, int end) {
		int pivotIndex = (end+start)/2;
		int pivot = arr[pivotIndex];
		int i = start;
		int j = end;
		int tmp = 0;


			// !(i==j) 를 조건으로 쓰지 않는 이유는
			// 123 5 785 이렇게 피벗과 같은 값이 있을 경우
			// i와 j가 계속 서로를 바꾸면서 무한루프에 빠져버리기 때문이다
			while(i<=j) {

				while(arr[i]<pivot) {
					i++;
				}
				while(arr[j]>pivot) {
					j--;
				}
				
				// i==j인 경우에도 스왑해야함
				// 그래야 서로 1칸씩 이동해서 엇갈리게 되기때문
				if(i<=j) {
					 tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					i++;
					j--;
				}
			}//while end

			return i;
	}
}

