package util;

import java.util.LinkedList;

public class MultisetPermutation {
    static int[] arr;
    static String[] strArr;
    static int depth;
    static int length;
    static int[] countArr;

    static LinkedList<Integer> deque;

    public static void main(String[] args) {
        arr = new int[]{3, 2};
        countArr = new int[arr.length];
        strArr = new String[]{"a", "b"};

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
        depth++;
        deque.add(x);
        countArr[x]--;

        if (length == depth) {
            for (int a : deque) {
                System.out.print(strArr[a] + " ");
            }
            System.out.println();

            depth--;
            countArr[deque.removeLast()]++;
            return;
        }

        for (int i = 0; i < strArr.length; i++) {
            if (countArr[i] == 0) continue;
            dfs(i);
        }

        depth--;
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
