package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ex_14888 {

    static int n;
    static int[] arr; // 입력 받은 숫자를 저장할 배열
    static int[] copyArr; // arr의 복사본
    static int[] operationCnt = new int[4]; //연산자의 개수를 카운트할 배열
    static int max = -1_000_000_001; // -10억 - 1
    static int min = 1_000_000_001;  //10억 + 1
    static ArrayList<Integer> list; // 중복 순열을 담을 리스트

    public static void main(String[] args) throws Exception {
        //-- 입력부 --
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 숫자 개수를 입력 받는다

        // 입력받은 숫자를 배열에 저장
        arr = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        // 연산자 개수를 입력 받는다
        StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < operationCnt.length; i++) {
            operationCnt[i] = Integer.parseInt(stk2.nextToken());
        }
        //-- 입력부 끝 --

        cal();

        System.out.println(max);
        System.out.println(min);
    }

    static void cal() {
        MultisetPermutation.arr = operationCnt;
        MultisetPermutation.countArr = new int[operationCnt.length];
        for (int i = 0; i < operationCnt.length; i++) {
            MultisetPermutation.length += operationCnt[i];
        }
        MultisetPermutation.kind = operationCnt.length;
        copyArr = new int[arr.length];

        list = MultisetPermutation.multisetPermutation();

        for (int i = 0; i < list.size(); i += MultisetPermutation.length) {
            //하나의 순열에 대해 실행하는 for문

            for (int m = 0; m < arr.length; m++) {
                copyArr[m] = arr[m];
            }
            //    copyArr = Arrays.copyOf(arr, arr.length);

            for (int j = i, k = 0; j < MultisetPermutation.length + i; j++, k++) {
                switch (list.get(j)) {
                    case 0:
                        copyArr[k + 1] = copyArr[k] + copyArr[k + 1];
                        break;
                    case 1:
                        copyArr[k + 1] = copyArr[k] - copyArr[k + 1];
                        break;
                    case 2:
                        copyArr[k + 1] = copyArr[k] * copyArr[k + 1];
                        break;
                    case 3:
                        copyArr[k + 1] = copyArr[k] / copyArr[k + 1];
                        break;
                }
            }
            int result = copyArr[copyArr.length - 1];
            max = Math.max(max, result);
            min = Math.min(min, result);
        }


    }

    static class MultisetPermutation {
        static int[] arr;
        static int[] countArr;

        static int kind; // 순열에 들어가는 문자의 종류
        static int length; // 순열의 길이
        static int depth = 0;

        static LinkedList<Integer> deque = new LinkedList<>();
        static ArrayList<Integer> list = new ArrayList<>();

        static ArrayList<Integer> multisetPermutation() {

            for (int i = 0; i < kind; i++) {
                deepCopy(arr, countArr);
                if (countArr[i] == 0) continue;

                dfs(i);
            }

            return list;
        }

        static void dfs(int x) {
            depth++;
            deque.add(x);
            countArr[x]--;

            if (length == depth) {
                for (int a : deque) {
                    list.add(a);
                }
                depth--;
                countArr[deque.removeLast()]++;
                return;
            }

            for (int i = 0; i < kind; i++) {
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

}

