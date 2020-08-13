package greedy_study;

import java.util.*;

public class Ex_11399 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int size = scanner.nextInt();
				scanner.nextLine();
		String time = scanner.nextLine();
		
		StringTokenizer timeToken = new StringTokenizer(time," ");
		int[] sizeArr = new int[size]; 
		boolean isSwap = false;
		for(int i=0;i<sizeArr.length;i++) {
			sizeArr[i] = Integer.parseInt(timeToken.nextToken());
		}
		
		//�������� ����
		for(int i=1; i<sizeArr.length+1;i++) {
			for(int j=0; j<sizeArr.length-i;j++) {
				if(sizeArr[j]>sizeArr[j+1]) {
					swap(sizeArr,j,j+1);
					isSwap = true;
				}
			}
			if(isSwap==false) {
				break;
			}
		}
		int sum = 0;
		int result =0;

		for(int i=0; i<sizeArr.length ;i++) {
			for(int j=sizeArr.length-1-i;j>=0;j--) {
				sum += sizeArr[j];
			}
			result += sum;
			sum = 0;
		}
		
		System.out.println(result);
	}
	
	public static void swap(int[] arr, int a, int b) {
		int tmp = 0;
		tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
