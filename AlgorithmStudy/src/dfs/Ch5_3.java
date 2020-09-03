package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ch5_3 {

	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());

		int[][] arr = new int[n][m];
		int count = 0;
		
		LinkedList<Ch5_3.Node> list = new LinkedList<Ch5_3.Node>();

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
				list.add(new Ch5_3.Node(arr, i, j));
			}
		}
		
		System.out.println(Arrays.deepToString(arr));
		while(!list.isEmpty()) {
			Ch5_3.Node node = list.pollFirst();
			if(arr[node.x][node.y]==0) {
				dfs(node.x,node.y, arr, n, m);
				System.out.println("conut :" +count);
				System.out.println(Arrays.deepToString(arr));
				count++;
			}
		}
			
		
		System.out.println(count); 

	}

	static void dfs(int x, int y, int[][] arr, int n, int m) {
		
		int[] dirX = { 1, 0, -1, 0 };
		int[] dirY = { 0, 1, 0, -1 };

		arr[x][y] = 1;
		
		int afterX = 0;
		int afterY = 0;
		
		for (int i = 0; i <= 3; i++) {
			afterX = x + dirX[i];
			afterY = y + dirY[i];
			if (afterX < 0 || afterX >= n || afterY < 0 || afterY >= m) {
				continue;
			}
			if(arr[afterX][afterY] == 1) {
				continue;				
			}
			arr[afterX][afterY] = 1;
			dfs(afterX, afterY, arr, n, m);
		}
	}

	static class Node {
		int[][] arr;
		int x;
		int y;

		Node(int[][] arr, int x, int y) {
			this.arr = arr;
			this.x = x;
			this.y = y;
		}

	}
}
