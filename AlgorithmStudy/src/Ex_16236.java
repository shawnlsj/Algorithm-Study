import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_16236 {
    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};
    static int[][] dirBoard;
    static int sharkDir;

    static int n;
    static int[][] board;
    static boolean[][] visited;

    static int time;
    static int exp;
    static int row;
    static int col;
    static int distance;
    static int sharkSize = 2;
    static ArrayList<Fish> fishes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        dirBoard = new int[n + 1][n + 1];
        exp = 0;
        distance = 0;

        for (int[] arr : dirBoard) {
            Arrays.fill(arr, -1);
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                board[i][j] = x;
                if (x == 9) {
                    row = i;
                    col = j;
                }
            }
        }
        while (true) {
            fishes = new ArrayList<>();
            dirBoard[row][col] = -1;
            dfs();
            if (fishes.size() == 0){
                System.out.println(" 보드 상태 ");
                System.out.println("sharkSize = " + sharkSize);
                for (int[] arr : board) {
                    System.out.println(Arrays.toString(arr));
                }
                System.out.println("방향 상태");
                for (int[] arr : dirBoard) {
                    System.out.println(Arrays.toString(arr));
                }
                System.out.println("방문 상태");
                for (boolean[] arr : visited) {
                    System.out.println(Arrays.toString(arr));
                }
                System.out.println("---");
                break;}

            fishes.sort((o1, o2) -> {
                if (o1.distance != o2.distance) {
                    return o1.distance - o2.distance;
                } else if (o1.row != o2.row) {
                    return o1.row - o2.row;
                } else {
                    return o1.col - o2.col;
                }
            });

            //물고기 위치로 이동해 물고기를 먹는다
            Fish fish = fishes.get(0);
            System.out.println("fish.distance = " + fish.distance);
            time += fish.distance;
            exp++;
            if (sharkSize == exp) {
                exp = 0;
                sharkSize++;
            }
            board[row][col] = 0;
            row = fish.row;
            col = fish.col;
            board[row][col] = 9;

        }
        System.out.println(time);




    }//main

    static void dfs() {
//        System.out.println("--현재 위치--");
//        for (int[] arr : board) {
//            System.out.println(Arrays.toString(arr));
//        }
//        System.out.println("거리 = " + distance);
//        System.out.println("row = " + row);
//        System.out.println("col = " + col);
//        System.out.println("방문 상태");
//        for (boolean[] arr : visited) {
//            System.out.println(Arrays.toString(arr));
//        }
//        System.out.println("방향 상태");
//        for (int[] arr : dirBoard) {
//            System.out.println(Arrays.toString(arr));
//        }


        visited[row][col] = true;

        int afterRow;
        int afterCol;

        if (board[row][col] > 0 && board[row][col] < sharkSize) {
            fishes.add(new Fish(row, col, distance));
            back();
            return;
        }


        for (int i = 0; i < 4; i++) {
            afterRow = row + rowDir[i];
            afterCol = col + colDir[i];
            if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                continue;
            }
            if (visited[afterRow][afterCol]) {
                continue;
            }

            if (board[afterRow][afterCol] <= sharkSize) {
                sharkDir = i; // 아기상어의 방향 설정
                row = afterRow;
                col = afterCol;
                dirBoard[afterRow][afterCol] = i;
                distance++;
                dfs();
            }
        }
        back();
    }
    static void back() {
        if (dirBoard[row][col] == -1){ visited[row][col] = false; return;}

        visited[row][col] = false;
        sharkDir = (dirBoard[row][col] + 2) % 4; // 상어의 방향을 반대 방향으로 돌린다
        row = row + rowDir[sharkDir];
        col = col + colDir[sharkDir];
        distance--;
    }

    static class Fish{
        int row;
        int col;
        int distance;

        Fish(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
