package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ch4_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int n2 = (n + 1) * 3600 - 1;

		int answer = 0;

		while (true) {
			if (n2 % 3 == 0) {
				answer = n2/3;
				break;
			} else {	
				n2 -=1;
			}
		}
		
		System.out.println(answer);

	}
}
