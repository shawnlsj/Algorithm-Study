package greedy;

import java.util.*;
public class Ex_11047 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int coinNum = scanner.nextInt(); //���� N
		int money = scanner.nextInt(); //��ǥ�ݾ� K
		
		int resultNum =0;
		int remainMoney =money;
		
		int[] coinArr = new int[coinNum];
		
		for(int i=0;i<coinArr.length;i++) {
			coinArr[i] = scanner.nextInt();
		}
		
		for(int i=coinArr.length-1;i>=0;i--) {
			int tmp = remainMoney/coinArr[i];
			resultNum += tmp;
			
			if(remainMoney%coinArr[i] == 0) {
				break;
			} else {
				remainMoney = remainMoney%coinArr[i];
			}
		}
		System.out.println(resultNum);
	}
}
