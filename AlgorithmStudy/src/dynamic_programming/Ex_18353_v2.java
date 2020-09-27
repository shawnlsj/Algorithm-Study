package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex_18353_v2 {
    static int n;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        list.add(arr[0]);

        for (int i = 0; i < n; i++) {
            if (list.get(list.size() - 1) > arr[i]) {
                list.add(arr[i]);
            } else {
                list.set(getUpperBound(arr[i]), arr[i]);
            }
        }

        System.out.println(n - list.size());
    }

    static int getUpperBound(int x) {
        int start = 0;
        int end = list.size() - 1;
        int middle = 0;
        while (start < end) {
            middle = (start + end) / 2;
            if (list.get(middle) > x) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        if (list.get(start) > x) {
            return start + 1;
        } else {
            return start;
        }
    }
}
