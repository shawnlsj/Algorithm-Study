package etc.hanoi_tower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex_1914 {
    static int count;
    static boolean isTwenty;
    static int depth;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n <= 20) isTwenty = true;

        double start = System.currentTimeMillis();
        hanoi2(n, 1, 2, 3);
        System.out.println("hanoi2 :" + (System.currentTimeMillis() - start));

        System.out.println(count);

        double start2 = System.currentTimeMillis();
        hanoi(n, 1, 2, 3);
        System.out.println("hanoi2 :" + (System.currentTimeMillis() - start2));

    }

    static void hanoi(int n, int a, int b, int c) {
        depth++;
        if (n == 0) {
            depth--;
            return;
        }
        hanoi(n - 1, a, c, b);
        if (isTwenty) {
            System.out.print(a + " " + c);
            System.out.println(" depth = " + depth);
        }
        if(depth==1) return;
        hanoi(n - 1, b, a, c);
        depth--;
    }

    static void hanoi2(int n, int a, int b, int c) {
        if (n == 0) return;
        hanoi2(n - 1, a, c, b);
        count++;
        hanoi2(n - 1, b, a, c);
    }
}
