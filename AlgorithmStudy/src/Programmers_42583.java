package etc.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_42583 {
    static int nowBridgeWeight;
    static int[] nowDistance;
    static ArrayList<Integer> bridge = new ArrayList<>();
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        nowDistance = new int[truck_weights.length];
        nowBridgeWeight = 0;

        for (int i = 0; i < truck_weights.length; i++) {
            int truck = truck_weights[i];
            if (nowBridgeWeight + truck <= weight) {
                bridge.add(truck);
                nowBridgeWeight += truck;
                continue;
            } else {

                while (true) {

                    time++;
                    nowDistance[i]++;
                }
            }
        }









        return time;
    }
}
