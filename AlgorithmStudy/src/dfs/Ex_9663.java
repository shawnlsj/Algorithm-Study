package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Ex_9663 {
    static int n;
    static int[][] board;
    static int answer = 0;
    static int queenCnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            System.out.println("queenCnt = " + queenCnt);
            System.out.println("dfs("+(1)+", "+i+")");
            System.out.println("answer = " + answer);
            for (int arr[] : board) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println("----");
            dfs(1, i);
            clean();
        }

        System.out.println(answer);
    }

    static void dfs(int row, int col) {
        if(row > n) return;
        int col2 = col; // col 값 복사
        queenCnt++; // 퀸을 보드에 놓는다.

        if (queenCnt == n) {
            answer++;
            queenCnt--;
            return;
        }

        System.out.println("dfs("+(row)+", "+col+")"+"에 퀸 놓고 x표시 전 ");
        System.out.println("queenCnt = " + queenCnt);
        for (int arr[] : board) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("----");

        //------- 퀸의 영향권에 x표시 하기 ---------
        //세로 줄에 x 표시
        for (int i = row + 1; i <= n; i++) {
            board[i][col2]++;
        }

        //왼쪽 대각선 x표시
        col2--;
        for (int i = row + 1; i >= 1; i++) {
            if (col2 < 1 || i > n) break;
            board[i][col2]++;
            --col2;
        }

        col2 = col; // 원래 col값으로 초기화

        //오른쪽 대각선 x표시
        col2++;
        for (int i = row + 1; i <= n; i++) {

            if (col2 > n || i > n) break;
            board[i][col2]++;
            col2++;
        }

        //------- 퀸의 영향권에 x표시 하기 끝---------
        System.out.println("dfs("+(row)+", "+col+")"+"에 퀸 놓고 x표시 후 ");
        System.out.println("queenCnt = " + queenCnt);
        for (int arr[] : board) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("----");

        for (int i = 1; i <= n; i++) {
            if(board[row+1][i] > 0) continue;
            System.out.println("queenCnt = " + queenCnt);
            System.out.println("호출 dfs("+(row+1)+", "+i+")");
            for (int arr[] : board) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println("----");
            dfs(row + 1, i);
        }

        col2 = col;
        //------- 자신의 영향권에 있던 x 표시 지우기 ---------
        //세로 줄에 x 표시
        for (int i = row + 1; i <= n; i++) {
            board[i][col2]--;
        }

        //왼쪽 대각선 x표시 지우기
        col2--;
        for (int i = row + 1; i >= 1; i++) {
            if (col2 < 1 || i > n) break;
            board[i][col2]--;
            --col2;
        }

        col2 = col; // 원래 col값으로 초기화

        //오른쪽 대각선 x표시 지우기
        col2++;
        for (int i = row + 1; i <= n; i++) {

            if (col2 > n || i > n) break;
            board[i][col2]--;
            col2++;
        }
        //------- 끝---------

        queenCnt--;
        return;

    }

    static void clean(){
        for (int[] arr : board) {
            Arrays.fill(arr, 0);
        }
        queenCnt = 0;
    }
}
