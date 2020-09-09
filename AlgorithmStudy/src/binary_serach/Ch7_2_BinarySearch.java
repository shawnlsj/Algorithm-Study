package binary_serach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ch7_2_BinarySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //아이템의 종류와 각 아이템의 번호를 입력 받는다
        int kindOfItem = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        

        int[] itemNumArr = new int[kindOfItem]; //아이템 마다 붙어있는 번호를 저장할 배열 선언
        //아이템 번호 저장
        for (int i = 0; i < kindOfItem; i++) {
            itemNumArr[i] = Integer.parseInt(stk.nextToken());
        }

        //손님이 요구한 아이템 번호의 종류와 각 아이템의 번호를 입력받는다.
        int kindOfRequest = Integer.parseInt(br.readLine());
        StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
        

        int[] requestNumArr = new int[kindOfRequest]; //요구한 아이템 번호를 저장할 배열 선언
        //아이템 번호 저장
        for (int i = 0; i < kindOfRequest; i++) {
            requestNumArr[i] = Integer.parseInt(stk2.nextToken());
        }

        Arrays.sort(itemNumArr);

        for(int i=0; i<kindOfRequest; i++){
            if (binarySearch(itemNumArr, requestNumArr[i]) == true) {
                System.out.print("yes");
            } else {
                System.out.print("no");
            }
            //마지막 항에서는 공백을 붙이지 않게 한다
            if (i < kindOfRequest - 1) {
                System.out.print(" ");
            }
        }

    }

    public static boolean binarySearch(int[] itemNumArr, int target){
        int start = 0;
        int end = itemNumArr.length-1;

        while(start<=end){
            int middle = (end-start)/2 + start;
            //타겟이 middle의 왼쪽에 있는 경우
            if(itemNumArr[middle] > target){
                end = middle-1;
            //타겟을 찾았으므로 true 리턴   
            } else if(itemNumArr[middle] == target){
                return true;
            } else {
                // 타겟이 middle의 오른쪽에 있는 경우
                start = middle+1;
            }
        }
        // 타겟이 배열에 존재하지 않으므로 false 리턴
        return  false;
    }

}
