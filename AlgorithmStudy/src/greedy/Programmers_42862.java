package greedy;

import java.util.Arrays;

//체육복
public class Programmers_42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[] arr = new int[n + 1];

        Arrays.fill(arr, 1);

        for (int i = 0; i < lost.length; i++) {
            arr[lost[i]] -= 1;
        }
        for (int i = 0; i < reserve.length; i++) {
            arr[reserve[i]] += 1;
        }

        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];
            if (x == 0) {
                if (i - 1 >= 1 && arr[i - 1] == 2) {
                    arr[i - 1]--;
                    arr[i] = 1;
                }
               else if (i + 1 <= n && arr[i + 1] == 2) {
                    arr[i + 1]--;
                    arr[i] = 1;
                }
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= 1) {
                answer++;
            }
        }
        return answer;
    }
}
