package floyd_warshall;

import sun.awt.image.ImageWatched;;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FloydWarshall {

    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //노드의 종류
        int kindOfNode = Integer.parseInt(br.readLine());

        //간선 개수
        int numEdge = Integer.parseInt(br.readLine());

        Graph graph = new Graph(kindOfNode);

        //출발노드, 도착노드, 거리를 입력받는다
        for (int i = 0; i < numEdge; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            int nodeNum = Integer.parseInt(stk.nextToken());
            int targetNode = Integer.parseInt(stk.nextToken());
            int distance = Integer.parseInt(stk.nextToken());

            graph.add(nodeNum, targetNode, distance);
        }

        floydWarshall(graph);
    }

    static class Node {
        private int targetNode;
        private int distance;

        public Node(int targetNode, int distance) {
            this.targetNode = targetNode;
            this.distance = distance;
        }
    }

    static class Graph {

        //바깥의 ArrayList의 크기는 노드의 개수 + 1 이다
        //예를들면 노드가 1번~6번까지 있으면 ArrayList 크기는 7이 된다
        //안쪽의 LinkedList 에는 해당 노드에 연결된 노드 리스트가 저장된다
        private ArrayList<LinkedList<Node>> list;

        Graph(int kindOfNode) {
            list = new ArrayList<LinkedList<Node>>(kindOfNode + 1);

            for (int i = 0; i < kindOfNode + 1; i++) {
                list.add(i, new LinkedList<Node>());
            }
        }

        public void add(int nodeNum, int targetNode, int distance) {
            list.get(nodeNum).add(new Node(targetNode, distance));
        }

        public ArrayList<LinkedList<Node>> getList() {
            return list;
        }
    }

    public static void floydWarshall(Graph graph) {
        ArrayList<LinkedList<Node>> list = graph.getList();

        int[][] dp = new int[list.size()][list.size()];

        // 자기 자신으로 가는 거리는 0, 나머지 경우는 INF를 저장한다
        for (int i = 1; i < list.size(); i++) {
            for (int j = 1; j < list.size(); j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = INF;
                }
            }
        }
        for (int i = 1; i < list.size(); i++) {
            LinkedList<Node> linkedList = list.get(i);
            for (int j = 0; j < linkedList.size(); j++) {
                dp[i][linkedList.get(j).targetNode] = linkedList.get(j).distance;
            }
        }
        //k번 노드를 거쳐가는 경우에 대하여
        //i번에서 j번으로 바로 가는 거보다, k번 노드를 거치는 경우가 짧은지 비교한다
        for (int i = 1; i < list.size(); i++) {
            for (int j = 1; j < list.size(); j++) {
                for (int k = 1; k < list.size(); k++) {
                    if(i==j || j==k) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }


        for (int i = 1; i < list.size(); i++) {
            for (int j = 1; j < list.size(); j++) {
                if (dp[i][j] == INF) {
                    System.out.println("0");
                } else
                    System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }


    }
}
