package sort;

import java.util.PriorityQueue;

public class Programmers_42626 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> q = new PriorityQueue<>();
        for (long a : scoville) {
            q.add(a);
        }
        if (q.peek() >= K) return 0;

        while (q.size() >= 2) {
            answer++;
            long newScoville = q.poll() + q.poll() * 2;
            q.add(newScoville);
            if (q.peek() >= K) {
                return answer;
            }
        }
        return -1;
    }
}
