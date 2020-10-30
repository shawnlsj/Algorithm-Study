package binary_serach;

// 징검다리 건너기
public class Programmers_64062 {
    public static void main(String[] args) {
        Programmers_64062 p = new Programmers_64062();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(p.solution(stones, k));
    }
    public long solution(int[] stones, int k) {
        long start = 0;
        long end = Long.MAX_VALUE;

        while (true) {
            if (start == end) break;

            long middle = (start + end) / 2 + (start + end) % 2;
            long[] copyArr = new long[stones.length];
            for (int i = 0; i < copyArr.length; i++) {
                copyArr[i] = stones[i];
            }
            if (checkPossible(copyArr, k, middle)) {
                start = middle;
            } else {
                end = middle - 1;
            }
        }
        return start;
    }

    boolean checkPossible(long[] stones, int k, long numFreinds) {

        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            stones[i] -= numFreinds - 1;
            if (stones[i] <= 0) {
                cnt++;
            } else if (stones[i] >= 1 && cnt >= 1) {
                cnt = 0;
            }
            if (cnt == k) {
                return false;
            }
        }
        return true;
    }
}
