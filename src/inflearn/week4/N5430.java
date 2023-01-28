package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

// AC
// https://www.acmicpc.net/problem/5430
public class N5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            char[] function = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            LinkedList<Integer> intArr = Arrays.stream(
                            br.readLine()
                                    .replace("[", "")
                                    .replace("]", "")
                                    .split(",")
                    )
                    .filter(s -> s.length() > 0)
                    .map(Integer::valueOf)
                    .collect(Collectors.toCollection(LinkedList::new));

            boolean isError = false;
            boolean flag = true;
            for (char f : function) {
                switch (f) {
                    case 'R':
                        flag = !flag;
                        break;
                    case 'D':
                        if (intArr.isEmpty()) {
                            isError = true;
                        } else {
                            if (flag) {
                                intArr.removeFirst();
                            } else {
                                intArr.removeLast();
                            }
                        }
                        break;
                }
            }

            if (isError) {
                System.out.println("error");
            } else {
                if (!flag) Collections.reverse(intArr);
                System.out.println(intArr.stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]")));
            }
        }
    }
}
