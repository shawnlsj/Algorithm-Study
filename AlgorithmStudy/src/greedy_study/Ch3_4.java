package greedy_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch3_4 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		int count = 0;
		int tmp = 0;
		while(n>1) {
			tmp = n%k;
			if(tmp==0) {
				n = n/k;
				count++;
			} else {
				n--;
				count++;
			}
		}
		System.out.println(count);
	}
}
