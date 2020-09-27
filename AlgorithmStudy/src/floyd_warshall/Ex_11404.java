package floyd_warshall;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex_11404 {
    static final int INF = 100_001; // 비용의 범위가 10만 이하의 자연수이므로
    static int[][] graph;
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            graph[start][end] = Math.min(graph[start][end], cost);
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    graph[j][k] = Math.min(graph[j][i] + graph[i][k], graph[j][k]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("0 ");
                } else {
                    System.out.print(graph[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
