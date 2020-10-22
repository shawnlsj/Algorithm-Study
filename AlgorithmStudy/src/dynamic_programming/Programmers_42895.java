package dynamic_programming;

import java.util.*;

public class Programmers_42895 {
    public static void main(String[] args) {
        Programmers_42895 p = new Programmers_42895();
        System.out.println(p.solution(5, 12));
    }

    public int solution(int N, int number) {
        int answer = -1;
        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();

        if (N == number) {
            return 1;
        }
        for (int i = 0; i <= 8; i++) {
            dp.add(new ArrayList<>());
            if (i == 1) dp.get(i).add(N);
            if (i >= 2) {
                dp.get(i).add(dp.get(i - 1).get(0) * 10 + N);
            }
        }

        for (int i = 2; i <= 8; i++) {
            ArrayList<Integer> after = dp.get(i);
            if (after.get(0) == number) {
                return i;
            }
            int a = 1;
            int b = i - a;
            while (a <= b) {
                for (int m = 0; m < dp.get(a).size(); m++) {
                    for (int n = 0; n < dp.get(b).size(); n++) {
                        int left = dp.get(a).get(m);
                        int right = dp.get(b).get(n);

                        if (left + right == number ||
                                left - right == number ||
                                right - left == number ||
                                left * right == number) {
                            return i;
                        }
                        after.add(left + right);
                        after.add(left - right);
                        after.add(right - left);
                        after.add(left * right);
                        if (right != 0) {
                            if (left / right == number) {
                                return i;
                            }
                            after.add(left / right);
                        }
                        if (left != 0) {
                            if (right / left == number) {
                                return i;
                            }
                            after.add(right / left);
                        }
                    }
                }
                a++;
                b--;
            }
        }
        return answer;
    }
}
