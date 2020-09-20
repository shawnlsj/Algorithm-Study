package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ch12_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int left = 0;
        int right = 0;

        for (int i = 0; i < s.length()/2; i++) {
            left += s.charAt(i)-'0';
        }

        for (int i = s.length() / 2; i < s.length(); i++) {
            right += s.charAt(i)-'0';
        }

        if (left == right) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }

    }
}
