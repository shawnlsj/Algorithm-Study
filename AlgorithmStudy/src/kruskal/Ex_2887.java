package kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ex_2887 {
    static int n;
    static ArrayList<Planet> xList= new ArrayList<>();
    static ArrayList<Planet> yList= new ArrayList<>();
    static ArrayList<Planet> zList= new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            Planet p = new Planet(x, y, z);
            xList.add(p);
            yList.add(p);
            zList.add(p);

            parent[i] = i;
        }

        xList.sort((o1, o2) -> o1.x - o2.x);
        yList.sort((o1, o2) -> o1.y - o2.y);
        zList.sort((o1, o2) -> o1.z - o2.z);

        for (int i = 1; i < n; i++) {
            edges.add(new Edge(xList.get(i-1), xList.get(i)));
            edges.add(new Edge(yList.get(i-1), yList.get(i)));
            edges.add(new Edge(zList.get(i-1), zList.get(i)));
        }

        edges.sort((o1, o2) -> o1.cost - o2.cost);

        for (int i = 0; i < edges.size(); i++) {
            if (findParent(edges.get(i).nodeNumA) != findParent(edges.get(i).nodeNumB)) {
                union(edges.get(i).nodeNumA, edges.get(i).nodeNumB);
                answer += edges.get(i).cost;
            }
        }
        System.out.println(answer);
    }

    static int findParent(int nodeNum) {
        if (parent[nodeNum] == nodeNum) return nodeNum;
        return parent[nodeNum] = findParent(parent[nodeNum]);
    }

    static void union(int nodeNumA, int nodeNumB) {
        int p = findParent(nodeNumA);
        int p2 = findParent(nodeNumB);
        if (p < p2) {
            parent[p2] = parent[p];
        } else {
            parent[p] = parent[p2];
        }
    }

    static class Edge {
        int nodeNumA;
        int nodeNumB;
        int cost;

        Edge(Planet nodeA, Planet nodeB) {
            nodeNumA = nodeA.nodeNum;
            nodeNumB = nodeB.nodeNum;

            int a = Math.abs(nodeA.x - nodeB.x);
            int b = Math.abs(nodeA.y - nodeB.y);
            int c = Math.abs(nodeA.z - nodeB.z);
            cost = Math.min(a, Math.min(b, c));
        }
    }

    static class Planet {
        static int pk = 1;
        int nodeNum;
        int x;
        int y;
        int z;

        public Planet(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            nodeNum = pk;
            pk++;
        }

    }
}
