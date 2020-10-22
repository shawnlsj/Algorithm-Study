package etc.simulation;

import java.util.LinkedList;
import java.util.Queue;

// 프린터
public class Programmers_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int[] numPriority = new int[10];

        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            numPriority[priorities[i]]++;
            if (i == location) {
                q.add(new Node(priorities[i], true));
                continue;
            }
            q.add(new Node(priorities[i]));
        }
        outer:
        while (true) {
            Node node = q.peek();
            for (int i = node.priority + 1; i < numPriority.length; i++) {
                if (numPriority[i] >= 1) {
                    q.add(q.poll());
                    continue outer;
                }
            }
            node = q.poll();
            answer++;
            if (node.target) {
                break;
            }
            numPriority[node.priority]--;
        }
        return answer;
    }

    class Node{
        int priority;
        boolean target = false;

        Node(int priority) {
            this.priority = priority;
        }
        Node(int priority, boolean target) {
            this.priority = priority;
            this.target = target;
        }
    }
}
