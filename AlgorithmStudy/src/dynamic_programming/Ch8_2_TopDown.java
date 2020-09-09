package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//재귀호출을 이용하고, 함수 호출마다 새로운 배열 생성하기 때문에 메모리 소비가 심하다
public class Ch8_2_TopDown {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        // x의 최적해는 cache[x]에 저장할 것이기 때문에
        // 배열의 크기를 x+1 로 생성
        int[] cache = new int[x+1];

        System.out.println(dp(x,cache));


    }

    public static int dp(int x, int[] cache){
        if(x==1) return 0;
        
        // x에 대한 최적해를 저장할 배열 선언
        // 최적해는 무조건 0이상 이기 때문에 먼저 -1로 초기화를 해놓는다
        int[] arr = {-1,-1,-1,-1};

        // 최소값을 저장할 min을 -1로 초기화 (무조건 0이상이 저장될 예정이니 0으로 초기화 해도 됨)
        int min = -1;


        // x-1은 어떠한 수가 와도 -1을 할 수 있기 때문에 먼저 수행한다
        if(cache[x-1] == 0){
            cache[x-1] = dp(x-1, cache);
        }
        arr[0] = cache[x-1];

        // 5,3,2로 나누어서 떨어질 경우에는
        // cache 배열에 저장된 값이 없으면 새로 dp()을 호출하고 
        // 값이 있으면 arr 배열에 값을 저장한다
        if (x % 5 == 0) {
            if (cache[x / 5] == 0) {
                cache[x / 5] = dp(x/5, cache);
            }
            arr[1] = cache[x/5];
        }
        if (x % 3 == 0) {
            if (cache[x / 3] == 0) {
                cache[x / 3] = dp(x/3, cache);
            }
            arr[2] = cache[x/3];
        }
        if (x % 2 == 0) {
            if (cache[x / 2] == 0) {
                cache[x / 2] = dp(x/2, cache);
            }
            arr[3] = cache[x/2];
        }

        // for문을 돌면서 arr 요소 값이 -1이 아닌 경우에만 (x가 나누어 떨어지는 조건이 맞아서 -1이 아닌 값이 대입 된 경우)
        // min과 값을 비교 후 , 작은 값을 min에 저장한다
        // 처음으로 들어오는 값은 무조건 min에 저장되어야 하기 때문에, 처음인지 체크할 변수 first 선언

        for(int i=0; i<arr.length; i++){
            if(arr[i]>-1){
                if(i==0){
                    min = arr[i];
                    continue;
                }
                if(arr[i]<min){
                    min = arr[i];
                }
            }
        }
        
        // 연산이 1번더 진행 된 것이므로 1을 더해준다
        return min + 1;

    }
}
