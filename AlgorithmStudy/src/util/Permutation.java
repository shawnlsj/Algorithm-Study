package util;

import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Permutation {
    private static int n;
    private static int r;
    private static boolean[] visited;
    private static LinkedList<Integer> deque;
    private static LinkedList<Integer> permutations;

    public static void main(String[] args) {
        Permutation.permutation(5, 3);
    }
    public static LinkedList<Integer> permutation(int n, int r) {
        permutations = new LinkedList<>();
        visited = new boolean[n + 1];
        deque = new LinkedList<>();
        Permutation.n = n;
        Permutation.r = r;

        for (int i = 1; i <= n; i++) {
            dfs(i);
        }
        return permutations;
    }

    static void dfs(int x) {
        deque.addLast(x);
        visited[x] = true;

        if (deque.size() == r) {
            for (int i : deque) {
                permutations.add(i);
                System.out.print(i + " ");
            }
            System.out.println();
            visited[x] = false;
            deque.removeLast();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            dfs(i);
        }
        visited[x] = false;
        deque.removeLast();
        return;
    }
}
