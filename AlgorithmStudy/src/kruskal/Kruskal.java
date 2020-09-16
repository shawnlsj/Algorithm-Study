package kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Kruskal {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int numNode = Integer.parseInt(stk.nextToken());
        int numEdge = Integer.parseInt(stk.nextToken());

        int[] parentArr = new int[numNode + 1]; // 인덱스 번호 노드의 루트노드 정보를 요소로 담고있는 배열
        Edge[] edgeArr = new Edge[numEdge]; // 간선을 요소로 담고 있는 배열

        for (int i = 1; i < parentArr.length; i++) {
            parentArr[i] = i;
        }
        
        //간선 정보를 입력 받는다
        for (int i = 0; i < numEdge; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.parseInt(stk2.nextToken());
            int node2 = Integer.parseInt(stk2.nextToken());
            int cost = Integer.parseInt(stk2.nextToken());

            edgeArr[i] = new Edge(node1, node2, cost);
        }

        //간선 비용에 따라 오름차순으로 정렬한다
        Arrays.sort(edgeArr, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost-o2.cost;
            }
        });

        int result = 0;

        for (int i = 0; i < edgeArr.length; i++) {
            Edge edge = edgeArr[i];
            if (findParent(parentArr, edge.node1) != findParent(parentArr,edge.node2)) {
                union(parentArr, edge.node1, edge.node2);
                result += edge.cost;
            }
        }

        System.out.println(result);


    }

    static int findParent(int[] parentArr, int targetNode) {
        if (parentArr[targetNode] == targetNode) {
            return targetNode;
        }
        return parentArr[targetNode] = findParent(parentArr, parentArr[targetNode]);
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

    static void union(int[] parentArr, int node1, int node2) {
        int p1 = findParent(parentArr, node1);
        int p2 = findParent(parentArr, node2);
        if (p1 < p2) {
            parentArr[p2] = p1;
        } else {
            parentArr[p1] = p2;
        }


    }

}
