package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex_2110 {
    static int n; //집의 개수
    static int c; //공유기 개수
    static int[] arr = new int[200000]; //집 위치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            arr[x]++;
        }
    }
}
