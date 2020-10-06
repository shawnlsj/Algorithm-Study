package etc.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 기능개발
public class Programmers_42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int day = 1;

        Queue<Work> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            q.add(new Work(progresses[i], speeds[i]));
        }

        int num = 0;
        while (!q.isEmpty()) {
            Work work = q.peek();
            if (work.speed * day + work.progress >= 100) {
                q.poll();
                num++;
                if(!q.isEmpty()) continue;
            }
            if (num > 0) list.add(num);
            num = 0;
            day++;
        }

        // 리스트를 배열에 옮겨담기
        answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    static class Work {
        int progress;
        int speed;

        Work(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }
}
