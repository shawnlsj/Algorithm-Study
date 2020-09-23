package util;

import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Permutation {
    private static int n;
    private static int r;
    private static boolean[] visited;
    private static int depth;
    private static LinkedList<Integer> deque;
    private static LinkedList<Integer> permutations;

    public static LinkedList<Integer> permutation(int n, int r) {
        permutations = new LinkedList<>();
        visited = new boolean[n + 1];
        depth = 0;
        deque = new LinkedList<>();
        Permutation.n = n;
        Permutation.r = r;

        for (int i = 1; i <= n; i++) {
            dfs(i);
        }

        return permutations;
    }

    static void dfs(int x) {
        deque.add(x);
        depth++;
        visited[x] = true;

        if (depth == r) {
            for (int i : deque) {
                permutations.add(i);
            }
            clear(x);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            dfs(i);
        }

        clear(x);
        return;
    }

    static int clear(int x) {
        depth--;
        visited[x] = false;
        return deque.removeLast();
    }
}
