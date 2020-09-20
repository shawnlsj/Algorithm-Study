package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_18405 {
    static int n;
    static int k;
    static int[][] board;
    static int[][] copyBoard;

    static int s;
    static int x;
    static int y;



    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};
    static int[] virusArr;
    static int time = 0;

    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        board = new int[n + 1][n + 1];
        copyBoard = new int[n + 1][n + 1];
        virusArr = new int[k + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine()," ");
            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk2.nextToken());
                board[i][j] = x;
                if (x != 0) {
                    virusArr[x] = 1;
                }
            }
        }
        StringTokenizer stk3 = new StringTokenizer(br.readLine()," ");
        s = Integer.parseInt(stk3.nextToken());
        x = Integer.parseInt(stk3.nextToken());
        y = Integer.parseInt(stk3.nextToken());


        while(time < s) {
            for (int i = 1; i < virusArr.length; i++) {
                if (virusArr[i] != 0) {
                    spreadVirus(i);
                }
            }
            time++;
        }

        if (board[x][y] == 0) {
            System.out.println(0);
        } else {
            System.out.println(board[x][y]);
        }


    }

    static void spreadVirus(int v) {
        int afterRow = 0;
        int afterCol = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(visited[i][j]) continue;

                if (board[i][j] == v) {
                    visited[i][j] = true;
                    for (int k = 0; k < rowDir.length; k++) {
                        afterRow = i + rowDir[k];
                        afterCol = j + colDir[k];
                        if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                            continue;
                        }
                        if(board[afterRow][afterCol] == 0){
                            copyBoard[afterRow][afterCol] = v;
                        }
                    }//for k
                }//if

            }//for j
        }//for i
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (copyBoard[i][j] == v) {
                    board[i][j] = v;
                }
            }
        }
    }//spreadVirus()
}
