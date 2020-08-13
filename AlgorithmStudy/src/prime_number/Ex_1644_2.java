package prime_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Ex_1644_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int max = Integer.parseInt(br.readLine()) + 1;
		List list = getPrimeNumList2(max);
		System.out.println(list);
	}

public static LinkedList<Integer> getPrimeNumList2(int max) {
		
		LinkedList<Integer> list = new LinkedList<>();
		boolean[] a = new boolean[max];
		for(int i=2 ;i<=Math.sqrt(max);i++) {
			for(int j=i*i;j<a.length;j+=i) {
				
				
//				if(a[i]==true) {
//					System.out.println("i : "+i);
//					break;
//				}
//				if(j==i) {
//					continue;
//				}
//				if(j%i==0) {
//					a[j] = true;
//				}
			}
			
		}
		for(int i=2 ;i<a.length;i++) {
			if(a[i]!=true) {
				list.add(i);
			}
		}
		return list;
	}
}
