package binary_serach;

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
            long middle = (start + end) / 2;
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
