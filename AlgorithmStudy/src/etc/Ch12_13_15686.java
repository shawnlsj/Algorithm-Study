package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ch12_13_15686 {
    static int[][] board;
    static int n;
    static int m; //nCr에서 r을 담당한다
    static int chickenCnt; // nCr 에서 n을 담당한다

    static ArrayList<Chicken> chickenList = new ArrayList<>();
    static Queue<Chicken> workList = new LinkedList<>();
    static LinkedList<Integer> combinationList = new LinkedList<>();

    static Home[][] homeBoard;
    static final int INF = (int) 1e9;
    static int cnt = 0;
    static int answer = INF;

    static class Home {
        int[][] distanceBoard;
        int chickenDistance = INF;
    }

    static class Chicken {
        int i;
        int j;
        Chicken(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        board = new int[n + 1][n + 1];
        homeBoard = new Home[n + 1][n + 1];
        chickenList.add(null);
        //board는 집과 치킨집의 위치를 저장한다 1이면 집 2이면 치킨집
        //homeBoard는 board에서 집의 위치에 Home객체를 저장한다
        //집 객체 마다 distanceBoard를 가진다, 치킨집의 위치에 집과 치킨집 사이의 거리가 저장된다
        for (int i = 1; i <= n; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {

                int x = Integer.parseInt(stk2.nextToken());
                board[i][j] = x;

                if (x == 1) {
                    homeBoard[i][j] = new Home();
                    homeBoard[i][j].distanceBoard = new int[n + 1][n + 1];
                }
                if (x == 2) {
                    chickenCnt++;
                    chickenList.add(new Chicken(i, j));
                }

            }//for j
        }//for i

        //-----입력 부 끝 -----


        //백 트래킹
        for (int i = 1; i <= chickenCnt; i++) {
            dfs(i);
        }

        // 백 트래킹에서 찾은 경우의 수에 해당 하는 치킨 거리를 구한 후
        // 그 중에서 최소값 찾기
        while (!workList.isEmpty()) {
            for (int i = 0; i < m; i++) {
                Chicken c = workList.poll();
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k <= n; k++) {
                        if(homeBoard[j][k] == null) {
                            continue;
                        }
                        homeBoard[j][k].distanceBoard[c.i][c.j] = -1;
                    }
                }
            }
            calChickenDistance();
            answer = Math.min(answer, getSumOfChickenDistance());

            clean();
        }

        System.out.println(answer);

    }

    static void clean(){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(homeBoard[i][j] == null) continue;
                for (int[] arr : homeBoard[i][j].distanceBoard) {
                    Arrays.fill(arr, 0);
                }
                homeBoard[i][j].chickenDistance = INF;
            }
        }
    }
    static void calChickenDistance() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j] == 1) {
                    //치킨 집의 위치는 (k,l)
                    //집의 위치는 (i,j)
                    for (int k = 1; k <= n; k++) {
                        for (int l = 1; l <= n; l++) {
                            if(homeBoard[i][j] == null) continue;
                            if (homeBoard[i][j].distanceBoard[k][l] == -1) {
                                homeBoard[i][j].chickenDistance =
                                        Math.min(Math.abs(k - i) + Math.abs(l - j), homeBoard[i][j].chickenDistance); //치킨 거리
                            }
                        }
                    }//for k
                }//if
            }//for j
        } //for i
    }

    static int getSumOfChickenDistance() {
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(homeBoard[i][j] == null) continue;
                sum += homeBoard[i][j].chickenDistance;
            }
        }
        return sum;
    }

    static void dfs(int x){

        if(x + (m - (cnt+1))> chickenCnt) return;

        combinationList.add(x);
        cnt++;

        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                workList.add(chickenList.get(combinationList.get(i)));
            }
            combinationList.removeLast();
            cnt--;
            return;
        }

        for (int i = x + 1; i <= chickenCnt; i++) {
            dfs(i);
        }

        cnt--;
        combinationList.removeLast();
    }
}
