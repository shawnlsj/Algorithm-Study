package etc.simulation;

import java.util.Queue;

// 124 나라의 숫자
public class Programmers_12899 {
    public static void main(String[] args) {
        System.out.println(new Programmers_12899().solution(4));
    }
    public String solution(int n) {
        String[] arr = new String[]{"4","1","2"};
        String[] arr2 = new String[]{"2", "4", "1"};
        StringBuilder sb = new StringBuilder();

        sb.insert(0, arr[n % 3]);
        double cycle = Math.ceil((double) n / (double) 3);
        while (cycle >= 2) {
            sb.insert(0, arr2[(int) cycle % 3]);
            cycle -= 1;
            cycle = Math.ceil(cycle / (double) 3);
        }
        return sb.toString();
    }
}
