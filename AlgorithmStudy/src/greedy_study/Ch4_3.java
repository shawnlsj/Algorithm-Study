package greedy_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch4_3 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int x = (s.charAt(0)-('a'-1));
		int y = s.charAt(1)-'0';
		

		
		int[] xArr = {1, -1,  1, -1, 2,  2, -2,  -2}; 
		int[] yArr = {2,  2, -2, -2, 1, -1,  1,  -1};
		int answer = 0;
		for(int i=0; i<xArr.length ;i++) {
			int ix = x+xArr[i];
			int iy = y+yArr[i];
			if(ix<1 || ix>8 || iy<1 || iy>8) {
				continue;
			}
				answer++;
		}
		
		System.out.println(answer);
		
		// 말 좌표 구하기
		
		
		
		
		
	}
	
}
