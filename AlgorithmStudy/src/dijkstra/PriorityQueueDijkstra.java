package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PriorityQueueDijkstra {
    private static final int INF = (int) 1e9;

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
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            int node = Integer.parseInt(stk2.nextToken());
            int targetNode = Integer.parseInt(stk2.nextToken());
            int distance = Integer.parseInt(stk2.nextToken());

            graph.add(node, targetNode, distance);
        }
        dijkstra(graph, startNode);
    }

    static class Graph {

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

    static class Node {
        private int targetNode;
        private int distance;

        public Node(int targetNode, int distance) {
            this.targetNode = targetNode;
            this.distance = distance;
        }
    }

    static void dijkstra(Graph graph, int startNode) {
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.distance != o2.distance) {
                    return o1.distance - o2.distance;
                } else {
                    return o1.targetNode - o2.targetNode;
                }
            }
        });

        ArrayList<LinkedList<Node>> list = graph.getList();

        int[] dp = new int[list.size()]; // 최단 경로를 기록할 dp테이블
        boolean[] visited = new boolean[dp.length]; // 방문 여부를 기록할 배열

        Arrays.fill(dp, INF);
        Arrays.fill(visited, false);


        dp[startNode] = 0; //시작 노드에서 자기 자신까지의 거리를 0으로 설정한다
        q.add(new Node(startNode, 0)); //시작 노드를 우선순위 큐에 넣는다

        while (!q.isEmpty()) {
            Node removedNode = q.remove();
            int nodeNum = removedNode.targetNode;

            if (visited[nodeNum]) continue;

            visited[nodeNum] = true;

            for (int i = 0; i < list.get(nodeNum).size(); i++) {
                // 해당 노드(큐에서 꺼낸 노드)와 연결된 노드 리스트에서 하나씩 노드를 꺼낸다
                Node listNode = list.get(nodeNum).get(i);

                // listNode를 경유한 거리가 기존의 거리보다 더 짧을 경우
                // dp 테이블을 갱신한 뒤, listNode를 우선순위 큐에 넣는다
                if ((listNode.distance + dp[nodeNum]) < dp[listNode.targetNode]) {
                    dp[listNode.targetNode] = listNode.distance + dp[nodeNum];
                    q.add(listNode);
                }
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
