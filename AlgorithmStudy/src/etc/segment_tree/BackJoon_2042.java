package etc.segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BackJoon_2042 {
    static long[] segmentTree;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        arr = new long[n];
        segmentTree = new long[4 * arr.length];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(0, arr.length - 1, 1);
        System.out.println(Arrays.toString(segmentTree));


        for (int i = 0; i < m + k; i++) {
            stk = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            if (a == 1) {
                update(0, arr.length - 1, 1, b - 1, c - arr[b - 1]);
                arr[b - 1] = c;
            } else {
                System.out.println(query(b - 1, c - 1, 1, 0, arr.length - 1));
            }
        }
    }

    static long init(int start, int end, int index) {
        if (end == start) {
            return segmentTree[index] = arr[start];
        }
        int middle = (start + end) / 2;
        return segmentTree[index] = init(start, middle, index * 2) + init(middle + 1, end, index * 2 + 1);
    }

    static void update(int left, int right, int nodeNum, int index, long dif) {
        segmentTree[nodeNum] += dif;
        if (left == right) return;
        int middle = (left + right) / 2;
        if (index <= middle) {
            update(left, middle, nodeNum * 2, index, dif);
        } else {
            update(middle + 1, right,nodeNum * 2 + 1, index, dif);
        }
    }

    static long query(int start, int end, int index, int left, int right) {
        if (right < start || left > end) return 0;
        if (left >= start && right <= end) return segmentTree[index];
        int middle = (left + right) / 2;
        return query(start, end, index * 2, left, middle) + query(start, end, index * 2 + 1, middle + 1, right);
    }
}
