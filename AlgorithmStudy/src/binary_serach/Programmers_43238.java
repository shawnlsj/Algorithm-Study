package binary_serach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// 입국 심사
public class Programmers_43238 {
    public static void main(String[] args) {
        Programmers_43238 p = new Programmers_43238();
        System.out.println(p.solution(7, new int[]{7, 10}));
    }
    public long solution(int n, int[] times) {
        long start = 1;
        long end = (long)times[0] * (long)n;

        while (true) {
            if (start == end) break;
            long middle;
            if (start % 2 == 1 && end % 2 == 1) {
                middle = start / 2 + end / 2 + 1;
            } else {
                middle = start / 2 + end / 2;
            }

            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += middle / times[i];
            }
            if (sum >= n) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return start;
    }
}
