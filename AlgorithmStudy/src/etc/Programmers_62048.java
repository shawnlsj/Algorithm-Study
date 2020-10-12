package etc;

import java.util.Arrays;

public class Programmers_62048 {

    public static void main(String[] args) {
        Programmers_62048 p = new Programmers_62048();
        System.out.println(p.solution(100000000,100000000));
    }
    public long solution(int w, int h) {
        int gcd = 1;
        int width = w;
        int height = h;

        boolean[] primeNum = new boolean[(w > h ? h : w) + 1];
        long wh = (long)w * (long)h;
        int len = w > h ? h : w;
        Arrays.fill(primeNum, true);

        for (int i = 2; i <= Math.sqrt(primeNum.length - 1); i++) {
            if (primeNum.length % i == 0) {
                for (int j = i; j * i <= primeNum.length - 1; j++) {
                    primeNum[j * i] = false;
                }
            }
        }

        double start = System.currentTimeMillis();
        for (int i = 2; i <= len; i++) {
            if (!primeNum[i]) continue;
            while (w % i == 0 && h % i == 0) {
                gcd *= i;
                w /= i;
                h /= i;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

        System.out.println("wh = " + wh);
        System.out.println("gcd = " + gcd);
        return wh - (width + height - gcd);
    }
}
