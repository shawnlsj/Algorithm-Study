package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch4_4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

 		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int width = Integer.parseInt(stk.nextToken());
		int height = Integer.parseInt(stk.nextToken());

		int[][] board = new int[width][height];
		int[][] visitBoard = new int[width][height];

		StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(stk2.nextToken());
		int y = Integer.parseInt(stk2.nextToken());
		int dir = Integer.parseInt(stk2.nextToken());
		int[] xArr = { 0, 1, 0, -1 };
		int[] yArr = { -1, 0, 1, 0 };

		int answer = 1;

		for (int by = 0; by < height; by++) {

			StringTokenizer stk3 = new StringTokenizer(br.readLine(), " ");

			for (int bx = 0; bx < width; bx++) {
				board[bx][by] = Integer.parseInt(stk3.nextToken());

			}
		}
		visitBoard[x][y] = 1;

		while (true) {
			System.out.println("x : " + x);
			System.out.println("y : " + y);
			System.out.println("dir : " + dir);
			dir = turnLeft(dir);
				//���� ������ üũ
			if (isWent(board, visitBoard, x, y, dir, xArr, yArr)) {
				continue;

			} else {
				// �ٴٰ� �ƴϸ� �ٶ󺸴� �������� 1ĭ ����
				if (!isOcean(board, x, y, dir, xArr, yArr)) {
					visitBoard[x + xArr[dir]][y + yArr[dir]] = 1;
					x = x + xArr[dir];
					y = y + yArr[dir];
					answer++;
				}
			}

			if (isBlocked(board, visitBoard, x, y, dir, xArr, yArr)) {
				// 4���� ��� ���� �ٴ� or ���� ���̸� 
				// ������ �����ϸ� 1ĭ �ڷΰ�
				int reverseDir = turnLeft(turnLeft(dir));
				x = x + xArr[reverseDir];
				y = y + yArr[reverseDir];

				if (board[x][y] == 1) {
					break;
				}

			}

		}

		System.out.println(answer);

	}

	static boolean isWent(int[][] board, int[][] visitBoard, int x, int y, int dir, int[] xArr, int[] yArr) {
		int afterX = x + xArr[dir];
		int afterY = y + yArr[dir];

		if (board[afterX][afterY] == 1) {

//�ٴٸ� �������� �ƴϴ�
			return false;
		} else {

//�ٴٰ� �ƴ϶�� �����̴�, �����̸� ���� ������ üũ
			if (visitBoard[afterX][afterY] == 0) {
				// �������� �ƴϴ�
				
				return false;
			}
		}
//���� ���̴�
		return true;
	}

	static boolean isBlocked(int[][] board, int[][] visitBoard, int x, int y, int dir, int[] xArr, int[] yArr) {

		if ((board[x + xArr[0]][y + yArr[0]] == 1 || visitBoard[x + xArr[0]][y + yArr[0]] == 1)
				&& (board[x + xArr[1]][y + yArr[1]] == 1 || visitBoard[x + xArr[1]][y + yArr[1]] == 1)
				&& (board[x + xArr[2]][y + yArr[2]] == 1 || visitBoard[x + xArr[2]][y + yArr[2]] == 1)
				&& (board[x + xArr[3]][y + yArr[3]] == 1 || visitBoard[x + xArr[3]][y + yArr[3]] == 1)) {
			return true;
		}

		return false;

	}

	static int turnLeft(int dir) {
		if (dir == 0) {
			dir = 3;
		} else {
			dir -= 1;
		}
		return dir;
	}

	static boolean isOcean(int[][] board, int x, int y, int dir, int[] xArr, int[] yArr) {
		int afterX = x + xArr[dir];
		int afterY = y + yArr[dir];

		if (board[afterX][afterY] == 1) {
			return true;
		}
		return false;
	}

}
