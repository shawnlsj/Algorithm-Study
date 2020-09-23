package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ex_18310 {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>(n);

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(stk.nextToken());
            list.add(x);
        }
        Collections.sort(list);
        System.out.println(list.get((list.size()-1)/2));
    }
}
