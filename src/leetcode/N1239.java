package leetcode;

import java.util.List;

public class N1239 {
    int len = 0;

    public int maxLength(List<String> arr) {
        boolean[] visited = new boolean[26];
        search(arr, visited, 0, 0);
        return len;
    }

    private void search(List<String> arr, boolean[] visited, int i, int temp) {
        if (i == arr.size()) {
            len = Math.max(temp, len);
            return;
        }

        // not concat
        search(arr, visited, i + 1, temp);

        boolean flag = true;
        char[] s = arr.get(i).toCharArray();
        boolean[] sVisited = new boolean[26];
        for (char c : s) {
            int idx = c - 'a';
            if (sVisited[idx]) {
                return;
            }
            sVisited[idx] = true;
        }
        for (char c : s) {
            int idx = c - 'a';
            if (visited[idx]) {
                flag = false;
            }
        }
        if (flag) {
            for (char c : s) {
                int idx = c - 'a';
                visited[idx] = true;
            }
            search(arr, visited, i + 1, temp + s.length);
            for (char c : s) {
                int idx = c - 'a';
                visited[idx] = false;;
            }
        }
    }
}
