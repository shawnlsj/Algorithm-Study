package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch4_1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(stk.nextToken());
		
		int x = 1;
		int y = 1;
				
		StringTokenizer stk2 = new StringTokenizer(br.readLine()," ");
		
		while(stk2.hasMoreTokens()) {
			switch(stk2.nextToken()){
			
			case "L":
			y = Math.max(1, y-1);
			break;
			
			case "R":
			y = Math.min(n, y+1);
			break;
			
			case "U":
			x = Math.max(1, x-1);
			break;
			
			case "D":
			x = Math.min(n, x+1);
			break;
			}
		}
		
		System.out.println(x+" "+y);
	
	}

}
