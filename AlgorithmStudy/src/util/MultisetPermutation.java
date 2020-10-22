package util;

import java.util.LinkedList;

public class MultisetPermutation {
    static int[] arr; // 각 요소의 개수
    static String[] strArr; // 요소의 값
    static int length; // 요소 개수의 총합
    static int[] countArr; // 요소가 몇 번 사용되었는지 기록할 배열
    static LinkedList<Integer> deque; // 요소를 저장할 때 사용할 덱

    public static void main(String[] args) {
        arr = new int[]{3, 2};
        strArr = new String[]{"a", "b"}; // a가 3개 b가 2개 일 때
        countArr = new int[arr.length];


        for (int i = 0; i < arr.length; i++) {
            length += arr[i];
        }

        deque = new LinkedList<>();

        multisetPermutation();
    }

    static void multisetPermutation() {
        for (int i = 0; i < strArr.length; i++) {
            deepCopy(arr, countArr);
            dfs(i);
        }
    }

    static void dfs(int x) {
        deque.add(x);
        countArr[x]--;

        if (length == deque.size() ) {
            for (int a : deque) {
                System.out.print(strArr[a] + " ");
            }
            System.out.println();

            countArr[deque.removeLast()]++;
            return;
        }

        for (int i = 0; i < strArr.length; i++) {
            if (countArr[i] == 0) continue;
            dfs(i);
        }
        countArr[x]++;
        deque.removeLast();
        return;
    }

    static void deepCopy(int[] from, int[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }
}
