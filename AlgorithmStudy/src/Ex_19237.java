import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex_19237 {
    static int n; // 보드 크기
    static int m; // 상어 개수
    static int k; // 냄새 지속시간
    static Node[][] board;
    static int[] rowDir = {0, -1, 1, 0, 0};//방향 1북 2남 3서 4동
    static int[] colDir = {0, 0, 0, -1, 1};

    static int time = 0;
    static ArrayList<Shark> sharks = new ArrayList<>();
    ; // 상어 목록

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        board = new Node[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(stk2.nextToken());
                board[i][j] = new Node();
                if (x != 0) {
                    Shark shark = new Shark(i, j, x);
                    board[i][j].add(shark);
                    board[i][j].arrangeShark();
                    sharks.add(shark);
                }
            }
        }

        sharks.sort((o1, o2) -> o1.sharkNum - o2.sharkNum); //상어 번호 오름차순 정렬
        sharks.add(0, null); // 인덱스와 상어번호를 맞추기 위해 null 삽입


        //현재 방향 입력 받기
        stk = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i < sharks.size(); i++) {
            sharks.get(i).dir = Integer.parseInt(stk.nextToken());
        }


        //방향 우선순위 입력 받기
        for (int i = 1; i < sharks.size(); i++) {
            for (int j = 1; j <= 4; j++) {
                StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
                //번호 i번 상어의 i번 방향의 우선순위
                for (int k = 1; k <= 4; k++) {
                    sharks.get(i).dirList.get(j).add(Integer.parseInt(stk2.nextToken()));
                }
            }
        }

        solution();
    }

    static void solution() {
        while (true) {

            for (int i = 1; i < sharks.size(); i++) {
                if (sharks.get(i).isLive) {
                    moveShark(sharks.get(i));
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    board[i][j].arrangeSmell();
                    if (board[i][j].sharkList.size() != 0) {
                        board[i][j].arrangeShark();
                    }
                }
            }
            time++;


            System.out.println("---smell num");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    System.out.print(board[i][j].smellNum+ " ");
                }
                System.out.println();
            }
            System.out.println("---smell time");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    System.out.print(board[i][j].smellTime+ " ");
                }
                System.out.println();
            }
            System.out.println("---size");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    System.out.print(board[i][j].sharkList.size()+ " ");
                }
                System.out.println();
            }
            System.out.println("-- shark live");
            for (int i = 1; i < sharks.size(); i++) {
                System.out.println(i+" 번 상어 "+sharks.get(i).isLive);
            }
            System.out.println();

            if (time == 1001) {
                if (checkOtherShark()) {
                    System.out.println(-1);
                } else {
                    System.out.println(time);
                }
                return;
            }
            if (time <= 1000 && checkOtherShark() == false) {
                System.out.println(time);
                return;
            }
        }
    }

    static boolean checkOtherShark() {
        boolean result = false;
        for (int i = 2; i < sharks.size(); i++) {
            if (sharks.get(i).isLive) {
                result = true;
                break;
            }
        }
        return result;
    }


    static void moveShark(Shark shark) {
        int afterRow; // 상어가 이동 한 뒤 row 값
        int afterCol; // 이동 한 뒤 col 값

        int dir = shark.dir;
        int smellDir = shark.dir;

        int tmpRow = 0;
        int tmpCol = 0;

        int smellRow = 0;
        int smellCol = 0;

        int numMovableNode = 0;
        int smellCnt = 0;

        //4방향에 대해 냄새가 없는 칸을 조사한다
        for (int i = 1; i <= 4; i++) {
            afterRow = shark.row + rowDir[i];
            afterCol = shark.col + colDir[i];

            if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                continue;
            }
            if (board[afterRow][afterCol].smellNum == 0) {

                numMovableNode++;
                tmpRow = afterRow;
                tmpCol = afterCol;
                dir = i;

            }
            if (board[afterRow][afterCol].smellNum == shark.sharkNum) {
                smellCnt++;
                smellRow = afterRow;
                smellCol = afterCol;
                smellDir = i;

            }
        }


        // 빈 칸이 1개 일 경우 그 칸으로 이동한다
        if (numMovableNode == 1) {
            board[shark.row][shark.col].remove(shark);
            shark.row = tmpRow;
            shark.col = tmpCol;
            shark.dir = dir;

            board[tmpRow][tmpCol].add(shark);
            return;
        }
        // 빈 칸이 2개 이상일 경우 규칙을 따라 이동한다
        if (numMovableNode >= 2) {
            for (int i = 1; i <= 4; i++) {
                board[shark.row][shark.col].remove(shark);

                dir = shark.dirList.get(shark.dir).get(i);
                afterRow = shark.row + rowDir[dir];
                afterCol = shark.col + colDir[dir];

                if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                    continue;
                }
                if (board[afterRow][afterCol].smellNum == 0) {
                    shark.row = afterRow;
                    shark.col = afterCol;
                    shark.dir = dir;
                    board[afterRow][afterCol].add(shark);
                    return;
                }
            }
            return;
        }
        
        //빈 칸이 0개일 경우에는 자기 냄새가 있는 칸으로 향한다
        // 냄새가 1칸 있는 경우
        if (smellCnt == 1) {
            board[shark.row][shark.col].remove(shark);

            shark.row = smellRow;
            shark.col = smellCol;
            shark.dir = smellDir;
            board[smellRow][smellCol].add(shark);
            return;
        }

        // 냄새가 2칸 이상 있는 경우
        for (int i = 1; i <= 4; i++) {
            board[shark.row][shark.col].remove(shark);

            smellDir = shark.dirList.get(shark.dir).get(i);
            afterRow = shark.row + rowDir[smellDir];
            afterCol = shark.col + colDir[smellDir];

            if (afterRow > n || afterCol > n || afterRow < 1 || afterCol < 1) {
                continue;
            }
            if (board[afterRow][afterCol].smellNum == shark.sharkNum) {
                shark.row = afterRow;
                shark.col = afterCol;
                shark.dir = smellDir;


                board[afterRow][afterCol].add(shark);
                return;
            }
        }
    }

    static class Node {
        ArrayList<Shark> sharkList = new ArrayList<>();
        int smellNum = 0;
        int smellTime;

        void add(Shark shark) {
            sharkList.add(shark);

        }
        void remove(Shark shark) {
            for (int i = 0; i < sharkList.size(); i++) {
                if (sharkList.get(i) == shark) {
                    sharkList.remove(i);
                }
            }
        }

        void arrangeSmell() {
            if (smellNum == 0 || sharkList.size() != 0) return;

            smellTime--;

            if (smellTime == 0) {
                smellNum = 0;
            }
        }

        void arrangeShark() {

            if (sharkList.size() == 1) {
                smellNum = sharkList.get(0).sharkNum;
                smellTime = k;
                return;
            }

            sharkList.sort((o1, o2) -> o1.sharkNum - o2.sharkNum);
            for (int i = sharkList.size() - 1; i > 0; i--) {
                sharkList.get(i).isLive = false;
                sharkList.remove(i);
            }
            smellNum = sharkList.get(0).sharkNum;
            smellTime = k;
        }

    }

    static class Shark {
        int row;
        int col;
        int sharkNum;

        boolean isLive;
        int dir;
        ArrayList<ArrayList<Integer>> dirList;

        Shark(int row, int col, int sharkNum) {
            this.row = row;
            this.col = col;
            this.sharkNum = sharkNum;
            isLive = true;

            dirList = new ArrayList<>();
            dirList.add(null);

            for (int i = 1; i <= 4; i++) {
                dirList.add(new ArrayList<>());
                dirList.get(i).add(null);
            }
        }
    }
}
