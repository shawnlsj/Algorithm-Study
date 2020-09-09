package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Ch7_2_Set {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //아이템의 종류와 각 아이템의 번호를 입력 받는다
        int kindOfItem = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        HashSet set = new HashSet(); // 아이템을 저장할 Set 선언
        //아이템 번호 저장
        for (int i = 0; i < kindOfItem; i++) {
            set.add(Integer.parseInt(stk.nextToken()));
        }

        //손님이 요구한 아이템 번호의 종류와 각 아이템의 번호를 입력받는다.
        int kindOfRequest = Integer.parseInt(br.readLine());
        StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
        

        int[] requestNumArr = new int[kindOfRequest];      //요구한 아이템 번호를 저장할 배열 선언
        //아이템 번호 저장
        for (int i = 0; i < kindOfRequest; i++) {
            int beforeSize = set.size();
            set.add(Integer.parseInt(stk2.nextToken()));
            int afterSize = set.size();

            if(beforeSize == afterSize){
                System.out.print("yes");
            } else {
                System.out.print("no");
            }
            if (i < kindOfRequest - 1) {
                System.out.print(" ");
            }
        }



        
        

    }
}
