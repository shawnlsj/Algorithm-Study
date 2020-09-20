import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ex_14502 {
    static int n;
    static int m;
    static int[][] board;
    static int[][] copyBoard;

    static int[] rowDir = {1,0,-1,0};
    static int[] colDir = {0,1,0,-1};

    static int numZero;

    static int cnt = 0; // 조합에서 현재까지 선택 된 요소의 수를 카운트할 변수
    static int zeroCnt = 0;

    static int answer;
    static LinkedList<Integer> list = new LinkedList<>();
    static ArrayList<Barrier> barriers = new ArrayList<>();

    static LinkedList<Barrier> combinationList = new LinkedList<>();

    static class Barrier{
        int i;
        int j;

        Barrier(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {

        //-- 입력부 --
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        board = new int[n+1][m+1];
        copyBoard = new int[n+1][m+1];
        barriers.add(null);

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                int x = Integer.parseInt(stk2.nextToken());
                board[i][j] = x;
                if (x == 0) {
                    numZero++;
                    barriers.add(new Barrier(i, j));
                }
            }
        }
        //-- 입력부 끝 --

        //값 복사하기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        for (int[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("=====");
        for (int[] arr : copyBoard) {
            System.out.println(Arrays.toString(arr));
        }


        //벽을 놓을 수 있는 경우의 수 구하기
        for (int i = 1; i <= numZero; i++) {
            dfs(i);
        }

        while (!combinationList.isEmpty()) {
            // nC3 이니까 큐에서 3개를 뽑는다
            // 해당 위치에 벽을 세운다
            for (int i = 0; i < 3; i++) {
                Barrier b = combinationList.poll();
                copyBoard[b.i][b.j] = 1;
            }
            //바이러스를 퍼뜨린다
            simulate();
            
            //안전 지대의 개수를 구한다
            getResult();
            
            //입력부가 끝난 직후의 보드로 되돌린다
            reset();
        }

        System.out.println(answer);

    }

    static void reset(){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        zeroCnt = 0;
    }

    static void getResult() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (copyBoard[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }
        answer = Math.max(answer, zeroCnt);
    }

    static void simulate(){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (copyBoard[i][j] == 2) {
                    spreadVirus(i, j);
                }
            }
        }

    }
    static void spreadVirus(int row, int col) {
        for (int i = 0; i < rowDir.length; i++) {
            int afterRow = row+rowDir[i];
            int afterCol = col+colDir[i];
            if (afterRow > n || afterRow < 1 || afterCol > m || afterCol < 1) {
                continue;
            }
            if (copyBoard[afterRow][afterCol] == 0) {
                copyBoard[afterRow][afterCol] = 2;
                spreadVirus(afterRow, afterCol);
            }
        }


    }
    static void dfs(int x){

        if(x + (3 - (cnt+1))> numZero) return;

        list.add(x);
        cnt++;

        if (cnt == 3) {
            for (int i = 0; i < 3; i++) {
                combinationList.add(barriers.get(list.get(i)));
            }
            list.removeLast();
            cnt--;
            return;
        }

        for (int i = x + 1; i <= numZero; i++) {
            dfs(i);
        }

        cnt--;
        list.removeLast();
    }
}
