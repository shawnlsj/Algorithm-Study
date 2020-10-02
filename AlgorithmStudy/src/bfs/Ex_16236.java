package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_16236 {
    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};
    static int[][] dirBoard;
    static int sharkDir;
    static int[] fishCntArr = new int[6 + 1];

    static int n;
    static int[][] board;
    static boolean[][] visited;

    static final int INF = (int) 1e9;
    static int time; // 현재까지 흐른 시간
    static int exp; // 아기상어의 경험치
    static int row; // 현재 세로 위치
    static int col; // 현재 가로 위치
    static int min;
    static int numFish; // 물고기의 수
    static int distance; // 물고기까지의 거리
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


        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk.nextToken());
                board[i][j] = x;
                if (x == 9) {
                    row = i;
                    col = j;
                } else if (x > 0) {
                    numFish++;
                    fishCntArr[x]++;
                }
            }
        }

        double start = System.currentTimeMillis();
        while (numFish > 0) {

            min = INF;
            fishes = new ArrayList<>();
            dirBoard[row][col] = -1;
            dfs();
            if (fishes.size() == 0) {
                break;
            }

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
            fishCntArr[fish.size]--;
            numFish--;
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
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(time);
    }//main

    static boolean checkFishes() {
        for (int i = 1; i <= 6; i++) {
            if (i < sharkSize && fishCntArr[i] > 0) {
                return true;
            }
        }
        return false;
    }
    static void dfs() {
        if (distance > min || checkFishes() == false) {
            back();
            return;
        }

        visited[row][col] = true;

        int afterRow;
        int afterCol;

        if ((board[row][col] > 0 && board[row][col] < sharkSize) && board[row][col] != 9) {
            fishes.add(new Fish(row, col, distance, board[row][col]));
            min = Math.min(min, distance);
            back();
            return;
        }
        System.out.println("-----");

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
                for (int m = 1; m <= n; m++) {
                    for (int z = 1; z <= n; z++) {
                        if (visited[m][z] == false) {
                            System.out.print(0 + " ");
                        } else {
                            System.out.print(1 + " ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("distance = " + distance);
                dfs();
            }
        }
        System.out.println("-------");
        back();
    }

    static void back() {
        if (dirBoard[row][col] == -1) {
            visited[row][col] = false;
            return;
        }

        visited[row][col] = false;
        sharkDir = (dirBoard[row][col] + 2) % 4; // 상어의 방향을 반대 방향으로 돌린다
        row = row + rowDir[sharkDir];
        col = col + colDir[sharkDir];
        distance--;
    }

    static class Fish {
        int row;
        int col;
        int distance;
        int size;

        Fish(int row, int col, int distance, int size) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.size = size;
        }
    }
}
