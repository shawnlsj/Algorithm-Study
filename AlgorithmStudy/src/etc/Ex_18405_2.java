package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ex_18405_2 {
    static int n;
    static int k;
    static int[][] board;

    static int s;
    static int x;
    static int y;

    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};
    static LinkedList<Virus> q = new LinkedList<>();

    static class Virus {
        int i;
        int j;
        int t;
        int n;

        Virus(int i, int j, int t, int n) {
            this.i = i;
            this.j = j;
            this.t = t;
            this.n = n;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        board = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine()," ");
            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk2.nextToken());
                board[i][j] = x;
                if (x != 0) {
                    q.add(new Virus(i, j, 0, x));
                }
            }
        }
        q.sort(new Comparator<Virus>() {
            @Override
            public int compare(Virus o1, Virus o2) {
                return o1.n - o2.n;
            }
        });

        StringTokenizer stk3 = new StringTokenizer(br.readLine()," ");
        s = Integer.parseInt(stk3.nextToken());
        x = Integer.parseInt(stk3.nextToken());
        y = Integer.parseInt(stk3.nextToken());

        bfs();

        System.out.println(board[x][y]);
    }

    static void bfs() {
        while(!q.isEmpty()) {
            Virus v = q.poll();
            System.out.println("v.t = " + v.t);
            for (int[] arr : board) {
                System.out.println(Arrays.toString(arr));
            }

            System.out.println("----");
            if (v.t == s) return;

            for (int i = 0; i < rowDir.length; i++) {
                int afterRow = v.i + rowDir[i];
                int afterCol = v.j + colDir[i];

                if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                    continue;
                }
                if (board[afterRow][afterCol] == 0) {
                    board[afterRow][afterCol] = v.n;
                    q.add(new Virus(afterRow, afterCol, v.t + 1, v.n));
                }

            }
        }
    }



}
