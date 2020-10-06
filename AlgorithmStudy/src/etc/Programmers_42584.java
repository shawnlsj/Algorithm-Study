package etc;

//주식가격
public class Programmers_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            int period = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] >= prices[i]) {
                    period++;
                } else {
                    period++;
                    break;
                }
            }
            answer[i] = period;
        }
        answer[answer.length - 1] = 0;
        return answer;
    }
}
