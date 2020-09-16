package kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Ch10_3 {

    static int[] parentArr = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        int v = Integer.parseInt(stk.nextToken());
        int e = Integer.parseInt(stk.nextToken());

        Edge[] edgeArr = new Edge[e];

        for (int i = 1; i <= v; i++) {
            parentArr[i] = i;
        }

        for (int i = 0; i < e; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");

            int node1 = Integer.parseInt(stk2.nextToken());
            int node2 = Integer.parseInt(stk2.nextToken());
            int cost = Integer.parseInt(stk2.nextToken());

            edgeArr[i] = new Edge(node1, node2, cost);
        }

        Arrays.sort(edgeArr, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });

        int result = 0;
        int max = 0;
        for (int i = 0; i < edgeArr.length; i++) {

            Edge edge = edgeArr[i];

            if (findParent(edge.node1) != findParent(edge.node2)) {

                result += edge.cost;
                max = Math.max(edge.cost, max);
                union(edge.node1, edge.node2);
            }
        }
        System.out.println(Math.abs(result-max));


    }
    static void union(int a, int b) {
        int p1 = findParent(a);
        int p2 = findParent(b);

        if (p1 < p2) {
            parentArr[p2] = p1;
        } else {
            parentArr[p1] = p2;
        }
    }

    static int findParent(int a){
        if (parentArr[a] == a) {
            return a;
        } else {
            return parentArr[a] = findParent(parentArr[a]);
        }
    }


    static class Edge {
        int node1;
        int node2;
        int cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }
}
