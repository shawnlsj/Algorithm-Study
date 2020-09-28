package greedy;

import com.sun.media.jfxmedia.control.MediaPlayerOverlay;

import javax.lang.model.SourceVersion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex_1783 {
    static int n;
    static int m;
    static int row = 1;
    static int col = 1;
    static final String UUR = "UUR";
    static final String URR = "URR";
    static final String DDR = "DDR";
    static final String DRR = "DRR";
    static int cnt = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        while (cnt < 4 && col < m) {
            if (move(UUR)) {
                continue;
            }
            if (move(DDR)) {
                continue;
            }
            if (move(URR)) {
                continue;
            }
            if (move(DRR)) {
                continue;
            }
        }
        while (col < m) {
            if (move(DDR)) {
                move(UUR);
                move(DRR);
                move(URR);
            } else if (move(UUR)) {
                move(DDR);
                move(URR);
                move(DRR);
            }
        }

        System.out.println(cnt);

    }

    static boolean move(String command) {
        int afterRow = row;
        int afterCol = col;
        switch (command) {
            case UUR:
                afterRow += 2;
                afterCol += 1;
                break;
            case URR:
                afterRow += 1;
                afterCol += 2;
                break;
            case DDR:
                afterRow -= 2;
                afterCol += 1;
                break;
            case DRR:
                afterRow -= 1;
                afterCol += 2;
                break;
        }
        if (afterRow <= n && afterCol <= m && afterRow >= 1 && afterCol >= 1) {
            row = afterRow;
            col = afterCol;
            cnt++;
            return true;
        } else {
            return false;
        }
    }
}
