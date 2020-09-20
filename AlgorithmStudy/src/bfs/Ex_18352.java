package bfs;

import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ex_18352 {
    static int v;
    static int e;
    static int shortestDistance;
    static int startNode;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();

    static LinkedList<Integer> list = new LinkedList<>();

    static boolean[] visited;
    static int[] cost;
    static boolean possible;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        v = Integer.parseInt(stk.nextToken());
        e = Integer.parseInt(stk.nextToken());
        shortestDistance = Integer.parseInt(stk.nextToken());
        startNode = Integer.parseInt(stk.nextToken());

        visited = new boolean[v+1];
        cost = new int[v+1];

        for (int i = 0; i <= v; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            StringTokenizer stk2 = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stk2.nextToken());
            int b = Integer.parseInt(stk2.nextToken());
            arr.get(a).add(b);
        }

        q.add(startNode);
        visited[startNode] = true;
        bfs();

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        if (possible == false) {
            System.out.println(-1);
        }

    }


    static void bfs() {

        while(!q.isEmpty()) {
            int nodeNum = q.poll();

            if (cost[nodeNum] == shortestDistance) {
                list.add(nodeNum);
                possible = true;
            }

            for (int i = 0; i < arr.get(nodeNum).size(); i++) {
                if(visited[arr.get(nodeNum).get(i)]) continue;
                q.add(arr.get(nodeNum).get(i));
                visited[arr.get(nodeNum).get(i)] = true;
                cost[arr.get(nodeNum).get(i)] += cost[nodeNum] + 1;
            }
        }
    }
}
