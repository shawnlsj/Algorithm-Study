package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SimpleDijkstra {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int kindOfNode = Integer.parseInt(stk.nextToken()); //노드의 종류
        int NumOfEdge = Integer.parseInt(stk.nextToken()); // 간선의 개수

        int startNode = Integer.parseInt(br.readLine()); // 시작 노드

        Graph graph = new Graph(kindOfNode);

        //node 노드 번호
        //targetNode 목표 노드 번호
        //distance 목표 노드까지의 거리
        for (int i = 0; i < NumOfEdge; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine()," ");
            int node = Integer.parseInt(stk2.nextToken());
            int targetNode = Integer.parseInt(stk2.nextToken());
            int distance = Integer.parseInt(stk2.nextToken());

            graph.add(node, targetNode, distance);
        }
        dijkstra(graph, startNode);
    }

    //무한을 의미하는 상수 INF을 1e9로 초기화한다
    private static final int INF = (int)1e9;
    
    static class Node{

        private int targetNode; // 목표 노드의 번호
        private int distance; // 목표 노드까지의 거리

        Node(int targetNode, int distance) {
            this.distance = distance;
            this.targetNode = targetNode;
        }

        public int getTargetNode() {
            return targetNode;
        }

        public int getDistance() {
            return distance;
        }
    }
    static class Graph{

        //바깥의 ArrayList의 크기는 노드의 개수 + 1 이다
        //예를들면 노드가 1번~6번까지 있으면 ArrayList 크기는 7이 된다
        //안쪽의 LinkedList 에는 해당 노드에 연결된 노드 리스트가 저장된다
        private ArrayList<LinkedList<Node>> list;

        Graph(int kindOfNode) {
            list = new ArrayList<LinkedList<Node>>(kindOfNode + 1);

            for (int i = 0; i < kindOfNode + 1; i++) {
                list.add(new LinkedList<Node>());
            }
        }

        public void add(int nodeNum, int targetNode, int distance) {
            list.get(nodeNum).add(new Node(targetNode, distance));
        }

        public ArrayList<LinkedList<Node>> getList() {
            return list;
        }
    }




    public static void dijkstra(Graph graph,int startNode){
        ArrayList<LinkedList<Node>> list = graph.getList();

        int[] dp = new int[list.size()]; // 최단 경로를 기록할 dp테이블
        boolean[] visited = new boolean[dp.length]; // 방문 여부를 기록할 배열
        
        Arrays.fill(dp, INF);
        Arrays.fill(visited, false);

        // 자기자신까지의 거리는 0이기 때문에, 시작노드의 최단거리는 0으로 설정한다
        dp[startNode] = 0;


        // 최단거리가 가장 짧은 노드 중, 번호가 가장 낮은 노드부터 찾기위해
        // 반복문을 dp테이블이 끝에서 1번 인덱스 방향으로 돌린다
        while(true) {
            int min = INF; // dp테이블에서 가장 낮은 값을 저장할 변수
            int minIndex = INF; // 가장 낮은 값이 저장된 인덱스

            for (int i = dp.length - 1; i >= 1; i--) {
                if (min > dp[i] && (visited[i] == false)) {
                    min = dp[i];
                    minIndex = i;
                }
            }

            if(minIndex == INF) break; // 인덱스가 여전히 INF 값이다 -> 모든 노드를 방문했다는 뜻 -> 탈출

            visited[minIndex] = true; // 최단거리가 가장 짧은 노드 찾았으니, 방문처리를 한다

            LinkedList<Node> nodeList = list.get(minIndex); // 최단거리가 가장 짧은 노드에 대한 인접노드 리스트를 가져온다

            for (int i = 0; i < nodeList.size(); i++) {
                Node node = nodeList.get(i);

                //dp테이블에는 기존 값 vs 자기 자신을 경유한 값을 비교하여 작은 값을 저장한다
                dp[node.getTargetNode()] =
                        Math.min(dp[node.getTargetNode()], dp[minIndex] + node.getDistance());
            }
        }

        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == INF) {
                System.out.println("infinity");
            } else{
                System.out.println(dp[i]);
            }

        }
    }
}
