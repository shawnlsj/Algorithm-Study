import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_42583 {
    public static void main(String[] args) {
        Programmers_42583 p = new Programmers_42583();
        int len = 2;
        int weight = 10;
        int[] trucks = {7,4,5,6};
        System.out.println(p.solution(len, weight, trucks));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        ArrayList<Truck> trucks = new ArrayList<>();
        int sum = 0;
        int time = 0;

        for (int i = 0; i < truck_weights.length; i++) {
            int truckWeight = truck_weights[i];
            if (truckWeight + sum <= weight) {
                trucks.add(new Truck(truckWeight));
                time++;
                sum += truckWeight;
                for (int j = trucks.size() - 2; j >= 0; j--) {
                    trucks.get(j).distance++;
                }
                if (trucks.get(0).distance > bridge_length) {
                    sum -= trucks.remove(0).weight;
                }
            } else {
                int x = bridge_length - trucks.get(0).distance + 1;
                time += x;
                sum -= trucks.remove(0).weight;
                for (int j = 0; j < trucks.size(); j++) {
                    trucks.get(j).distance += x;
                }
                if (truckWeight + sum <= weight) {
                    trucks.add(new Truck(truckWeight));
                    sum += truckWeight;
                } else {
                    i--;
                }
            }
            System.out.println("sum = " + sum);
        }
        System.out.println("time = " + time);
        return time + bridge_length;
    }

    static class Truck {
        int distance;
        int weight;
        Truck(int weight) {
            this.weight = weight;
            this.distance = 1;
        }
    }
}
