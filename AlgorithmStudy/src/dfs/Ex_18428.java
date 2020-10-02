package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ex_18428 {

    static int n;
    static String[][] board;
    static String[][] copyBoard;

    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};

    static Queue<Integer> combinations = new LinkedList<>();

    static ArrayList<Barrier> barriers = new ArrayList<>();
    static ArrayList<Teacher> teachers = new ArrayList<>();

    static boolean isSearched;
    static boolean result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new String[n + 1][n + 1];
        copyBoard = new String[n + 1][n + 1];

        barriers.add(null);

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                String s = stk.nextToken();
                if (s.equals("X")) {
                    barriers.add(new Barrier(i, j));
                }
                if (s.equals("T")) {
                    teachers.add(new Teacher(i, j));
                }
                board[i][j] = s;
                copyBoard[i][j] = s;
            }
        }

        Combination.n = barriers.size() - 1;
        Combination.r = 3;
        Combination.combination();

        while (!combinations.isEmpty()) {
            isSearched = false;
            for (int i = 0; i < 3; i++) {
                Barrier barrier = barriers.get(combinations.poll());
                copyBoard[barrier.row][barrier.col] = "O";
            }

            check();

            if (result) {
                break;
            }


            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }
        }

        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


    }

    static void check() {
        for (int i = 0; i < teachers.size(); i++) {

            Teacher teacher = teachers.get(i);
            searchStudent(teacher);
            if (isSearched) {
                return;
            }
        }

        result = true;
    }

    static void searchStudent(Teacher teacher) {
        for (int i = 0; i < 4; i++) {
            dfs(teacher.row, teacher.col, i);
            if (isSearched) return;
        }
    }
    static void dfs(int i, int j, int direction) {
        int afterRow = i + rowDir[direction];
        int afterCol = j + colDir[direction];

        if(afterRow < 1 || afterCol < 1 || afterRow > n || afterCol > n) return;
        if(copyBoard[afterRow][afterCol].equals("O")) return;

        if (copyBoard[afterRow][afterCol].equals("S")) {
            isSearched = true;
            return;
        }

        dfs(afterRow, afterCol, direction);
    }
    static class Barrier{
        int row;
        int col;

        Barrier(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static class Teacher{
        int row;
        int col;

        Teacher(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Combination {

        static int n; // nCr 에서 n
        static int r; // nCr 에서 r
        static int cnt = 0; // 현재까지 뽑은 요소의 수를 카운트할 변수
        static LinkedList<Integer> elements = new LinkedList<>(); // 뽑은 요소를 저장할 리스트

        static void combination() {
            for (int i = 1; i <= n; i++) {
                dfs(i);
            }
        }

        static void dfs(int x) {

            if (x + (r - (cnt + 1)) > n) return;

            elements.add(x);
            cnt++;

            //요소를 r개만큼 뽑았으면 조합을 출력
            if (cnt == r) {
                for (int i = 0; i < r; i++) {
                    combinations.add(elements.get(i));
                }
                elements.removeLast();
                cnt--;
                return;
            }

            for (int i = x + 1; i <= n; i++) {
                dfs(i);
            }

            cnt--;
            elements.removeLast();
        }

    }
}
