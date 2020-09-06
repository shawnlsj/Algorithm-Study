package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ch7_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        //n : 떡의 개수
        //m : 요구한 떡의 길이
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] dduckArr = new int[n];
        int max = 0;

        StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            int length = Integer.parseInt(stk2.nextToken());
            if (max < length) max = length;
            dduckArr[i] = length;
        }


        int start = 0;
        int end = max;
        int middle = 0;

        while (true) {
            if(end == start) break; // 절단기의 범위는 0 에서 max 까지인데 범위가 자기자신 뿐이면 탈출
            middle = (end-start)/2 + start;
            int sum = 0;

            for (int i = 0; i < n; i++) {
                if (dduckArr[i] > middle) {
                    sum += dduckArr[i] - middle;
                }
            }

            if (sum > m) {
                start = middle+1; // 요구한 길이보다 많이 잘렸으므로 절단기를 높인다

            } else if (sum < m) {
                end = middle-1; //요구한 길이보다 덜 잘렸으므로 절단기를 낮춘다
            } else {
                break;
            }


        }

        System.out.println(middle);


    }
}
