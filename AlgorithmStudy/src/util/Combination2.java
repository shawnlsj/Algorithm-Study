package util;

import java.util.ArrayList;

public class Combination2 {
    int n;
    int r;
    ArrayList<ArrayList<Integer>> combinations;
    ArrayList<Integer> tmpList;

    ArrayList<ArrayList<Integer>> combination(int n, int r) {
        this.n = n;
        this.r = r;
        combinations = new ArrayList<>();
        tmpList = new ArrayList<>();
        dfs(1);
        return combinations;
    }

    void dfs(int x) {
        if (tmpList.size() == r) {
            ArrayList<Integer> combinationList = new ArrayList<>();
            combinationList.addAll(tmpList);
            combinations.add(combinationList);
            return;
        }
        for (int i = x; i <= n; i++) {
            tmpList.add(i);
            dfs(i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }
}

