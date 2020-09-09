package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ch7_2_CountingSort {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //아이템의 종류와 각 아이템의 번호를 입력 받는다
        int kindOfItem = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");


        int[] countArr = new int[1_000_001];  //카운팅할 배열을 선언
        int[] itemNumArr = new int[kindOfItem]; //아이템 마다 붙어있는 번호를 저장할 배열 선언

        //카운팅 배열의 아이템 번호 인덱스에 1을 대입, 아이템 번호 배열에는 아이템 번호를 저장
        for (int i = 0; i < kindOfItem; i++) {
            int tmp = Integer.parseInt(stk.nextToken());
            countArr[tmp] = 1;
            itemNumArr[i] = tmp;
        }

        //손님이 요구한 아이템 번호의 종류와 각 아이템의 번호를 입력받는다.
        int kindOfRequest = Integer.parseInt(br.readLine());
        StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
        
        //요구한 아이템 번호를 저장할 배열 선언
        int[] requestNumArr = new int[kindOfRequest];

        //아이템 번호 저장
        for (int i = 0; i < kindOfRequest; i++) {
            requestNumArr[i] = Integer.parseInt(stk2.nextToken());
        }

        for (int i = 0; i < kindOfRequest; i++) {
           if( countArr[requestNumArr[i]] == 1){
               System.out.print("yes");
           } else{
               System.out.print("no");
           }
           if( i < kindOfRequest-1){
               System.out.print(" ");
           }
        }




    }
}
