package kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch10_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] parentArr = new int[n+1];

        for (int i = 0; i < parentArr.length; i++) {
            parentArr[i] = i;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            int type = Integer.parseInt(stk2.nextToken());
            int a = Integer.parseInt(stk2.nextToken());
            int b = Integer.parseInt(stk2.nextToken());

            //0이면 팀 합치기
            //1이면 같은 팀인지 확인
            if (type == 0) {
            union(parentArr,a ,b);
            } else {
            check(parentArr,a,b);
            }

        }


    }

    static void check(int[] parentArr, int a, int b) {
        if (findParent(parentArr, a) == findParent(parentArr, b)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void union(int[] parentArr, int a, int b) {

        if (findParent(parentArr, a) < findParent(parentArr, b)) {
            parentArr[b] = parentArr[a];
        } else {
            parentArr[a] = parentArr[b];
        }

    }

    static int findParent(int[] parentArr, int nodeNum) {
        if (parentArr[nodeNum] == nodeNum) {
            return nodeNum;
        } else {
            return parentArr[nodeNum] = findParent(parentArr, parentArr[nodeNum]);
        }
    }
}
