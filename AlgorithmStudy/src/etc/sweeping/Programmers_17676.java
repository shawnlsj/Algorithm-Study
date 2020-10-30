package etc.sweeping;

import etc.simulation.Programmers_64064;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers_17676 {
    public static void main(String[] args) {
        Programmers_17676 p = new Programmers_17676();
        System.out.println(p.solution(
                new String[]{
                        "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" }
        ));
    }
    public int solution(String[] lines) {
        int answer = 0;
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            int start = 0;
            int end = 0;

            Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(lines[i]);
            StringTokenizer stk;

            //시 : 분 : 초 를 밀리초로 저장
            if (matcher.find()) {
                stk = new StringTokenizer(matcher.group(), ":");
                end += Integer.parseInt(stk.nextToken()) * 60 * 60 * 1000;
                end += Integer.parseInt(stk.nextToken()) * 60 * 1000;
                end += Integer.parseInt(stk.nextToken()) * 1000;
            }

            //밀리초를 더해주기
            pattern = Pattern.compile("(\\.)(\\d{0,3})(\\s)");
            matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                end += Integer.parseInt(matcher.group(2));
            }

            // 끝시간에서 경과시간 -1 빼서 시작시간 구하기
            pattern = Pattern.compile("(\\d\\.?\\d{0,3})(s)");
            matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                double second = Double.parseDouble(matcher.group(1));
                start = end - (int)(second * 1000 - 1);
            }
            edges.add(new Edge(start, end));
        }

        //시작시간 기준 오름차순 정렬하기
        edges.sort((o1, o2) -> o1.start - o2.start);

        //끝나는 시간 기준 오름차순으로 출력하는 큐 생성
        PriorityQueue<Edge> q = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        q.add(edges.get(0));
        answer = q.size();
        for (int i = 1; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            while (!q.isEmpty() && edge.start - q.peek().end >= 1000 ) {
                q.poll();
            }
            q.add(edge);
            answer = Math.max(answer, q.size());
        }

        return answer;
    }

    class Edge{
        int start;
        int end;
        Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
