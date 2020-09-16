import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ch12_3190 {
    static int n;

    static int headX = 1;
    static int headY = 1;
    
    static int tailX = 1;
    static int tailY = 1;

    static int[][] board; //뱀이 있는 곳은 1 아니면 0
    static int[][] apple; //사과가 있는 곳은 1 아니면 0

    static int[][] turnedPlace;
    static int t = 0; // 경과 시간

    static int headD = 0; // 현재 머리 방향
    static int tailD = 0; // 현재 꼬리 방향

    static int[] dirX = {0, 1, 0, -1};
    static int[] dirY = {1, 0, -1, 0}; // 시계방향 순(동 남 서 북)

    static LinkedList<Integer> timing = new LinkedList<>(); // 어느 타이밍에 틀지 저장한다
    static LinkedList<Integer> turn = new LinkedList<>(); // 어느 방향으로 틀지 저장한다

    static int timingVal = -1;
    static int turnVal = 0;
    static boolean isTurned = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n+1][n+1];
        apple = new int[n+1][n+1];
        turnedPlace = new int[n+1][n+1];

        for (int[] arr : turnedPlace) {
            Arrays.fill(arr,-1);
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k ; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            //사과가 있는 곳에 1 대입
            apple[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = 1;
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; (i < k); i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine()," ");
            timing.add(Integer.parseInt(stk.nextToken()));
            if (stk.nextToken().equals("D")) {
                turn.add(1);
            } else {
                turn.add(-1);
            }
        }
        start();
    }

    static void turnLeft() {
        if (--headD == -1) {
            headD = 3;
        }
    }

    static void turnRight() {
        if (++headD == 4) {
            headD = 0;
        }
    }

    static boolean go() {
        if (timing.isEmpty()==false && turn.isEmpty()==false && isTurned) {
            timingVal = timing.remove();
            turnVal = turn.remove();
            isTurned = false;
        }
        System.out.println("t = " + t);
        System.out.println("timing = " + timingVal);
        System.out.println("turnVal = " + turnVal);
        System.out.println("headD = " + headD);
        System.out.println("headX = " + headX);
        System.out.println("headY = " + headY);
        System.out.println("tailD = " + tailD);
        System.out.println("tailX = " + tailX);
        System.out.println("tailY = " + tailY);
        for (int[] arr : turnedPlace) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-----");

        if (timingVal == t) {
            //1이면 시계방향 -1이면 반시계방향
            if (turnVal == 1) {
            turnRight();
            } else {
            turnLeft();
            }
            System.out.println(" ** * * ** *" );
            System.out.println("headX = " + headX);
            System.out.println("headY = " + headY);
            System.out.println("headD = " + headD);

            turnedPlace[headX][headY] = headD;
            for (int[] arr : turnedPlace) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println(" ** * * ** *" );
            isTurned = true;
        }
        // 자신의 몸통에 닿거나 벽에 부딪치면 게임 오버
        if(
                (headX+dirX[headD]) > n ||
                (headX+dirX[headD]) < 1 ||
                (headY+dirY[headD]) > n ||
                (headY+dirY[headD]) < 1
        ){
            t++;
            System.out.println("링아웃");
            return false;
        }
        if(board[headX + dirX[headD]][headY + dirY[headD]] == 1){
            t++;
            System.out.println("몸통 박치기");
            return false;
        }

        // 사과가 없었을 경우 꼬리도 1칸 이동
        if (apple[headX + dirX[headD]][headY + dirY[headD]] == 0) {
            if (turnedPlace[tailX][tailY] != -1) {
                tailD = turnedPlace[tailX][tailY];
                turnedPlace[tailX][tailY] = -1;
            }
            board[tailX][tailY] = 0;
            tailX = tailX+dirX[tailD];
            tailY = tailY+dirY[tailD];
        } else {
            apple[headX + dirX[headD]][headY + dirY[headD]] = 0;
        }

        board[headX +dirX[headD]][headY +dirY[headD]] = 1;
        headX = headX + dirX[headD];
        headY = headY + dirY[headD];

        t++;
        return true;

    }

    static void start() {
        board[headX][headY] = 1;

        timingVal = timing.remove();
        turnVal = turn.remove();
        isTurned = false;

        while(go());

        for (int[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(t);
    }
}
