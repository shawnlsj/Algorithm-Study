package etc.simulation.kakao_2018;

public class Programmers_17681 {
    public String[] solution(int n, int[] arr1, int[] arr2) {

        String[] answer = new String[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            String x =  Integer.toBinaryString(arr1[i] | arr2[i]);

            StringBuilder sb;

            if (x.length() < n){
                sb = new StringBuilder();
                for (int j = 0; j < n - x.length(); j++) {
                    sb.append(" ");

                }
               x = sb.append(x).toString();
            }

            sb = new StringBuilder();
            for (int j = 0; j < x.length(); j++) {
                if (x.charAt(j) == '1') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}
