package hanoi_tower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex_1914 {
	static int count;
	static boolean isTwenty;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n<=20) isTwenty = true;
		double start = System.currentTimeMillis();
		hanoi2(n,1,2,3);
		System.out.println(System.currentTimeMillis()-start);
		System.out.println(count);
		hanoi(n,1,2,3);
	}
	
	static void hanoi(int n, int a, int b, int c) {
		if(n==0)return;
		hanoi(n-1,a,c,b);
		if(isTwenty) {
		System.out.println(a+" "+c);
		}
		hanoi(n-1,b,a,c);
	}
	static void hanoi2(int n, int a, int b, int c) {
		if(n==0)return;
		hanoi2(n-1,a,c,b);
		count++;
		hanoi2(n-1,b,a,c);
	}
}
