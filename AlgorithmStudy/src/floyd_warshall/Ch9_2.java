package floyd_warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ch9_2 {
    private static int INF = (int)1e9;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int numNode = Integer.parseInt(stk.nextToken());
        int numEdge = Integer.parseInt(stk.nextToken());

        int[][] dp = new int[numNode + 1][numNode + 1];


        // 자기자신으로 가는 거리는 0, 나머지는 무한으로 설정
        for (int i = 1; i <= numNode; i++) {
            for (int j = 1; j <= numNode; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = INF;
                }
            }
        }

        // 양방향 간선이기 때문에 반대 방향으로도 테이블을 초기화한다
        for (int i = 0; i < numEdge; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stk2.nextToken());
            int b = Integer.parseInt(stk2.nextToken());
            dp[a][b] = 1;
            dp[b][a] = 1;
        }

        // i==j인 경우, 자기 자신으로가는 거리보다 더 짧은 경우는 없으므로 패스한다
        // j==k인 경우, i->j 와 i->k->j 를 비교하는 의미가 없으므로 패스한다
        for (int i = 1; i <= numNode; i++) {
            for (int j = 1; j <= numNode; j++) {
                for (int k = 1; k <= numNode; k++) {
                    if(i==j || j==k) continue;
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]);
                }
            }
        }

        StringTokenizer stk3 = new StringTokenizer(br.readLine()," ");
        int x = Integer.parseInt(stk3.nextToken());
        int k = Integer.parseInt(stk3.nextToken());

        if (dp[1][k] == INF || dp[k][x] == INF) {
            System.out.println(-1);
        } else
            System.out.println(dp[1][k] + dp[k][x]);

    }

}
