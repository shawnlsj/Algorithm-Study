package sort;

import java.util.ArrayList;
import java.util.Arrays;

public class Programmers_42889 {
    public static void main(String[] args) {
        Programmers_42889 p = new Programmers_42889();
        System.out.println(Arrays.toString(p.solution(4, new int[]{3,1,2,1})));
    }
    public int[] solution(int N, int[] stages) {
        int[] failedUsers = new int[N + 1];
        int[] challengers = new int[N + 1];

        // 오름차순 정렬
        Arrays.sort(stages);

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < stages.length; j++) {
                if (i == stages[j]) {
                    failedUsers[i]++;
                }
                if (i <= stages[j]) {
                    challengers[i]++;
                }
            }
        }
        ArrayList<Rate> rates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            double failureRate;
            if (challengers[i] == 0) {
                failureRate = 0;
            } else {
               failureRate = (double) failedUsers[i] / (double) challengers[i];
            }
            rates.add(new Rate(i, failureRate));
        }


        rates.sort((o1, o2) -> {
            if (o1.failureRate != o2.failureRate) {
                double x = o2.failureRate - o1.failureRate;
                if (x > 0) {
                    return 1;
                } else if (x == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return o1.stageNum - o2.stageNum;
            }
        });
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = rates.get(i).stageNum;
        }
        return answer;
    }
    class Rate{
        int stageNum;
        double failureRate;

        Rate(int stageNum, double failureRate) {
            this.stageNum = stageNum;
            this.failureRate = failureRate;
        }
    }
}
