package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 처음 버전에서는 boolean[]을 이용하여 방문여부를 체크한 뒤에
// 반복문의 처음에서 1번 노드부터 마지막노드까지 방문여부와 진입 차수를 전부 체크하였지만
// 이제는 큐에서 꺼낸 노드에 대해 노드가 가진 인접 노드 리스트만 체크한다

public class TopologicalSort2 {
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

        int cnt = 0; // 큐에 들어갔다 나온 노드의 개수를 카운트할 변수

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0 ) {
                q.add(nodeArr[i]);
            }
        }

        while (true) {
            if (q.isEmpty() && cnt < nodeArr.length - 1) {
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
                if (indegree[removedNode.list.get(i)] == 0) {
                    q.add(nodeArr[removedNode.list.get(i)]);
                }
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
