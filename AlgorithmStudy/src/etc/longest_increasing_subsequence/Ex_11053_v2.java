package etc.longest_increasing_subsequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//O(N*logN) 알고리즘
public class Ex_11053_v2 {
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

        for (int i = 1; i < n; i++) {
            System.out.println(list);
            if (arr[i] > list.get(list.size() - 1)) {
                list.add(arr[i]);
            } else {
                System.out.println("getLowerBound(arr[i]) = " + getLowerBound(arr[i]));
                list.set(getLowerBound(arr[i]), arr[i]);
            }
            System.out.println("----");
        }
        System.out.println(list.size());
    }

    //순차 탐색으로 찾을 경우 O(N)
    //이진 탐색으로 찾을 경우 O(log N)
    static int getLowerBound(int a) {
        int start = 0;
        int end = list.size() - 1;
        int middle = 0;

        while (start < end) {

            middle = (start + end) / 2;

            if (list.get(middle) < a) {
                start = middle + 1;
            } else if (list.get(middle) == a) {
                return middle;
            } else {
                end = middle;
            }
        }
        if (list.get(start) >= a) {
            return start;
        } else {
            return start + 1;
        }
    }
}
