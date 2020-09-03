package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;



public class Ch5_4 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		
		int[][] arr = new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
				String s = br.readLine();
			for(int j=1; j<=m; j++) {
				arr[i][j] = s.charAt(j-1) - '0';
			}
		}
		
		int x = 1;
		int y = 1;
		
		Ch5_4.Queue<Ch5_4.Node> queue = new Ch5_4.Queue<Ch5_4.Node>();
		
		bfs(new Ch5_4.Node(arr, x, y), n, m, queue);
			
		
		
		System.out.println(arr[n][m]);
		

	}
	static class Node{
		int[][] arr;
		int x;
		int y;
		Node(int[][] arr, int x, int y){
			this.arr = arr;
			this.x = x;
			this.y = y;
		}
	}
	static class Queue<T> {
		
		@SuppressWarnings("hiding")
		private class Node<T> {
			private Node<T> next;
			private T t;
			
			private Node(T t){
				this.t = t;
			}
			
		}

		private Node<T> first;
		private Node<T> last;
		
		
		public void add(T t) {
			if (first == null) {
				first = new Node<T>(t);
				last = first;		

			} else {
				last = last.next = new Node<T>(t);
			}		
		}
		
		public T remove() {
			if(first == null) {
				throw new NoSuchElementException();
			}
			
			T t = first.t;
			
			if(first == last) {
			first = null;
			last = null;
				
			}else {
			first = first.next;
			}
			
			return t;
		}
		public T peek() {
			if(this.isEmpty()) {
				return null;
			}
			return first.t;
			
		}
		public boolean isEmpty() {
			if(first == null) {
				return true;
			} 
			return false;
		}	

	}
	
	public static void bfs(Ch5_4.Node node, int n,int m, Ch5_4.Queue<Ch5_4.Node> queue) {		
		int x= node.x;
		int y= node.y;
		int[][] arr = node.arr;
		int movedBlock = arr[x][y];
		
		if(x==n && y==m) return;
		
		arr[x][y] = 0;
		int[] dirX = {1,0,-1,0};
		int[] dirY = {0,1,0,-1};
			for(int i=0;i<4;i++) {
				int afterX = x+dirX[i];
				int afterY = y+dirY[i];
				if(afterX<1||afterX>n||afterY<1||afterY>m) {
					continue;
				} else if(arr[afterX][afterY]==0) {
					continue;
				}
				
				arr[afterX][afterY] += movedBlock;						
				queue.add(new Ch5_4.Node(arr, afterX, afterY));
			}
			if(!queue.isEmpty()) {
			Ch5_4.Node removedNode = queue.remove();
			bfs(removedNode,n,m,queue);
			} else {
				return;
			}
	}
	
}
