package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ex_3665 {
    static int numTestCase; // tc의 개수
    static int n; // n 개의 팀이 있다
    static int m; // m 줄에 걸쳐 순위 정보가 주어진다
    static int[] indegree; // 노드의 진입 차수를 기록할 배열
    static int[][] outdegree;
    static boolean[] visited;
    static ArrayList<Node> nodes;
    static Node[] nodeArr;
    static Queue<Node> q;
    static Stack<Node> stack;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numTestCase = Integer.parseInt(br.readLine());

        //테스트 케이스 시작
        for (int i = 0; i < numTestCase; i++) {
            // -- 매 TC 마다 초기화 한다 --
            nodes = new ArrayList<>();
            nodeArr = new Node[500 + 1];
            indegree = new int[500 + 1];
            outdegree = new int[500 + 1][500 + 1];
            visited = new boolean[500 + 1];
            q = new LinkedList<>();
            stack = new Stack<>();
            cnt = 0;

            n = Integer.parseInt(br.readLine()); // 팀의 개수를 입력 받는다
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < n; j++) {
                Node node = new Node(Integer.parseInt(stk.nextToken()));

                //노드 리스트에 있는 노드 전부를 새 노드가 가리키게 만든다
                for (int k = 0; k < nodes.size(); k++) {
                    node.add(nodes.get(k).nodeNum);
                    indegree[nodes.get(k).nodeNum]++; //새 노드가 가리키는 노드의 진입 차수를 1 올린다
                }
                nodes.add(node);
                nodeArr[node.nodeNum] = node;
            }

            m = Integer.parseInt(br.readLine());
            for (int j = 0; j < m; j++) {
                stk = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                Node nodeA = nodeArr[a];
                Node nodeB = nodeArr[b];

                // a->b 인지
                // b->a 인지 알아내야 한다
                if (outdegree[a][b] == 1) {
                    outdegree[a][b] = 0;
                    outdegree[b][a] = 1;
                    indegree[b]--;
                    indegree[a]++;
                } else {
                    outdegree[b][a] = 0;
                    outdegree[a][b] = 1;
                    indegree[a]--;
                    indegree[b]++;
                }
            }
            topologicalSort();

            System.out.println();
        }
    }
    static void topologicalSort() {

        while(true) {
            //진입 차수가 0인 노드 찾아서 큐에 넣는다
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                if (indegree[node.nodeNum] == 0 && visited[node.nodeNum] == false) {
                    q.add(node);
                    visited[node.nodeNum] = true;
                    cnt++;
                }
            }

            // 큐에 들어있는 노드의 개수가 2개 이상이면 순위를 알 수 없으므로 ?를 출력한다
            if (q.size() >= 2) {
                System.out.print("?");
                return;
            }

            if (q.isEmpty()) {
                if (cnt == n) {
                    while (!stack.isEmpty()) {
                        System.out.print(stack.pop().nodeNum + " ");
                    }
                } else {
                    System.out.print("IMPOSSIBLE");
                }
                return;
            }

            //큐에서 노드를 하나 꺼내고, 간선을 모두 끊는다
            Node node = q.poll();
            stack.push(node);
            for (int i = 1; i < outdegree.length; i++) {
                if (outdegree[node.nodeNum][i] == 1) {
                    outdegree[node.nodeNum][i] = 0;
                    indegree[i]--;
                }
            }
        }
    }

    static class Node {
        int nodeNum;
        Node(int nodeNum) {
            this.nodeNum = nodeNum;
        }
        void add(int nodeNum) {
            outdegree[this.nodeNum][nodeNum] = 1;
        }
    }

}
