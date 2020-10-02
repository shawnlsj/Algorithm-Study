package dfs;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_19236 {
    static int n = 4;
    static Fish[][] board;

    static int[] rowDir = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] colDir = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    static int row;
    static int col;
    static int dir;

    static int sumOfFishNum;
    static int max;
    static ArrayList<Fish> fishes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new Fish[n + 1][n + 1];

        for (int i = 1; i <= 4; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 4; j++) {
                Fish fish = new Fish(i, j, Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
                board[i][j] = fish;
                fishes.add(fish);
            }
        }

        //물고기 목록을 번호 기준 오름차순 으로 정렬
        fishes.sort((o1, o2) -> o1.num - o2.num);
        eatFish(board[1][1]);
        System.out.println("------");
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(board[i][j].visited + " ");
            }
            System.out.println();
        }


        dfs();


        System.out.println("sumOfFishNum = " + sumOfFishNum);
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(board[i][j].num + " ");
            }
            System.out.println();
        }
        System.out.println("------");
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(board[i][j].dir + " ");
            }
            System.out.println();
        }
        System.out.println("------");
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(board[i][j].visited + " ");
            }
            System.out.println();
        }


        System.out.println(max);
    }

    static void deepCopy(Fish[][] originalBoard, Fish[][] copyBoard, ArrayList<Fish> copyList) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Fish fish = originalBoard[i][j];
                Fish copyFish = new Fish(fish.row, fish.col, fish.num, fish.dir);
                copyFish.visited = fish.visited;
                copyBoard[i][j] = copyFish;
                copyList.add(copyFish);
            }
        }
        copyList.sort((o1, o2) -> o1.num - o2.num);
    }

    static void dfs() {

        int afterRow = row;
        int afterCol = col;

        int copyRow = row;
        int copyCol = col;
        int copyDir = dir;

        Fish[][] copyBoard = new Fish[n + 1][n + 1];
        ArrayList<Fish> copyList = new ArrayList<>();

        deepCopy(board, copyBoard, copyList);

        while (true) {

            afterRow = afterRow + rowDir[copyDir];
            afterCol = afterCol + colDir[copyDir];

            //벽을 만나면 리턴한다
            if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                max = Math.max(max, sumOfFishNum);
                ;
                System.out.println("-- 빼기 전 --");
                System.out.println("sumOfFishNum = " + sumOfFishNum);
                sumOfFishNum -= board[row][col].num;
                System.out.println("board[row][col].num = " + board[row][col].num);
                System.out.println("-- 빼기 후 --");
                System.out.println("sumOfFishNum = " + sumOfFishNum);
                return;
                //빈 자리를 만나면 한번 더 간다
            } else if (board[afterRow][afterCol].visited) {
                continue;
            }

            //벽도 아니고 빈자리도 아니면 물고기가 있다는 것이니 잡아먹는다
            eatFish(board[afterRow][afterCol]);
            dfs();
            fishes = new ArrayList<>();
            deepCopy(copyBoard, board, fishes);
            row = copyRow;
            col = copyCol;
            dir = copyDir;
        }
    }

    static void eatFish(Fish fish) {
        fish.visited = true;
        sumOfFishNum += fish.num;
        row = fish.row;
        col = fish.col;
        dir = fish.dir;
        moveFish();
    }

    static void moveFish() {
        int afterRow;
        int afterCol;

        for (int i = 0; i < fishes.size(); i++) {
            Fish fish = fishes.get(i);
            if (fish.visited) continue;
            while (true) {
                afterRow = fish.row + rowDir[fish.dir];
                afterCol = fish.col + colDir[fish.dir];
                if (afterRow > n || afterCol > n ||
                        afterRow < 1 || afterCol < 1 ||
                        (afterRow == row && afterCol == col)) {
                    fish.dir = (fish.dir + 1) == 9 ? 1 : (fish.dir + 1);
                    continue;
                }
                swap(fish);
                break;
            }
        }
    }

    static void swap(Fish fish) {
        int afterRow = fish.row + rowDir[fish.dir];
        int afterCol = fish.col + colDir[fish.dir];

        Fish tmpFish = board[afterRow][afterCol];
        board[afterRow][afterCol] = fish;
        board[fish.row][fish.col] = tmpFish;

        tmpFish.row = fish.row;
        tmpFish.col = fish.col;

        fish.row = afterRow;
        fish.col = afterCol;
    }

    static class Fish {
        int row;
        int col;
        int dir;
        int num;
        boolean visited;

        public Fish(int row, int col, int num, int dir) {
            this.row = row;
            this.col = col;
            this.num = num;
            this.dir = dir;
        }
    }
}
