package sort;

import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TopologicalSort {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        int numNode = Integer.parseInt(stk.nextToken());
        int numEdge = Integer.parseInt(stk.nextToken());

        int[] indegree = new int[numNode + 1];
        Node[] nodeArr = new Node[numNode + 1];

        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i] = new Node(i);
        }

        for (int i = 0; i < numEdge; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine()," ");

            int nodeNum = Integer.parseInt(stk2.nextToken());
            int targetNode = Integer.parseInt(stk2.nextToken());

            nodeArr[nodeNum].add(targetNode);
            indegree[targetNode]++;
        }
        topologicalSort(nodeArr, indegree);
    }

    static class Node{
        LinkedList<Integer> list = new LinkedList<>();
        int nodeNum;

        Node(int nodeNum) {
            this.nodeNum = nodeNum;
        }

        void add(Integer a) {
            list.add(a);
        }
    }

    static void topologicalSort(Node[] nodeArr, int[] indegree) {
        LinkedList<Node> q = new LinkedList<>(); // 노드를 담을 큐
        LinkedList<Integer> answer = new LinkedList<>();// 빼내온 노드를 담을 큐

        boolean[] visited = new boolean[nodeArr.length];
        int cnt = 0; // 큐에 들어갔다 나온 노드의 개수를 카운트할 변수

        Arrays.fill(visited,false); // 방문 여부는 모두 false로 초기화

        while (true) {
            // 진입 차수가 0인 노드를 모드 큐에 넣는다
            for (int i = 1; i < indegree.length; i++) {
                if (indegree[i] == 0 && visited[i] == false) {
                    q.add(nodeArr[i]);
                    visited[i] = true;
                }
            }


            if (q.isEmpty() && cnt < nodeArr.length-1) {
                System.out.println("정렬 수행 불가");
                return;
            }

            Node removedNode = q.remove();
            answer.add(removedNode.nodeNum);
            cnt++;
            if(cnt == nodeArr.length-1) break;

            //꺼낸 노드의 간선을 모두 끊는다
            //따라서 연결되어있던 노드들의 indegree가 1씩 줄어든다
            for (int i = 0; i < removedNode.list.size(); i++) {
                indegree[removedNode.list.get(i)]--;
            }
        }//while

        //remove()을 호출할 때마다 size()의 값이 변하기 때문에
        // i를 빼주는 식으로 해서 출력한다
        for (int i = answer.size(); i > 0 ; i--) {
            System.out.print(answer.remove()+" ");
        }
        return;
    }
}
