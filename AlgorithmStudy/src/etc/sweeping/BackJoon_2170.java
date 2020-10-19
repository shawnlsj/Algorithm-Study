package etc.sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BackJoon_2170 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            lines.add(new Line(start, end));
        }
        lines.sort((o1, o2) ->{
            if (o1.start != o2.start) {
                return o1.start - o2.start;
            } else {
                return o1.end - o2.end;
            }
        });

        int answer = 0;

        int start = lines.get(0).start;
        int end = lines.get(0).end;
        int distance = lines.get(0).distance;
        answer = lines.get(0).distance;

        for (int i = 1; i < lines.size(); i++) {
            Line now = lines.get(i);
            if (end > now.start) {
                if (end > now.end) {
                    continue;
                } else {
                    answer -= distance;
                    distance = Math.abs(start - now.end);
                    answer += distance;
                }
            } else {
                distance = now.distance;
                answer += distance;
                start = now.start;
            }
            end = now.end;
        }
        System.out.println(answer);
    }

    static class Line {
        int start;
        int end;
        int distance;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
            distance = end - start;
        }
    }
}
