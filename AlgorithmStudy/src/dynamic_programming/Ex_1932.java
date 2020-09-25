package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ex_1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> floors = new ArrayList<>(n);
        // 1층은 이전 층이 없으므로 반복문 전에 미리 초기화한다
        floors.add(new ArrayList<>());
        floors.get(0).add(Integer.parseInt(br.readLine()));
        
        // 2층부터 시작
        for (int i = 1; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            //새로운 층을 추가한다
            floors.add(new ArrayList<>());

            //i번째 층에 요소들을 추가한다
            for (int j = 0; j <= i; j++) {
                int result = -1;
                int x = Integer.parseInt(stk.nextToken());
                // 왼쪽 부모와 오른쪽 부모가 있는지 확인하고 더 큰 부모와 병합한다
                // 왼쪽 부모가 있는지 확인하는 방법 : 인덱스에서 -1 해서 0 이상이 나오면 있음
                // 오른쪽 부모가 있는지 확인하는 방법 : 현재 인덱스 <= 이전 층의 마지막 인덱스
                if (j - 1 >= 0) {
                    result = floors.get(i-1).get(j-1) + x;
                }
                if (j <= floors.get(i-1).size() - 1) {
                    result = Math.max(result, floors.get(i - 1).get(j) + x);
                }
                floors.get(i).add(result);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(floors.get(i));
        }
        //마지막 층을 오름차순 정렬한다
        Collections.sort(floors.get(n-1));
        
        //마지막 층의 가장 마지막 인덱스 요소가 최댓값이다
        int index = floors.get(n - 1).size() - 1;

        System.out.println(floors.get(n-1).get(index));
    }
}
