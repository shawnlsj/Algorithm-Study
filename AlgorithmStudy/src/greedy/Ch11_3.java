package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ch11_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] arr = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i) - '0';
        }

        int zeroCnt = 0;
        int oneCnt = 0;
        int result = 0;
        //0을 뒤집었을 경우

        boolean check = false;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0 && check==false){
                System.out.println("i"+i);
                zeroCnt++;
                check = true;
            } else if(arr[i]==1) {
                check = false;
            }
        }

        check = false;

        //1을 뒤집었을 경우
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==1 && check==false){
                oneCnt++;
                check = true;
            } else if(arr[i]==0) {
                check = false;
            }
        }

        result = zeroCnt<oneCnt?zeroCnt:oneCnt;
        System.out.println(result);
    }
}
