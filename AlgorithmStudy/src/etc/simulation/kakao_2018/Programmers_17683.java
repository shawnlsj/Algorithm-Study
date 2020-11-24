package etc.simulation.kakao_2018;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Programmers_17683 {

    public String solution(String m, String[] musicinfos) {
        m = replace(m);
        ArrayList<Music> musics = new ArrayList<>();
        PriorityQueue<Music> priorityQueue = new PriorityQueue<>((o1, o2)->{
            if (o1.endTime - o1.startTime != o2.endTime - o2.startTime) {
                return (o2.endTime - o2.startTime) - (o1.endTime - o1.startTime);
            } else{
                return o1.index - o2.index;
            }
        });


        for (int i = 0; i < musicinfos.length; i++) {
            StringTokenizer stk = new StringTokenizer(musicinfos[i], ",");

            // 시간 파싱하기
            StringTokenizer timeStk = new StringTokenizer(stk.nextToken(), ":");
            int hour = Integer.parseInt(timeStk.nextToken()) * 60;
            int minute = Integer.parseInt(timeStk.nextToken());
            int startTime = hour + minute;

            timeStk = new StringTokenizer(stk.nextToken(), ":");
            hour = Integer.parseInt(timeStk.nextToken()) * 60;
            minute = Integer.parseInt(timeStk.nextToken());
            int endTime = hour + minute;
            // ----- 시간 파싱 끝 -----

            int index = i;
            String title = stk.nextToken();
            String melody = replace(stk.nextToken());

            musics.add(new Music(startTime, endTime, index, title, melody));
        }

        for (int i = 0; i < musics.size(); i++) {

            Music music = musics.get(i);

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < music.endTime - music.startTime; j++) {
                sb.append(music.melody.charAt(j % music.melody.length())+"");
            }

            if (sb.toString().contains(m)) {
                priorityQueue.add(music);
            }
        }


        if (priorityQueue.size() == 0) {
            return "(None)";
        } else {
            return priorityQueue.poll().title;
        }
    }

    String replace(String source) {
        source = source.replace("C#", "c")
                .replace("D#","d")
                .replace("F#","f")
                .replace("G#","g")
                .replace("A#","a");
        return source;
    }

    class Music {
        int startTime;
        int endTime;
        int index;
        String title;
        String melody;

        Music(int startTime, int endTime, int index, String title, String melody) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.index = index;
            this.title = title;
            this.melody = melody;
        }
    }
}
