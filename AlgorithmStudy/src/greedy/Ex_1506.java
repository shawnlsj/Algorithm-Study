package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_1506 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int cityNum = Integer.parseInt(in.readLine());
		String cost = in.readLine();
		
		int[] costArr = new int[cityNum];
		String[] rootArr = new String[cityNum];
		
		StringTokenizer costToken = new StringTokenizer(cost," ");
		
		//각 요소에 코스트를 등록한다
		for(int i=0;i<costArr.length;i++) {
			costArr[i]=Integer.parseInt(costToken.nextToken());
		}
		
		//각 요소에 루트정보를 등록한다
		for(int i=0;i<rootArr.length;i++) {
			rootArr[i]=in.readLine();
		}
		
		System.out.println("citynum:"+cityNum);
		System.out.println("costarr :" +Arrays.toString(costArr));
		System.out.println("rootarr : "+Arrays.toString(rootArr));
	}
}
