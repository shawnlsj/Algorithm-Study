package etc.simulation;

// 124 나라의 숫자
public class Programmers_12899 {
    public String solution(int n) {
        String[] arr = new String[]{"4","1","2"};
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % 4;
            sb.insert(0, arr[remainder]);
            n = n / 4;
        }
        return sb.toString();
    }
}
